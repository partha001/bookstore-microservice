import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { Http } from "@angular/http";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class BookService {

  constructor(public http: Http,public appConstant:AppConstant) { }

  getAllBooks() {
    console.log('inside BookService.getAllBooks()');
    //return this.http.get('http://localhost:8083/partners');
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+'/productService/books')
  }


}
