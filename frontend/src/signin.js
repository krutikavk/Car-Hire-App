// import React, { Component } from 'react';

// import axios from 'axios';
// import cookie from 'react-cookies';
// import { Redirect } from 'react-router';

// //Define a Login Component
// class Login extends Component {
//     //call the constructor method
//     constructor(props) {
//         //Call the constructor of Super class i.e The Component
//         super(props);
//         //maintain the state required for this component
//         this.state = {
//             username: "",
//             password: "",
//             authFlag: false,
//             message: ""
//         }
//         //Bind the handlers to this class
//         this.usernameChangeHandler = this.usernameChangeHandler.bind(this);
//         this.passwordChangeHandler = this.passwordChangeHandler.bind(this);
//         this.submitLogin = this.submitLogin.bind(this);
//     }
//     //Call the Will Mount to set the auth Flag to false
//     componentWillMount() {
//         this.setState({
//             authFlag: false
//         })
//     }
//     //username change handler to update state variable with the text entered by the user
//     usernameChangeHandler = (e) => {
//         this.setState({
//             username: e.target.value
//         })
//     }
//     //password change handler to update state variable with the text entered by the user
//     passwordChangeHandler = (e) => {
//         this.setState({
//             password: e.target.value
//         })
//     }
//     //submit Login handler to send a request to the node backend
//     submitLogin = (e) => {
//         //prevent page from refresh
//         e.preventDefault();
//         const data = {
//             username: this.state.username,
//             password: this.state.password
//         }
//         //set the with credentials to true
//         axios.defaults.withCredentials = true;
//         //make a post request with the user data
//         axios.post('http://localhost:3001/login', data)
//             .then(response => {
//                 this.setState({
//                     authFlag: true
//                 });
//             })
//             .catch(error => {
//                 this.setState({
//                     message: error.response.data
//                 })
//             });

//     }

//     render() {
//         //redirect based on successful login
//         let redirectVar = null;
//         if (cookie.load('cookie')) {
//             redirectVar = <Redirect to="/home" />
//         }
//         return (
//             <div>
//                 {redirectVar}
//                 <div class="container">
//                     <div class="login-form">
//                         <div class="main-div">
//                             <div class="panel">
//                                 <h2>Admin Login</h2>
//                                 <p>Please enter your username and password</p>
//                             </div>
//                             <form onSubmit={this.submitLogin}>
//                                 <div style={{ color: "#ff0000" }}>{this.state.message}</div>
//                                 <div class="form-group">
//                                     <input onChange={this.usernameChangeHandler} type="text" class="form-control" required name="username" placeholder="Username" />
//                                 </div>
//                                 <div class="form-group">
//                                     <input onChange={this.passwordChangeHandler} type="password" class="form-control" required name="password" placeholder="Password" />
//                                 </div>
//                                 <button type="submit" class="btn btn-primary">Login</button>
//                             </form>
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         )
//     }
// }
// //export Login Component
// export default Login;


import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" href="https://material-ui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignIn() {
  const classes = useStyles();

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
        <form className={classes.form} noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
          />
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="/signup" variant="body2">
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
      
    </Container>
  );
}