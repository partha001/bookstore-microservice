import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { Http } from "@angular/http";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class PartnerService {

  constructor(public http: Http,public appConstant:AppConstant) { }


  getPartners() {
    console.log('inside PartnerService.getPartners()');
    //return this.http.get('http://localhost:8083/partners');
    return this.http.get(this.appConstant.SERVICE_ENDPOINT+'/partners')
  }


  // getPartners(): Observable<Partners[]> {
  //   console.log('inside PartnerService.getPartners()');
  //   return Observable.create(observer => {
  //     this.http.get('http://localhost:8083/partners')
  //       .map(response => {
  //         console.log(response);
  //         return response.json();
  //       });
  // });
  // }


  // public getPartners(){
  //   console.log('inside partner service');
  //   this.http.get('http://localhost:8083/partners')
  //         .subscribe(response =>{
  //           console.log(response);
  //         });    
  // }



  // return this.http.get('http://localhost:8083/partners')
  // .map(this.extractData())
  // .catch(this.handleError);
  //}

  // getData(): Observable<Post[]> {
  //   return this.http.get('http://jsonplaceholder.typicode.com/posts/')
  //     .map(this.extractData)
  //     .catch(this.handleError);
  // }

  // private extractData(res: Response) {
  //   let body = res.json();
  //   return body || [];
  // }

  // private handleError(error: any) {
  //   // In a real world app, we might use a remote logging infrastructure
  //   // We'd also dig deeper into the error to get a better message
  //   let errMsg = (error.message) ? error.message :
  //     error.status ? `${error.status} - ${error.statusText}` : 'Server error';
  //   console.error(errMsg); // log to console instead
  //   return Observable.throw(errMsg);
  // }


}
