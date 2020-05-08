
import React ,{Component} from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import { fade, makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import axios from 'axios';

import SearchIcon from '@material-ui/icons/Search';
import Details from './detailspage';
import Navbar from './Navigationbar'


export default class Landingpage extends Component {
  constructor(props){
    super(props);
    this.state={cars:[]}
  }
  componentDidMount(){
   const data={city:localStorage.getItem("city"), type:localStorage.getItem("type")}
  
    axios.post('http://localhost:8080/api/vehicles/search',data)
            .then((response) => {              
              console.log(response.data);
              this.setState({
               cars : this.state.cars.concat(response.data) 
            });
            
        });
        
}

render(){
  console.log(this.state.cars)
  let details = this.state.cars.map(product => {
    return(
        <Details car_name={product.vehicleName} car_description={product.vehicleType} 
         car_id={product.vehicleId}/>   
    )
})
//product_id={product._id}
  return (
    <div >
      <Navbar/>
      <Grid container  spacing={20}>
      {details}
      </Grid>
      
      
    </div>
  );
}
}