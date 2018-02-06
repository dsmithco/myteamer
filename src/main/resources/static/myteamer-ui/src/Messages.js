import React, { Component } from 'react';
// import { } from 'reactstrap';

import {
  Link,
  Switch,
  Route,
  Redirect } from 'react-router';

class Messages extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false
    }
}
  render() {
    return (
      <div className="Messages">
        <h4>Messages</h4>
      </div>
    );
  }
}

export default Messages;
