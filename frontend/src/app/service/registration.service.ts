import { Injectable } from '@angular/core';
//import { Http,Headers, RequestOptions } from "@angular/http";
import { HttpHeaders, HttpClient , HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
//import { Http, Headers, RequestOptions } from '@angular/http';
//import { HttpClient   } from '@angular/common/http';

//import {Headers, RequestOptions} from 'angular2/http';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class RegistrationService { 

  constructor(public httpClient: HttpClient, public appConstant: AppConstant) { }

  
  // register(postData) {
  //   console.log('inside RegistrationService.register()');
  //   console.log(JSON.stringify(postData));

  //   let options = new RequestOptions();
  //   options.headers = new Headers();
  //   options.headers.append('Content-Type', 'application/json');
  //   options.headers.append('Accept', 'application/json');
  //   options.headers.append('Key1', 'value1');

  //   return this.httpClient.post(this.appConstant.SERVICE_ENDPOINT_API + '/userService/users/register', JSON.stringify(postData), options);
  // }


  // register(postData) : boolean {
  //     console.log('inside RegistrationService.register()');
  //     console.log(JSON.stringify(postData));
  //     let httpOptions :any = {
  //       headers: new HttpHeaders({
  //         'Content-Type': 'application/json',
  //         'Accept': 'application/json',
  //         'Access-Control-Allow-Credentials': 'true',
  //         'key1': 'value1'
  //       }),
  //       observe: 'response',
  //       withCredentials: true,
  //       responseType: 'json'
  //     } 

  //     let result  : boolean;
  //     this.httpClient.post(this.appConstant.SERVICE_ENDPOINT_API + '/userService/users/register', JSON.stringify(postData),httpOptions)
  //           .subscribe((response: any) => {
  //             let  httpResponse : HttpResponse<any> = response;
  //             console.log(httpResponse.status);
  //             if(httpResponse.status==201){
  //                 console.log("inside if");
  //                 result = true ;
  //             }else{
  //               console.log("inside else");
  //                 result = false;
  //             }
  //     },
  //     error => { console.log("some error occurred");
  //             result = false ;
  //     },
  //     () => { console.log("post request complete") });

  //     console.log("before");
  //     console.log(result);
  //     console.log("after");
  //     return result;
  //   }

  
  register(postData) : Observable<any> {
    console.log('inside RegistrationService.register()');
    console.log(JSON.stringify(postData));
    let httpOptions :any = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Credentials': 'true',
        'key1': 'value1'
      }),
      observe: 'response',
      withCredentials: true,
      responseType: 'json'
    }
    let result  : boolean;
    return this.httpClient.post(this.appConstant.SERVICE_ENDPOINT_API + '/userService/users/register', JSON.stringify(postData),httpOptions);
  }


  checkUsernameAvailability(username){
    console.log('inside RegistrationService.checkUsernameAvailability()');
    return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+ '/userService/users/checkUsernameAvailability?username='+username);
  }

}
