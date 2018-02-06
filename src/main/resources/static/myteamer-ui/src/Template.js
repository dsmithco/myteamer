import React, { Component } from 'react';
// import { } from 'reactstrap';

import {
  Link,
  Switch,
  Route,
  Redirect } from 'react-router';

class Template extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false
    }
}
  render() {
    return (
      <div className="Template">
        <h1>Template</h1>
      </div>
    );
  }
}

export default Template;
