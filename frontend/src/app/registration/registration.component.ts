import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { userReg } from '../userReg';
import {MatDatepickerModule} from '@angular/material/datepicker';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public registerv = false;

  constructor(private _loginService: LoginService) { }

  ngOnInit() {
    this.registerv = false;
  }

  public returnUser: any;

  public register(username, password, firstname, lastname, email, contact, gender, nationality, address1, address2, address3, dob){
    const regUser: userReg = new userReg();
    regUser.username = username;
    regUser.password = password;
    regUser.firstName = firstname;
    regUser.lastName = lastname;
    regUser.email = email;
    regUser.phoneNumber = contact;
    regUser.gender = gender;
    regUser.nationality = nationality;
    regUser.addressLine1 = address1;
    regUser.addressLine2 = address2;
    regUser.addressLine3 = address3;
    regUser.dob = dob;
    this._loginService.registerUser(regUser).subscribe( result => {
      this.returnUser = result;
      if(this.returnUser.id != null){
        this.registerv = true;      }
    });
  }
}
