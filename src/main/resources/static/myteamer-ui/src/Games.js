import React, { Component } from 'react';
// import { } from 'reactstrap';

import {
  Link,
  Switch,
  Route,
  Redirect } from 'react-router';

class Games extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false
    }
}
  render() {
    return (
      <div className="Games">
        <div className='card border-radius-0'>
          <div className='container pt-5'>
            <h1>Games</h1>
          </div>
        </div>
      </div>
    );
  }
}

export default Games;
