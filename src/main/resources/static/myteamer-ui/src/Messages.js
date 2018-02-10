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
        <div className='card border-radius-0'>
          <div className='container pt-5'>
            <h1>Messages</h1>
          </div>
        </div>
      </div>
    );
  }
}

export default Messages;
