import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { User } from '../model/model.user';
//import { HttpParams, HttpClient } from '@angular/common/http';

@Injectable()
export class LoginService {

  constructor(public http: Http, public appConstant: AppConstant) { }

  
  public login(user: User) {
    console.log('inside LoginService.login1()');

    //code for basic authentication start here
    let headers = new Headers();
    headers.append('Accept', 'application/json')
    // creating base64 encoded String from user name and password
    var base64Credential: string = btoa( user.username+ ':' + user.password);
    headers.append("Authorization", "Basic " + base64Credential);

    let options = new RequestOptions();
    options.headers=headers;

    return this.http.get(this.appConstant.SERVICE_ENDPOINT + '/home' ,   options)



//    //code with form login using form data start here
    // console.log('checking with formdata');
    // let body :FormData = new FormData(); 
    //  body.append('username', user.username); 
    //  body.append('password', user.password); 

    //  let option = new RequestOptions();
    // option.headers = new Headers();
    //  option.headers.append('Accept', 'application/json');
    //  option.headers.append('Content-Type', 'multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW');
    // option.headers.append('Content-Type', 'application/x-www-form-urlencoded');
    // return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', body);


//  //commented code starts here
    //console.log('checking with postdata')
    // //code for working with json post data
    //   let postdata = {
    //     username: user.username,
    //     password: user.password
    //   };
    //   console.log(postdata);
    //   let options = new RequestOptions();
    //   options.headers = new Headers();
    //   options.headers.append('Accept', 'application/json');
    //   //options.headers.append('Content-Type', 'multipart/form-data');
    //   options.headers.append('Content-Type', 'application/x-www-form-urlencoded');
    //   //return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', JSON.stringify(postData), options);
    //   return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', postdata, options);


      // let options = new RequestOptions();
      // options.headers = new Headers();
      // options.headers.append('Accept', 'application/json');
      // var body = 'username='+user.username+'&password='+user.password;
      // return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', body, options);


    //   return this.http.get(this.appConstant.SERVICE_ENDPOINT + '/login', body)
    //   .map((response: Response) => {
    //   // login successful if there's a jwt token in the response
    //   let user = response.json().principal;// the returned user object is a principal object
    //   if (user) {
    //     // store user details  in local storage to keep user logged in between page refreshes
    //     localStorage.setItem('currentUser', JSON.stringify(user));
    //   }
    // });

    //   //let params = new URLSearchParams();
    // // params.set('username', user.username);
    // // params.set('password', user.password);
    // // option.params = params;

    //  return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', body);
//  //commented code end here



   

  }


}

