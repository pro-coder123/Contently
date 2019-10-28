import { Component, OnInit, Output } from '@angular/core';
import { LoginService } from '../login.service';
import { user } from '../user';
import { EventEmitter } from 'events';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  router: Router;
  public fail: Boolean;

  constructor(
     private _loginService: LoginService,_router: Router
    ) { 
      this.router = _router;
    }

  ngOnInit() {
    this.fail = false;
  }
  public tokenObject:any;

 // @Output
 //public event = new EventEmitter();


  public authenticate(username, password){
    const checkUser: user = new user();
    checkUser.username = username;
    checkUser.password = password;
    this._loginService.authenticateUser(checkUser).subscribe(result => {
      this.tokenObject = result;
      if(this.tokenObject.message == "Username/Password is invalid"){
        this.fail = true;
      }else{
        this.router.navigate(['/dashboard']);
      }
    }
   );  
  }

}
