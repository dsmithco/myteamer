import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {
  Modal,
  ModalHeader,
  ModalBody,
  Button,
  ModalFooter } from 'reactstrap';
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
      team: {
        players: []
      }
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

  render() {
    return (
      <div className="Teams">
        <Link to="/teams">« Teams</Link>
        <br/><br/>
        <Fadein condition={this.state.team.id}>
          <h1>{this.state.team.name}</h1>
          <ul className="nav nav-tabs">
            <li className="nav-item">
              <Link className={`nav-link ${(this.props.location.pathname.indexOf('players') > -1 ? 'active':'')}`}
                    to={`/teams/${this.props.match.params.id}/players`}> Players
              </Link>
            </li>
            <li className="nav-item">
              <Link className={`nav-link ${(this.props.location.pathname.indexOf('schedule') > -1 ? 'active':'')}`}
                    to={`/teams/${this.props.match.params.id}/schedule`}> Schedule
              </Link>
            </li>
            <li className="nav-item">
              <Link className={`nav-link ${(this.props.location.pathname.indexOf('messages') > -1 ? 'active':'')}`}
                    to={`/teams/${this.props.match.params.id}/messages`}> Messages
              </Link>
            </li>
          </ul>
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
    );
  }
}

export default Team;
