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

export default function Details() {
  const classes = useStyles();

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
           Car Name
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            Car Description
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary">
         View More Details
        </Button>
        
      </CardActions>
    </Card>
    </div>
  );
}