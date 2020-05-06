import React ,{Component} from 'react';

import {  makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';
import LocationDetails from './locationDetails';
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


class Locations extends Component {
 
  constructor(props){
    super(props);
    this.state={locations:[]}
  }

  

  componentDidMount(){
    // const classes = useStyles();
    
    axios.get('http://localhost:8080/api/locations')
            .then((response) => {
             console.log(response.data);
              this.setState({
                locations : response.data
            });
            
        });
        
}

render(){
 
  console.log(this.state.locations)
  let details = this.state.locations.map(product => {
    return(
        <LocationDetails city={product.city} address={product.address} 
         capacity={product.capacity} filled={product.filledSpots}/>   
    )
})

  return (
    <div>

      <Navbar/>    
     <div>
       <div>
  <Grid container item xs={12} spacing={2}>
  <Grid item xs={4}>
   <Paper padding = 'theme.spacing(1)'  color='theme.palette.text.secondary'>{details}</Paper> 
  
        </Grid>
  </Grid>
  </div>
 </div>
  </div> 
  );
}
}

export default Locations