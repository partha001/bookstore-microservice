import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class LogoutService {

  constructor(public http: Http, public appConstant: AppConstant) { }

  public logout(){
    return this.http.post(this.appConstant.SERVICE_ENDPOINT + '/logout',{})
  }

}

