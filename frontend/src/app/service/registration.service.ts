import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { Http,Headers, RequestOptions } from "@angular/http";
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
////import {Headers, RequestOptions} from 'angular2/http';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class RegistrationService {

  constructor(public http: Http,public appConstant:AppConstant) { }

  register(postData) {
    console.log('inside RegistrationService.register()');
    console.log(JSON.stringify(postData));

    //let headers = new HttpHeaders();
   //let headers = new Headers({ 'Content-Type': 'application/json' });
  //  headers.append('Content-Type', 'application/json');
  //   headers.append('Accept', 'application/json');
  //   let options = new RequestOptions({ headers: headers });

    return this.http.post(this.appConstant.SERVICE_ENDPOINT_API+'/userService/users/register', JSON.stringify(postData));
  }

}
