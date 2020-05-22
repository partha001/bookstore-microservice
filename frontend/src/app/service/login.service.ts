import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient   } from '@angular/common/http';
//import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {CommonService} from '../service/common.service'

import 'rxjs/add/operator/map';

import { AppConstant } from './app-constant.service';
import { User } from '../model/model.user'; 
import { Observable } from 'rxjs';

@Injectable()
export class LoginService {

  constructor(public httpClient: HttpClient, 
              public appConstant: AppConstant ,
              public commonService : CommonService ) { }

  
  // public login(user: User) {
  //   console.log('inside LoginService.login()');

  //   //alternative way of making the post call using Http from @angular/http
  //   var base64Credential: string = btoa( user.username+ ':' + user.password);
  //   let options = new RequestOptions();
  //   options.headers = new Headers();
  //   options.headers.append('Content-Type', 'application/json');
  //   options.headers.append('Accept', 'application/json');
  //   options.headers.append('Authorization', "Basic " + base64Credential);
  //   options.headers.append('Access-Control-Allow-Credentials','true');
  //   options.withCredentials = true;
  //   options.responseType = ResponseContentType.Json;

  //   return this.http.post(this.appConstant.SERVICE_ENDPOINT+"/login", null , options);

  //   //   // making the post call using HttpClient from HttpClient
  //   //   //    for basic authentication
  //   //   var base64Credential: string = btoa( user.username+ ':' + user.password);
  //   //   const httpOptions = {
  //   //     headers: new HttpHeaders({
  //   //       'Accept': 'application/json',
  //   //       "Authorization": "Basic " + base64Credential
  //   //     }),
  //   //     withCredentials: true ,
  //   //     responseType: 'text'
  //   //    };
  //   //  return this.http.post("http://localhost:8083/login",  httpOptions)
  //   //   .subscribe(res => {
  //   //           console.log(res);
  //   //         });

  //         }

    public login(user: User) : Observable<any> {
      console.log('inside LoginService.login()');
        var base64Credential: string = btoa( user.username+ ':' + user.password);
        console.log(base64Credential);
        // const httpOptions = {
        //   headers: new HttpHeaders({
        //     'Accept': 'application/json',
        //     'Authorization': "Basic " + base64Credential,
        //     'Access-Control-Allow-Credentials':'true',
        //   }),
        //   withCredentials: true ,
        //   responseType: 'json'
        //  };
        let httpOptions :any = {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Access-Control-Allow-Credentials': 'true',
            'Authorization': 'Basic ' + base64Credential,
            'key1': 'value1'
          }),
          observe: 'response',
          withCredentials: true,
          responseType: 'json'
        }


       return this.httpClient.post("http://localhost:8083/login","",httpOptions);
    }


    getUserIdFromUsername(username: String) : Observable<any>{          
        console.log('inside OrganizationalUpdateService.getUpdates()');   
          let options = this.commonService.buildProtectedRequestHeader();
          return this.httpClient.get(this.appConstant.USER_SERVICE_ENDPOINT+'/users/findByUsername/'+username,options)
    }

}
        

 // //code for basic authentication start here
    // let headers = new Headers();
    // headers.append('Accept', 'application/json')
    // // creating base64 encoded String from user name and password
    // var base64Credential: string = btoa( user.username+ ':' + user.password);
    // headers.append("Authorization", "Basic " + base64Credential);
    // //headers.append('Access-Control-Allow-Credentials', 'true');
    // let options = new RequestOptions();
    // options.headers=headers;
    // //options.withCredentials=true;
    // return this.http.get(this.appConstant.SERVICE_ENDPOINT + '/home',options)

    
    // let options = new RequestOptions();
    // options.headers=headers;
    // options.withCredentials=true;



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



   


