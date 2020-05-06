import React ,{Component} from 'react';

import {  makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import UserDetails from './userDetails';
import Navbar from './navbar';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
}));


class Landingpage extends Component {
 
  constructor(props){
    super(props);
    this.state={users:[]}
  }

  

  componentDidMount(){
    // const classes = useStyles();
    
    axios.get('http://localhost:8080/api/drivers')
            .then((response) => {
              // console.log(response.data);
              this.setState({
               users : response.data
            });
            
        });
        
}

render(){
 
  // console.log(this.state.users)
  let details = this.state.users.map(product => {
    console.log(product)
    return(
        <UserDetails   user_name={product.driverName} user_address={product.driverAddress} 
         user_email={product.driverEmailId} user_memebership_end={product.driverMembershipEnd}/>   
    )
})

  return (
    <div>

      <Navbar/>    
     <div>
  <Grid container item xs={12} spacing={3}>
  <Grid item xs={3}>
  <Paper padding = 'theme.spacing(1)' color='theme.palette.text.secondary'>{details}</Paper>
  <div></div>
   </Grid>
   <div>
     </div>
  </Grid>
  </div>
 
  </div> 
  );
}
}

export default Landingpage