import { Injectable } from '@angular/core';
import { Http } from "@angular/http";

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class BookService {

  constructor(public http: Http,public appConstant:AppConstant) { }

  getAllBooks() {
    console.log('inside BookService.getAllBooks()');
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+'/productService/books')
  }


}
