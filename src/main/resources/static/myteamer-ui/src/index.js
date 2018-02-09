import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import './line-awesome/css/line-awesome.min.css';
import { BrowserRouter } from 'react-router-dom'
import {
  Switch,
  Route,
  Redirect } from 'react-router';
ReactDOM.render(
  <BrowserRouter>
    <Route path="/" component={App}/>
  </BrowserRouter>,
  document.getElementById('root'));
registerServiceWorker();
