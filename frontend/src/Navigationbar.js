import React ,{Component} from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import { fade, makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'
import SearchIcon from '@material-ui/icons/Search';
import Details from './detailspage';

const classes = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    // vertical padding + font size from searchIcon
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '12ch',
      '&:focus': {
        width: '20ch',
      },
    },
  },
}));
//const classes = useStyles();
//const classes = useStyles();
export default class Navbar extends Component{
  constructor(props) {
    super(props);
    this.state = {
      city:"",
      type:""
      
    };
    
    this.handleClick=this.handleClick.bind(this);
    this.handleChange=this.handleChange.bind(this);
  }
  handleChange(e) {
    this.setState({
        [e.target.name]: e.target.value
    });
  }
  handleClick(e){
    e.preventDefault()
    localStorage.setItem("city",this.state.city)
    localStorage.setItem("type",this.state.type)
    window.open('/lp','_self')
  }


render(){
  let cartypes = [];
  cartypes.push(<option value="SMALL"> SMALL</option>);
      cartypes.push(<option value="SUV"> SUV</option>);
      cartypes.push(<option value="SEDAN"> SEDAN</option>);
      cartypes.push(<option value="TRUCK"> TRUCK</option>);
      cartypes.push(<option value="">  </option>);
    
  return (
    <div >
      <AppBar position="static">
        <Toolbar>
          <IconButton
            edge="start"
            className={classes.menuButton}
            color="inherit"
            aria-label="open drawer"
          >
            
          </IconButton>
          <Typography className={classes.title} variant="h6" noWrap>
            Rent a Car
          </Typography>
          <Button  href="/profile" variant="h6" noWrap>
         Profile
          </Button>
          <Button  href="/lp" variant="h6" noWrap>
         Home
          </Button>
          <div className={classes.search}>
            
            <InputBase
              placeholder="Search…"
              name="city"
              onChange={this.handleChange}
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }} 
            />
            
          </div>
          
            <div>
              <select
              name="type"
                value={this.state.cartype}
                onChange={this.handleChange}
                id="month"
              >
                {cartypes}
              </select>
            </div>
            <Button onClick={this.handleClick}>Search</Button>
          <br/>
          <Button  href="/signin" variant="h6" noWrap>
             Logout
          </Button>
        </Toolbar>
        
      </AppBar>
     
      
    </div>
  );
}
}
