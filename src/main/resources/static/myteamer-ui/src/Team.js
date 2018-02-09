import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import _ from 'lodash';
import serialize from 'form-serialize';
import { Link } from 'react-router-dom';
import {
  Modal,
  ModalBody,
  ModalHeader,
  ModalFooter,
  Button } from 'reactstrap';
import Fadein from './Fadein.js';
import Players from './Players.js';
import Messages from './Messages.js';
import Schedule from './Schedule.js';
import {
  Switch,
  Route,
  Redirect } from 'react-router';

class Team extends Component {
  constructor(props) {
    super(props);
    this.state={
      team: {},
      editTeam: {}
    }
  }

  componentDidMount(){
    let that = this;
    axios.get('http://localhost:8080/api/teams/' + this.props.match.params.id)
      .then(function (response) {
        that.setState({team: response.data});
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  toggleTeamModal(state){
    let that = this
    this.setState({
      teamModalOpen: (state != undefined ? state : !this.state.teamModalOpen)
    });
    window.setTimeout(function () {
      if(that.state.teamModalOpen){
        that.refs.teamNameInput.focus();
      }
      if(!that.state.teamModalOpen){
        that.setState({editTeam: {}})
      }
    }, 0);
  }

  editTeam(){
    this.setState({editTeam: this.state.team});
    this.toggleTeamModal(true);
  }

  handleSubmit(form){
    let that = this;
    let id;
    form.preventDefault();
    let formData = serialize(form.target, { hash: true });
    axios.put('http://localhost:8080/api/teams/' + that.state.team.id , formData).then((response)=>{
      that.setState({team: response.data});
      that.toggleTeamModal(false);
    }).catch((response)=>{
      console.error(response);
    })
  }

  addCoach(){
    this.setState({
      editTeam: {
        ...this.state.editTeam,
        coaches: this.state.editTeam.coaches.concat([{}])
      }
    })
  }

  removeCoach(index, coachList){
    let newCoachList = coachList.concat([]);
    newCoachList.splice(index, 1, {...newCoachList[index], delete: true});
    console.log(newCoachList);
    this.setState({
      editTeam: {
        ...this.state.editTeam,
        coaches: newCoachList
      }
    })
  }

  updateCoach(coach, e, field){
    let newCoach = _.assign({}, coach);
    newCoach[field] = e.target.value

    let newCoaches = this.state.editTeam.coaches.concat();
    let index = newCoaches.indexOf(coach);

    newCoaches.splice(index, 1, newCoach)
    this.setState({
      editTeam: {...this.state.editTeam, coaches: newCoaches}
    })
  }

  render() {
    return (
      <div className="Teams">
        <div className='card border-radius-0'>
          <div className='container'>
            <br/>
            <Link to="/teams">« Teams</Link>
              <br/>
                <br/>
                  <div className='row'>
                    <div className='col-sm-12'>
                      <h1 className="mb-0 pb-0">
                        {this.state.team.name} &nbsp;
                        <a href='#' className="btn btn-light btn-sm mb-2 float-right" onClick={()=>{this.editTeam()}}>
                          <i className="la la-edit"></i> Edit team
                        </a>
                      </h1>
                      <div className='form-group mb-3' style={{fontSize: 14}}>
                        <div className='row'>
                          <div className='col-sm-auto pr-sm-2'>
                            <span>{this.state.team.division} {this.state.team.sport}</span>
                          </div>
                          <div className='col-sm-auto pl-sm-2'>
                            <span className='text-muted'>Coach{this.state.team.coaches && this.state.team.coaches.length > 1 ? 'es':''}:</span> {this.state.team.coaches && this.state.team.coaches.map((coach, index)=>{
                              return(
                                <span key={index}>
                                  <span>{coach.fullName}</span>{this.state.team.coaches.length > 1 && (index + 1 < this.state.team.coaches.length ? ', ':'')}
                                </span>
                              )
                            })}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <ul className="nav nav-tabs border-bottom-0">
                    <li className="nav-item">
                      <Link className={`nav-link ${(this.props.location.pathname.indexOf('players') > -1 ? 'active':'')}`}
                            to={`/teams/${this.props.match.params.id}/players`}> <i className='la la-users'></i> Players
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link className={`nav-link ${(this.props.location.pathname.indexOf('schedule') > -1 ? 'active':'')}`}
                            to={`/teams/${this.props.match.params.id}/schedule`}> <i className='la la-calendar'></i> Schedule
                      </Link>
                    </li>
                    <li className="nav-item">
                      <Link className={`nav-link ${(this.props.location.pathname.indexOf('messages') > -1 ? 'active':'')}`}
                            to={`/teams/${this.props.match.params.id}/messages`}> <i className='la la-envelope'></i> Messages
                      </Link>
                    </li>
                  </ul>
          </div>
        </div>
        <div className='container'>
          <Fadein condition={this.state.team.id}>
            <br/>
            <Switch>
              <Route
                path="/teams/:id/players"
                render={()=><Players match={this.props.match}/>}
              />
              <Route
                path="/teams/:id/schedule"
                render={()=><Schedule match={this.props.match}/>}
              />
              <Route
                path="/teams/:id/messages"
                render={()=><Messages match={this.props.match}/>}
              />
              <Redirect to={this.props.location.pathname + '/players'}/>
            </Switch>
          </Fadein>
        </div>


        <Modal isOpen={this.state.teamModalOpen} toggle={()=>this.toggleTeamModal()} className={this.props.className} backdrop={true}>
          <form onSubmit={(f) => this.handleSubmit(f)}>
            <ModalHeader>
              <div>{this.state.editTeam.id ? 'Edit':'Add'} Team</div>
            </ModalHeader>
            <ModalBody>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Name
                  </div>
                  <div className='col-sm-7'>
                    <div className="form-group">
                      <input type='text' ref="teamNameInput" name="name" defaultValue={this.state.editTeam.name} placeholder='First Name' className='form-control simple'/>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Sport
                  </div>
                  <div className='col-sm-7'>
                    <div className="form-group">
                      <input type='text' name="sport" defaultValue={this.state.editTeam.sport} placeholder='' className='form-control simple'/>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Division
                  </div>
                  <div className='col-sm-4'>
                    <div className="form-group">
                      <input type='text' name="division" defaultValue={this.state.editTeam.division} placeholder='' className='form-control simple'/>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Coaches
                  </div>
                  <div className='col-sm-9'>
                    {this.state.editTeam.coaches && this.state.editTeam.coaches.map((coach, index)=>{
                      return(
                        <div className='row' style={{display: (coach.delete ? 'none':'')}}key={index}>
                          <div className='col-sm-5 pr-sm-0'>
                            <div className="form-group">
                              <input
                                type='text'
                                name={`coaches[${index}][firstName]`}
                                value={coach.firstName}
                                onChange={(e)=>{this.updateCoach(coach, e, 'firstName')}}
                                placeholder='First name'
                                className='form-control simple'
                              />
                              <input
                                type='hidden'
                                name={`coaches[${index}][id]`}
                                value={coach.id}
                              />
                              <input
                                type='hidden'
                                name={`coaches[${index}][teamId]`}
                                value={this.state.team.id}
                              />
                              {coach.delete &&
                                <input
                                  type='hidden'
                                  name={`coaches[${index}][delete]`}
                                  value={true}
                                />
                              }
                            </div>
                          </div>
                          <div className='col-sm-5 mr-sm-0 pr-sm-0'>
                            <div className="form-group">
                              <input
                                type='text'
                                name={`coaches[${index}][lastName]`}
                                value={coach.lastName}
                                onChange={(e)=>{this.updateCoach(coach, e, 'lastName')}}
                                placeholder='Last name'
                                className='form-control simple'
                              />
                            </div>
                          </div>
                          <div className='col-sm-2 pr-sm-0'>
                            <div className="form-group">
                              <a href='#' className='btn btn-light btn-sm rounded-curve' onClick={()=>{this.removeCoach(index, this.state.editTeam.coaches)}}><i className='la la-trash'></i></a>
                            </div>
                          </div>
                        </div>
                      )
                    })}
                    <button className="btn btn-sm btn-info" type="button" onClick={()=>{this.addCoach()}}><i className='la la-plus'></i> coach</button>
                  </div>
                </div>
            </ModalBody>
            <ModalFooter>
              <div className="float-center">
                <button type='submit' className='btn btn-success float-center'>{this.state.editTeam.id ? 'Save':'Add'} Team</button>
              </div>
            </ModalFooter>
          </form>
        </Modal>
      </div>
    );
  }
}

export default Team;
