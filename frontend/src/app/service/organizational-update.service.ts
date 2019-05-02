import { Injectable } from '@angular/core';
import { Http ,Headers, RequestOptions, URLSearchParams } from "@angular/http";
import { Observable } from 'rxjs/Observable';


import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { CommonService } from './common.service';

@Injectable()
export class OrganizationalUpdateService {

  constructor(public http: Http,
              public appConstant:AppConstant , 
              public commonService:CommonService
              ){ }


  getUpdates() {
    console.log('inside OrganizationalUpdateService.getUpdates()');   
     let options = this.commonService.buildProtectedRequestHeader();
     return this.http.get(this.appConstant.SERVICE_ENDPOINT+'/updates',options)
  }

}
