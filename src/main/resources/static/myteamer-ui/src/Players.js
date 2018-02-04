import React, { Component } from 'react';
// import { } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom'
import Fadein from './Fadein.js';
import { parseFullName } from 'parse-full-name'
import {
  Modal,
  ModalBody,
  Button } from 'reactstrap';

class Players extends Component {
  constructor(props) {
    super(props);
    this.state={
      playerModalOpen: false,
    }
  }

  componentDidMount(){
    this.setState({players: this.props.players})
  }

  togglePlayerModal(state){
    this.setState({
      playerModalOpen: (state != undefined ? state : !this.state.playerModalOpen)
    })
  }

  componentWillUpdate(nextProps, nextState){
    if(nextProps.players != this.props.players){
      this.setState({players: nextProps.players})
    }
    if(nextState.players != this.state.players){
      this.setState({players: nextState.players})
    }
  }

  shouldComponentUpdate(){
    return true
  }

  handleSubmit(form){
    let that = this;
    form.preventDefault();
    let playerName = form.target.playerName.value;
    form.target.playerName.value = '';
    let bodyParams = {
      firstName: parseFullName(playerName).first,
      lastName: parseFullName(playerName).last || '',
      middleName: parseFullName(playerName).middle || '',
      nickName: parseFullName(playerName).nick || '',
      teamId: this.props.match.params.id
    }

    axios.post('http://localhost:8080/api/players', bodyParams).then((response)=>{
      let newPlayer = response.data;
      let newPlayers = that.state.players.concat([newPlayer])
      that.setState({players: newPlayers});
      that.togglePlayerModal(false);
    })
  }

  deletePlayer(player){
    let that = this;
    if(!window.confirm("Delete " + player.fullName)){
      return
    }
    axios.delete('http://localhost:8080/api/players/' + player.id).then((response)=>{
      let index = that.state.players.indexOf(player)
      let newPlayers = that.state.players.concat();
      newPlayers.splice(index, 1);
      that.setState({players: newPlayers});
    })
  }

  render() {
    return (
      <div className="Players">
          <Fadein condition={this.state.players && this.state.players.length}>
            <div className='form-group text-center'>
              <Button color="primary" className="" onClick={()=>this.togglePlayerModal()} > + Add player</Button>{' '}
            </div>
            <div className='list-group'>
              <div className='list-group-item'>
                <div className='row'>
                  <div className='col-sm-2'>
                    <strong>Number</strong>
                  </div>
                  <div className='col-sm-4'>
                    <strong>Name</strong>
                  </div>
                </div>
              </div>
              {this.state.players && this.state.players.map((player, index)=>{
                return(
                  <div className='list-group-item' key={index}>
                    <div className='row' key={index}>
                      <div className='col-sm-2'>
                        <a href=''>
                          {player.jerseyNumber}
                        </a>
                      </div>
                      <div className='col-sm-4'>
                        <a href=''>
                          {player.fullName}
                        </a>
                      </div>
                      <div className='col-sm-6 text-right'>
                        <a href='#' className="btn btn-dark btn-sm" onClick={()=>{this.deletePlayer(player)}}>
                          Delete
                        </a>
                      </div>
                    </div>
                  </div>
                )
              })}
            </div>
          </Fadein>
          <br/>
          <div className='form-group text-center'>
            <Button color="primary" className="" onClick={()=>this.togglePlayerModal()} > + Add player</Button>{' '}
          </div>
          <Modal isOpen={this.state.playerModalOpen} toggle={()=>this.togglePlayerModal()} className={this.props.className} backdrop={true}>
            <ModalBody>
              <h4>Add Player</h4>
              <form onSubmit={(f) => this.handleSubmit(f)}>
                <div className="form-group">
                  <input type='text' name="playerName" placeholder='New Player' className='form-control'/>
                </div>
                <div className="text-center">
                  <button type='submit' className='btn btn-success'>Add Player</button>
                </div>
              </form>
            </ModalBody>
          </Modal>
        <hr/>
      </div>
    );
  }
}

export default Players;
