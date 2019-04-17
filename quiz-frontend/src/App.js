import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

class App extends Component {
    
    constructor(props) {
        super(props);   
        this.state = {value: '', quiz: {}};
    }

    handleChange = (event) => {
        this.setState({value: event.target.value});
    }

    handleSubmit = () => {
        axios.post('http://localhost:8080/test', { urlList: [ this.state.value ] })
            .then(response => this.setState({quiz: response.data}))
    }
    
  render() {
        console.log(JSON.stringify(this.state.quiz))
    return (
      <div className="App">
        <header className="App-header">
          
            
                <input type="text" size="100" value={this.state.value} onChange={ this.handleChange } /><br/>
            <button className="button-generate" type="button" onClick={this.handleSubmit}>Generate!</button>
                
            
            <textarea value={JSON.stringify(this.state.quiz)}></textarea>
            
        </header>
      </div>
    );
  }
}

export default App;
