import React, { Component } from 'react';
import { CookiesProvider } from 'react-cookie';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import './App.css';
import Home from './Home';
import DiaryList from './components/DiaryList';
import DiaryEdit from './components/DiaryEdit';
import ExerciseList from './components/ExerciseList';
import ExerciseEdit from './components/ExerciseEdit';
import ExerciseVisual from './components/ExerciseVisual';

class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
          <Switch>
            <Route path='/' exact={ true } component={ Home } />
            <Route path='/visual' exact={ true } component={ ExerciseVisual } />
            <Route path='/diaries' exact={ true } component={ DiaryList } />
            <Route path='/diaries/:id' component={ DiaryEdit }/>
            <Route path='/exercises' exact={ true } component={ ExerciseList } />
            <Route path='/exercises/:id' component={ ExerciseEdit } />
          </Switch>
        </Router>
      </CookiesProvider>
    )
  }
}

export default App;
