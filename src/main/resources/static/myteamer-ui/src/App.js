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
import Messages from './Messages.js'

const navStyle = {
  navbar: {

  },
  link: {
    lineHeight: '1',
    paddingTop: '14px',
    marginBottom: '-1px',
    fontWeight: '300'
  },
  iconWrapper: {
    marginBottom: '-2px'
  },
  icon: {
    lineHeight: '.2',
    fontSize: '1.5em',
    fontWeight:'300'
  },
  text: {
    lineHeight: '.5',
    fontSize: '.8em'
  }
}


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

  componentDidMount(){
    console.log(this.props);
  }

  render() {
    return (
      <div className="App">
        <Navbar color="dark" className="text-white" links="white" dark expand="md">
          <div className="container">
            <Link className="navbar-brand" to="/">MyTeamer</Link>
            <NavbarToggler onClick={()=>{this.setState({isOpen: !this.state.isOpen})}} />
            <Collapse isOpen={this.state.isOpen} navbar>
              <Nav className="ml-auto" style={navStyle.navbar} navbar>
                <NavItem>
                  <Link style={navStyle.link} className={`nav-link ${this.props.location.pathname.indexOf('teams') >-1 ? 'active':''}`} onClick={()=>this.pageChange()} to="/teams">
                    <div style={navStyle.iconWrapper}>
                      <i style={navStyle.icon} className="la la-users"></i>
                    </div>
                    <span style={navStyle.text}>Teams</span>
                  </Link>
                </NavItem>
                <NavItem>
                  <Link style={navStyle.link} className={`nav-link ${this.props.location.pathname.indexOf('games') >-1 ? 'active':''}`} onClick={()=>this.pageChange()} to="/games">
                    <div style={navStyle.iconWrapper}>
                      <i style={navStyle.icon} className="la la-trophy"></i>
                    </div>
                    <span style={navStyle.text}>Games</span>
                  </Link>
                </NavItem>
                <NavItem>
                  <Link style={navStyle.link} className={`nav-link ${this.props.location.pathname.indexOf('messages') >-1 ? 'active':''}`} onClick={()=>this.pageChange()} to="/messages">
                    <div style={navStyle.iconWrapper}>
                      <i style={navStyle.icon} className="la la-envelope"></i>
                    </div>
                    <span style={navStyle.text}>Messages</span>
                  </Link>
                </NavItem>
                {/*
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
                */}
              </Nav>
            </Collapse>
          </div>
        </Navbar>
        <div className="text-left">
          <Switch>
            <Route exact path="/teams" component={Teams}/>
            <Route path="/teams/:id" component={Team}/>
            <Route path="/games" component={Games}/>
            <Route path="/messages" component={Messages}/>
            // <Redirect from="/invoices" to="/invoices/dashboard"/>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
