// import React from 'react';
// import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

// class Landingpage extends React.Component {
//     state = {
//       query: "",
//       data: [],
//       filteredData: []
//     };
  
//     handleInputChange = event => {
//       const query = event.target.value;
  
//       this.setState(prevState => {
//         const filteredData = prevState.data.filter(element => {
//           return element.name.toLowerCase().includes(query.toLowerCase());
//         });
  
//         return {
//           query,
//           filteredData
//         };
//       });
//     };
  
//     // getData = () => {
//     //   fetch(`http://localhost:8080/api/vehicles`)
//     //     .then(response => response.json())
//     //     .then(data => {
//     //       const { query } = this.state;
//     //       const filteredData = data.filter(element => {
//     //         return element.name.toLowerCase().includes(query.toLowerCase());
//     //       });
  
//     //       this.setState({
//     //         data,
//     //         filteredData
//     //       });
//     //     });

//     // };
  
//     // componentWillMount() {
//     //   this.getData();
//     // }
  
//     render() {
//       return (
        
//             <div>

// <form>
//     <h1>Landing Page</h1>
//   <input
//     placeholder="Search for..."
//     value={this.state.query}
//     onChange={this.handleInputChange}
//   />
// </form>
// <div>{this.state.filteredData.map(i => <p>{i.name}</p>)}</div>
// </div>
        
        
        
      
        
    
    
//       );
//     }
//   }

// export default Landingpage;
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
   
    axios.get('http://localhost:8080/vehicles')
            .then((response) => {
              
              console.log(response.data);
              this.setState({
               cars : this.state.cars.concat(response.data) 
            });
            
        });
}

render(){
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
      <br></br>
      <br></br>
      <Grid container  spacing={20}>
      {details}
      

      </Grid>
      
    </div>
  );
}
}