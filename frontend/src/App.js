import React from 'react';
import Landingpage from "./landingpage"
import Signin from "./signin"
import Signup from "./signup"
import Viewdetails from "./viewdetails"
import Navbar from './components/admin/navbar'
import Addcar from './components/admin/addcar'
import Removecar from './components/admin/removecar'
import AdminSignin from './components/admin/adminsignin'
import AdminLanding from './components/admin/adminLandingPage'
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
        <Route path="/addcar" component={Addcar}/>
        <Route path="/removecar" component={Removecar}/>
        <Route path="/adminsignin" component={AdminSignin}/>
        <Route path="/adminLanding" component={AdminLanding}/>

        
    </Switch>
      
        
      </Router>
      
         
  );
}

export default App;
