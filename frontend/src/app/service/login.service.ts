import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class LoginService {

  constructor(public http: Http, public appConstant: AppConstant) { }

  public login(postData){
    console.log('inside LoginService.login()');
    // console.log(JSON.stringify(postData));

    let options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Accept', 'application/json');
    options.headers.append('Content-Type', 'application/x-www-form-urlencoded');

    //return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', JSON.stringify(postData), options);
    return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/login', postData, options);
  }

}
