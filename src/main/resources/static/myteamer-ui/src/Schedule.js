import React, { Component } from 'react';
// import { } from 'reactstrap';

import {
  Link,
  Switch,
  Route,
  Redirect } from 'react-router';

class Schedule extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false
    }
}
  render() {
    return (
      <div className="Schedule">
        <h4>Schedule</h4>
      </div>
    );
  }
}

export default Schedule;
