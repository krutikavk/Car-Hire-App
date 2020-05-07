import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import image from './car.jpg';
const useStyles = makeStyles({
  root: {
    maxWidth: 345,
  },
});

export default function Details(props) {
  const classes = useStyles();
  function handleClick(e) {
    e.preventDefault(); 
    localStorage.setItem("selectedcar", props.car_id);
   window.open('/dp', "_self");
  }

  return (
      <div className="fixed-bottom">
    <Card className={classes.root} justify="center">
      <CardActionArea>
        <CardMedia
          component="img"
          alt="car image"
          height="140"
          src={image}
          title="car image"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
           {props.car_name}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Car Description :{props.car_description}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" onClick={handleClick}>
         View More Details
        </Button>
        
      </CardActions>
    </Card>
    </div>
  );
}