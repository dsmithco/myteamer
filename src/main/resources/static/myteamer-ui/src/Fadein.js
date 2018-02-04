import React, { Component } from 'react';

class Fadein extends Component {
  constructor(props) {
    super(props);
    this.state={
      height: "0px",
      overflow: 'hidden'
    }
  }
  componentDidMount(){
    this.setState({height: window.innerHeight + "px"})
  }

  render () {
    let style = {
            transition: '.8s',
            overflow: this.state.overflow,
            maxHeight: (this.props.condition ? this.state.height:'0px'),
            opacity: (this.props.condition ? '1':'0')
          }
    if(this.props.condition){
      let that = this;
      setTimeout(function () {
        that.setState({
          overflow: "visible",
          height: "100%"

        })
      }, 500);
    }


    return (
      <div ref={this.fadeElement} style={style}>{this.props.children}</div>
    )
  }

}

export default Fadein;
