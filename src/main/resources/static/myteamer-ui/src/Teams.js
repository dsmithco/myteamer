import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom'

import {
  Switch,
  Route,
  Redirect } from 'react-router';

class Teams extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false,
      teams: []
    }
  }

  componentDidMount(){
    let that = this;
    axios.get('http://localhost:8080/api/teams')
      .then(function (response) {
        that.setState({teams: response.data});
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    return (
      <div className="Teams">
        <h1>Teams</h1>
        <div className="card">
          <table className="table table-striped table-hover mb-0">
            <thead>
              <tr>
                <th className="border-top-0">Name</th>
                <th className="border-top-0">Coach(es)</th>
              </tr>
            </thead>
            <tbody>
              {this.state.teams.map((team, index)=>{
                return(
                  <tr key={index}>
                    <td><Link to={'/teams/' + team.id}>{team.name}</Link></td>
                    <td>{team.coaches.map( (coach, index) =>
                          <span>{coach.fullName}{team.coaches.length > 1 && (index + 1) < team.coaches.length && <span>, </span>} </span>
                      )}</td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default Teams;
