import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import _ from 'lodash';
import { Link } from 'react-router-dom'
import Fadein from './Fadein.js';
import { parseFullName } from 'parse-full-name'
import {
  Modal,
  ModalBody,
  ModalHeader,
  ModalFooter,
  Button } from 'reactstrap';

class Players extends Component {
  constructor(props) {
    super(props);
    this.state={
      playerModalOpen: false,
      editPlayer: {}
    }
  }

  componentDidMount(){
    let that = this;
    axios.get('http://localhost:8080/api/teams/' + that.props.match.params.id + '/players').then((response)=>{
      that.setState({players: response.data})
    })
  }

  togglePlayerModal(state){
    let that = this
    this.setState({
      playerModalOpen: (state != undefined ? state : !this.state.playerModalOpen)
    });
    window.setTimeout(function () {
      if(that.state.playerModalOpen){
        that.refs.playerNameInput.focus();
      }
      if(!that.state.playerModalOpen){
        that.setState({editPlayer: {}})
      }
    }, 0);
  }

  componentWillUpdate(nextProps, nextState){
    if(nextProps.players != this.props.players){
      this.setState({players: nextProps.players})
    }
    if(nextState.players != this.state.players){
      this.setState({players: nextState.players})
    }
  }

  editPlayer(player){
    this.setState({editPlayer: player});
    this.togglePlayerModal(true);
  }

  handleSubmit(form){
    let that = this;
    let id;
    form.preventDefault();
    let bodyParams = {
      firstName: form.target.firstName.value,
      lastName: form.target.lastName.value,
      middleName: form.target.middleName.value,
      nickName: form.target.nickName.value,
      jerseyNumber: form.target.jerseyNumber.value,
      teamId: this.props.match.params.id
    }
    if(this.state.editPlayer.id){
      this.updatePlayer(this.state.editPlayer.id, bodyParams);
    }else{
      this.createPlayer(bodyParams);
    }
  }

  createPlayer(bodyParams){
    let that = this;
    axios.post('http://localhost:8080/api/players', bodyParams).then((response)=>{
      let newPlayer = response.data;
      let newPlayers = that.state.players.concat([newPlayer])
      that.setState({players: newPlayers});
      that.togglePlayerModal(false);
    })
  }

  updatePlayer(id, bodyParams){
    let that = this;
    axios.put('http://localhost:8080/api/players/' + id , bodyParams).then((response)=>{
      let newPlayers = that.state.players.concat([])
      newPlayers.map((player)=>{
        if(player.id == id){
          _.assign(player, response.data)
        }
      })
      that.setState({players: newPlayers});
      that.togglePlayerModal(false);
    })
  }

  deletePlayer(player){
    let that = this;
    if(!window.confirm("Delete " + player.fullName)){
      return
    }
    axios.delete('http://localhost:8080/api/players/' + player.id).then((response)=>{
      let index = that.state.players.indexOf(player)
      let newPlayers = that.state.players.concat();
      newPlayers.splice(index, 1);
      that.setState({players: newPlayers});
    })
  }

  render() {
    return (
      <div className="Players">
        <div className='form-group'>
          <h3>Players &nbsp;
            <Button color="success" className="btn-sm float-right" style={{borderRadius: '100px'}} onClick={()=>this.togglePlayerModal()} >
              <i className="la la-plus"></i> Add player
             </Button>
          </h3>
        </div>
        <Fadein condition={this.state.players && this.state.players.length}>
          <div className='list-group card'>
            <div className='list-group-item bg-muted border-0'>
              <div className='row'>
                <div className='col-sm-2'>
                  <strong>Number</strong>
                </div>
                <div className='col-sm-4'>
                  <strong>Name</strong>
                </div>
              </div>
            </div>
            {this.state.players && this.state.players.map((player, index)=>{
              return(
                <div className='list-group-item border-0' key={index}>
                  <div className='row' key={index}>
                    <div className='col-sm-2'>
                      {player.jerseyNumber}
                    </div>
                    <div className='col-sm-4'>
                      {player.fullName}
                    </div>
                    <div className='col-sm-6 text-right'>
                      <a href='#' className="btn btn-light btn-sm" onClick={()=>{this.editPlayer(player)}}>
                        <i className="la la-edit"></i> Edit
                      </a>&nbsp;
                      <a href='#' className="btn btn-light btn-sm" onClick={()=>{this.deletePlayer(player)}}>
                        <i className="la la-trash"></i> Delete
                      </a>
                    </div>
                  </div>
                </div>
              )
            })}
          </div>
        </Fadein>
        <Modal isOpen={this.state.playerModalOpen} toggle={()=>this.togglePlayerModal()} className={this.props.className} backdrop={true}>
          <form onSubmit={(f) => this.handleSubmit(f)}>
            <ModalHeader>
              <div>{this.state.editPlayer.id ? 'Edit':'Add'} Player</div>
            </ModalHeader>
            <ModalBody>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Name
                  </div>
                  <div className='col-sm-3 pr-sm-0'>
                    <div className="form-group">
                      <input type='hidden' name="playerId" defaultValue={this.state.editPlayer.id}/>
                      <input type='text' ref="playerNameInput" name="firstName" defaultValue={this.state.editPlayer.firstName} placeholder='First Name' className='form-control simple'/>
                      <input type='hidden' name="nickName" defaultValue={this.state.editPlayer.nickName} placeholder='Nick Name' className='form-control simple'/>
                    </div>
                  </div>
                  <div className='col-sm-2 pr-sm-0'>
                    <div className="form-group">
                      <input type='text' name="middleName" defaultValue={this.state.editPlayer.middleName} placeholder='Middle Name' className='form-control simple'/>
                    </div>
                  </div>
                  <div className='col-sm-4'>
                    <div className="form-group">
                      <input type='text' name="lastName" defaultValue={this.state.editPlayer.lastName} placeholder='Last Name' className='form-control simple'/>
                    </div>
                  </div>
                </div>
                <div className='row'>
                  <div className='col-sm-3 pr-sm-0 col-form-label text-sm-right'>
                    Jersey No.
                  </div>
                  <div className='col-sm-3'>
                    <div className="form-group">
                      <input type='text' name="jerseyNumber" defaultValue={this.state.editPlayer.jerseyNumber} placeholder='#' className='form-control simple'/>
                    </div>
                  </div>
                </div>
            </ModalBody>
            <ModalFooter>
              <div className="float-center">
                <button type='submit' className='btn btn-success float-center'>{this.state.editPlayer.id ? 'Save':'Add'} Player</button>
              </div>
            </ModalFooter>
          </form>
        </Modal>
      </div>
    );
  }
}

export default Players;
