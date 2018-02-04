import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';
import { Link } from 'react-router-dom'
import {
  Switch,
  Route,
  Redirect } from 'react-router';
  import Teams from './Teams.js'
  import Team from './Team.js'
  import Games from './Games.js'

class App extends Component {
  constructor(props) {
    super(props);
    this.state={
      isOpen: false
    }
  }

  pageChange(){
    console.log('change');
      this.setState({isOpen: false})
  }

  render() {
    return (
      <div className="App">
        <Navbar color="dark" className="text-white" links="white" dark expand="md">
          <div className="container">
            <Link className="navbar-brand" to="/">MyTeamer</Link>
            <NavbarToggler onClick={()=>{this.setState({isOpen: !this.state.isOpen})}} />
            <Collapse isOpen={this.state.isOpen} navbar>
              <Nav className="ml-auto" navbar>
                <NavItem>
                  <Link className="nav-link" onClick={()=>this.pageChange()} to="/teams">Teams</Link>
                </NavItem>
                <NavItem>
                  <Link className="nav-link" onClick={()=>this.pageChange()} to="/games">Games</Link>
                </NavItem>
                <UncontrolledDropdown nav>
                  <DropdownToggle nav caret>
                    Team
                  </DropdownToggle>
                  <DropdownMenu >
                    <DropdownItem>
                      Option 1
                    </DropdownItem>
                    <DropdownItem>
                      Option 2
                    </DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem>
                      Reset
                    </DropdownItem>
                  </DropdownMenu>
                </UncontrolledDropdown>
              </Nav>
            </Collapse>
          </div>
        </Navbar>
        <br/>
        <div className="container text-left">
          <Switch>
            <Route exact path="/teams" component={Teams}/>
            <Route path="/teams/:id" component={Team}/>
            <Route path="/games" component={Games}/>
            // <Redirect from="/invoices" to="/invoices/dashboard"/>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
