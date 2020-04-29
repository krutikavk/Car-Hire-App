import React from 'react';
import Landingpage from "./landingpage"
import Signin from "./signin"
import Signup from "./signup"
import Viewdetails from "./viewdetails"
import Navbar from './Navigationbar'
//import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
function App() {
  return (
    
    
    <Router>
      
      <Switch>
        <Route path="/signin" component={Signin}/>
        <Route path="/signup" component={Signup}/>
        <Route path="/lp" component={Landingpage}/>
        <Route path="/dp" component={Viewdetails}/>
        <Route path="/nbar" component={Navbar}/>

        
    </Switch>
      
        
      </Router>
      
         
  );
}

export default App;
