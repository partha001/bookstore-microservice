import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs/Observable';


import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { CommonService } from './common.service';

@Injectable()
export class OrganizationalUpdateService {

  constructor(public httpClient: HttpClient,
              public appConstant:AppConstant , 
              public commonService:CommonService
              ){ }


  getUpdates() : any {
    console.log('inside OrganizationalUpdateService.getUpdates()');   
     let options = this.commonService.buildProtectedRequestHeader();
     return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT+'/updates',options)
  }

}
