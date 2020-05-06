import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import image from '../images/user.png';


const useStyles = makeStyles({
    root: {
      maxWidth: 345,
    },
    media: {
      height: 140,
    },
  });
  
export default function UserDetails(props) {
  const classes = useStyles();

// handleClick = e => {

//     e.preventDefault(); 
//     // localStorage.setItem("selectedcar", props._id);
// //    window.open('/dp', "_self");
//   }

  return (
      <div className="fixed-bottom">
    <Card className={classes.root} justify="center">
      <CardActionArea>
      <CardMedia
          component="img"
          alt="user image"
          height="140"
          src={image}
          title="user image"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
           {props.user_name}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            User Address :{props.user_address}
          </Typography>
          <Typography variant="body2" color="textSecondary" component="p">
            User email :{props.user_email}
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions>
        <Button size="small" color="primary" onClick>
          Terminate User Membership 
        </Button>
        
      </CardActions>
    </Card>
    </div>
  );
}