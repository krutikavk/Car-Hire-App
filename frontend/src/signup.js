import React from 'react';

function Signup() {
  return (
    <div>
    
      <form>
            <h1>Welcome to Car Rental Application</h1>
            <div>
            <label>Email address</label>
                    <input type="email" placeholder="Enter email" />
            </div>
            <div>
            <label>Password</label>
                    <input type="password" placeholder="Enter password" />
            </div>
          </form> 
    
      </div>
         
  );
}

export default Signup;