import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { Http ,Headers, RequestOptions, URLSearchParams } from "@angular/http";
import { Observable } from 'rxjs/Observable';


import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class OrganizationalUpdateService {

  constructor(public http: Http,public appConstant:AppConstant) { }


  getUpdates() {
    console.log('inside OrganizationalUpdateService.getUpdates()');
   
    let options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Content-Type', 'application/json');
    options.headers.append('Accept', 'application/json');
    options.headers.append('Access-Control-Allow-Credentials','true');
    options.withCredentials = true;
    options.headers.append('Authorization', "Bearer " + (JSON.parse(localStorage.getItem('currentUser'))).token);
    return this.http.get(this.appConstant.SERVICE_ENDPOINT+'/updates',options)
  }

}
