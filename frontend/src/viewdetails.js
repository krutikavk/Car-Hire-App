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
const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
});
//const classes = useStyles();
export default class Cardetails extends Component{
  constructor(props) {
    super(props);
    this.state = {
      prod: []
    };
  }

  componentDidMount() {
    let selectedCarId = localStorage.getItem("selectedcar");
    console.log(selectedCarId)
    axios
      .get('http://localhost:8080/api/vehicles/' + selectedCarId)
      .then(response => {
        
       console.log(response);
        this.setState({
          prod: response.data
        });
      });
  }

  
render(){
  return (
      <div backGroundColor={blue}>
    <Card className={useStyles.root} 
    style={{ display: 'inline-block', marginTop: '60px', marginLeft: '550px', width: '1000px', height: '500px' }}>
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
            car_description<br></br>
            Car Cost $15 per hour <br></br>
            Other Details <br></br>
            Other Details <br></br>
            Other Details <br></br>
            Other Details <br></br>
          </Typography>
        </CardContent>       
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" style={{marginLeft:'100px'}}>
                Book this car
        </Button>
        
      </CardActions>
    </Card>
    </div>
  );
}
}