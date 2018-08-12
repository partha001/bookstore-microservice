import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
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

