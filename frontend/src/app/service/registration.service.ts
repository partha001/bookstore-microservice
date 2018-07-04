import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
//import { Http,Headers, RequestOptions } from "@angular/http";
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, RequestOptions } from '@angular/http';

//import {Headers, RequestOptions} from 'angular2/http';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class RegistrationService {

  constructor(public http: Http, public appConstant: AppConstant) { }

  register(postData) {
    console.log('inside RegistrationService.register()');
    console.log(JSON.stringify(postData));

    let options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Content-Type', 'application/json');
    options.headers.append('Accept', 'application/json');
    options.headers.append('Key1', 'value1');

    return this.http.post(this.appConstant.SERVICE_ENDPOINT_API + '/userService/users/register', JSON.stringify(postData), options);
  }


  checkUsernameAvailability(username){
    console.log('inside RegistrationService.checkUsernameAvailability()');
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+ '/userService/users/checkUsernameAvailability?username='+username);
  }

}
