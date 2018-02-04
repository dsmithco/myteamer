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
              <a className="nav-link active" href="#">Players</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Schedule</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">Messages</a>
            </li>
          </ul>
          <br/>
          <Switch>
            <Route path="/teams/:id/players" render={()=><Players match={this.props.match} players={this.state.team.players}/>}/>
            <Redirect to={this.props.location.pathname + '/players'}/>
          </Switch>
        </Fadein>
      </div>
    );
  }
}

export default Team;
