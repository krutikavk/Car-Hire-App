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
import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import InputBase from '@material-ui/core/InputBase';
import { fade, makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';

import SearchIcon from '@material-ui/icons/Search';
import Details from './detailspage';

const useStyles = makeStyles((theme) => ({
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

export default function Landingpage() {
  const classes = useStyles();

  return (
    <div className={classes.root}>
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
          <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div>
        </Toolbar>
      </AppBar>
      <br></br>
      <br></br>
      <Grid container className={classes.root}  spacing={20}>
      <Details/>
      <br/><Details/><Details/>
      <Details/><Details/><Details/><Details/><Details/><Details/><Details/>
      

      </Grid>
      
    </div>
  );
}