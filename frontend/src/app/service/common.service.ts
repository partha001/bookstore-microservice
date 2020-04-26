import { Injectable } from '@angular/core';
//import { Http ,Headers, RequestOptions, URLSearchParams } from "@angular/http";
import { HttpClient , HttpHeaders } from "@angular/common/http"

@Injectable()
export class CommonService {

  constructor(public httpClient: HttpClient) { }

  // public buildProtectedRequestHeader(){
  //   let options = new RequestOptions();
  //   options.headers = new Headers();
  //   options.headers.append('Content-Type', 'application/json');
  //   options.headers.append('Accept', 'application/json');
  //   options.headers.append('Access-Control-Allow-Credentials','true');
  //   options.withCredentials = true;
  //   options.headers.append('Authorization', "Bearer " + (JSON.parse(localStorage.getItem('currentUser'))).token);
  //   return options;
  // }


  public buildProtectedRequestHeader(): any {
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Content-Type': 'application/json',
    //     'Accept': 'application/json',
    //     'Access-Control-Allow-Credentials':'true',
    //   }),
    //   withCredentials: true ,
    //   responseType: 'text'
    //  };  
    //  httpOptions.headers.append('Authorization', "Bearer " + (JSON.parse(localStorage.getItem('currentUser'))).token);

     let httpOptions :any = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Credentials': 'true',
        'Authorization': 'Bearer ' + (JSON.parse(localStorage.getItem('currentUser'))).token,
        'key1': 'value1'
      }),
      observe: 'response',
      withCredentials: true,
      responseType: 'json'
    }
    return httpOptions;
  }


}
