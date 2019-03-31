import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { CommonService } from './common.service';

@Injectable()
export class LogoutService {

  constructor(public http: Http, 
              public appConstant: AppConstant,
              public commonService: CommonService) { }

  public logout(){
    let options = this.commonService.buildProtectedRequestHeader();
    return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/userLogout', null , options)
  }

}

