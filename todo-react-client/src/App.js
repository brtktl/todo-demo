import React, { Component } from 'react';
import ToDoApp from './components/todo/ToDoApp';
import './bootstrap.min.css';
import "react-datepicker/dist/react-datepicker.css";
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <ToDoApp/>
      </div>
    );
  }
}
export default App;
