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
    console.log('inside OrganizationalUpdateService.getPartners()');
    //return this.http.get('http://localhost:8083/partners');

    // this.headers = new Headers();
    // this.headers.append('Content-Type', 'application/x-www-form-urlencoded');
    // this.headers.append('Access-Control-Allow-Origin', '');
    // this.headers.append('Access-Control-Allow-Headers', '');
    // this.headers.append('Access-Control-Allow-Methods', '*');
    // this.headers.append('Access-Control-Allow-Credentials', 'true');

    let headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Credentials', 'true');
    let options = new RequestOptions();
    options.headers=headers;
    options.withCredentials=true;

    return this.http.get(this.appConstant.SERVICE_ENDPOINT+'/updates',options)
  }


}
