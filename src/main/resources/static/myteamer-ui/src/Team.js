import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Team extends Component {
  constructor(props) {
    super(props);
    this.state={
      team: {}
    }
  }

  componentDidMount(){
    let that = this;
    console.log(this.props);
    axios.get('http://localhost:8080/api/teams/' + this.props.match.params.id)
      .then(function (response) {
        that.setState({team: response.data});
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  render() {
    return (
      <div className="Teams">
        <Link to="/teams">« Teams</Link>
          <br/>
            <br/>
        <h1>{this.state.team.name}</h1>
      </div>
    );
  }
}

export default Team;
