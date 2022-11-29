import React, { useState, useEffect } from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListNewsComponent from './components/ListNewsComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import SearchNewsComponent from './components/SearchNewsComponent';
import CreateNewsComponent from './components/CreateNewsComponent';
import UpdateNewsComponent from './components/UpdateNewsComponent';
import ViewNewsComponent from './components/ViewNewsComponent';

function App() {
  
  return (
    
    <div>
        <Router>
              <HeaderComponent />
              {/* <input placeholder="Enter Author Name"/> */}
                <div className="container">
                      <Switch> 
                          <Route path = "/" exact component = {ListNewsComponent}></Route>
                          <Route path = "/news/author" component = {SearchNewsComponent}></Route>
                          <Route path = "/news" component = {ListNewsComponent}></Route>
                          <Route path = "/add-news/:id" component = {CreateNewsComponent}></Route>
                          <Route path = "/view-news/:id" component = {ViewNewsComponent}></Route>
                          <Route path = "/update-news/:author" component = {UpdateNewsComponent}></Route> 
                          
                    </Switch>
                </div>

              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;


