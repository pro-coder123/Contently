import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { user } from './user';
import { userReg } from './userReg';
const httpOptions = {
headers: new HttpHeaders({'Content-Type':'application/json'})
};
@Injectable({
 providedIn: 'root'
})
export class LoginService {
 constructor(private http:HttpClient) { }
 authenticateUser(checkUser: user):any{
   let post_url = `http://localhost:8080/user-management/api/v1/user/login`;
   return this.http.post(post_url,checkUser,httpOptions);
 }

 registerUser(regUser: userReg):any{
  let post_url = `http://localhost:8080/user-management/api/v1/user/register`;
  return this.http.post(post_url,regUser,httpOptions);
}
}