import { Injectable } from '@angular/core';
//import { Http } from "@angular/http";
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class PartnerService {

  constructor(//public http: Http,
    public httpClient : HttpClient,
    public appConstant:AppConstant) { }


  getPartners() {
    console.log('inside PartnerService.getPartners()');
    //return this.http.get('http://localhost:8083/partners');
    return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT+'/partners')
  }


}
