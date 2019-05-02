import { Injectable } from '@angular/core';
import { Http ,Headers, RequestOptions, URLSearchParams } from "@angular/http";

@Injectable()
export class CommonService {

  constructor(public http: Http) { }

  public buildProtectedRequestHeader(){
    let options = new RequestOptions();
    options.headers = new Headers();
    options.headers.append('Content-Type', 'application/json');
    options.headers.append('Accept', 'application/json');
    options.headers.append('Access-Control-Allow-Credentials','true');
    options.withCredentials = true;
    options.headers.append('Authorization', "Bearer " + (JSON.parse(localStorage.getItem('currentUser'))).token);
    return options;
  }


}
