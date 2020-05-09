import React ,{Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import logo from './car.jpg';
import { pink, blue } from '@material-ui/core/colors';
import axios from 'axios'
import Navbar from './Navigationbar'
import DatePicker from 'react-date-picker';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import 'date-fns';
import TextField from '@material-ui/core/TextField';

import DateTimePicker from 'react-datetime-picker'

const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
  
});
const useStyless = makeStyles((theme) => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },
}));
//const classes = useStyles();
export default class Cardetails extends Component{
  constructor(props) {
    super(props);
    this.state = {
      prod: [],
      hours:0,
      date: new Date(),

    };
    // this.h=this.setTime.bind(this);
    this.bookthiscar=this.bookthiscar.bind(this);
    this.handleHour=this.handleHour.bind(this);
  }

  // setTime = e => {
  // this.setState({ 
  //   [e.target.date]: e.target.value
  //  });

  // }

  onChange = date => {
    this.setState({
      startDate: date
    });
  };


  handleChange = (event) => {
    this.logs.unshift("change: " + event.target.value);

    this.setState({
        value: event.target.value,
        events: this.logs.slice()
    });
}

  handleHour=(e)=>{
    this.setState({
      [e.target.name]: e.target.value
  });

  }

  bookthiscar=(e)=>{
    const data={
      pickup:this.state.date,
      hours:this.state.hours
    }
    console.log(data)

    let selectedCarId = localStorage.getItem("selectedcar");
    let email=localStorage.getItem("email");


   //let email="user3@carrental.com"
    //window.open('/Pickup', "_self");
    axios.post("http://localhost:8080/api/reservation?driverEmailId="+email+"&vehicle_id="+selectedCarId,data).then(response => {  
      if(response.status === 200){
        localStorage.setItem("reservationID",response.data.reservationId)
        console.log(localStorage.getItem("reservationID"));
        alert("Car reserved")
       window.open('/Pickup', "_self");        
             } 
             else if(response.status === 400){
              alert("Some Error Occured")
             }
            }
            
            ) 
             
            }
  

  componentDidMount() {
    let selectedCarId = localStorage.getItem("selectedcar");
    console.log(selectedCarId)
    axios.get('http://localhost:8080/api/vehicles/' + selectedCarId)
      .then(response => {        
       console.log(response);
        this.setState({
          prod: response.data
        });
      });

  }
 
             
 
render(){
  console.log()
  let price=this.state.prod.vehicleBasePrice*this.state.hours;
  return (
    <div>
    <Navbar/>
      <div backGroundColor={blue}>
    <Card className={useStyles.root} 
    style={{ display: 'inline-block', marginTop: '60px', marginLeft: '550px', width: '500px', height: '500px' }}>
      <CardActionArea>
        <CardMedia
          component="img"
          alt="car image"
          height="140"
          src={logo}
          title="car image"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
           car name {this.state.prod.vehicleName}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            This is Great car<br></br>
            Car Cost {this.state.prod.vehicleBasePrice} per hour<br></br>
           
          <h3>Cost for this Booking is : {price}</h3> 
          </Typography>
        </CardContent>       
      </CardActionArea>
      <CardActions>
      
    <form className={useStyless.container} noValidate>
      {/* <TextField
        id="datetime-local"
        label="Select Date and Time"
        type="datetime-local"
        dateFormat="yyyy-MM-dd, h:mm aa"
        onChange={this.setTime}
        value={this.state.date}
        className={useStyless.textField}
        InputLabelProps={{
          shrink: true,
        }}
      /> */}
      <div>
        <DateTimePicker
          onChange={this.onChange}
          value={this.state.date}
        />
      </div>
      <TextField
      label="Select No of Hours"
      value={this.state.hours}
      name="hours"
      onChange={this.handleHour}
      />
{/* <InputMoment
  moment={this.state.moment}
  onChange={this.handleChange}
  onSave={this.handleSave}
  minStep={1} // default
  hourStep={1} // default
  prevMonthIcon="ion-ios-arrow-left" // default
  nextMonthIcon="ion-ios-arrow-right" // default
/> */}
    </form>
    <br/>
        <Button size="small" color="primary" style={{marginLeft:'100px'}} onClick={this.bookthiscar}>
                Book this car
        </Button>
        
      </CardActions>
    </Card>
    </div>
    </div>
  );
}
}