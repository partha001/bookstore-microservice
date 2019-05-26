import { Injectable } from '@angular/core';
import { Http } from "@angular/http";
import { CommonService} from "../service/common.service";

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { query } from '@angular/core/src/render3/instructions';

@Injectable()
export class BookService {

  constructor(public http: Http,public appConstant:AppConstant, public commonService: CommonService ) { }

  getAllBooks() {
    console.log('inside BookService.getAllBooks()');
    let options = this.commonService.buildProtectedRequestHeader();
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+'/productService/books',options);
  }

  getBooks(itemsPerPage: number , currentPage:number){
    
    var payload ={
      "currentPage": currentPage,
      "itemsPerPage": itemsPerPage
    };

    let options = this.commonService.buildProtectedRequestHeader();
    return this.http.post(this.appConstant.SERVICE_ENDPOINT_API+'/productService/searchBook',payload,options);
  }

  getBooksPages(itemsPerPage:number){
    console.log('inside BookService.getBooksPages()');
    let options = this.commonService.buildProtectedRequestHeader();
    var queryString = '?itemsPerPage='+itemsPerPage;
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+'/productService/books/pageCount'+queryString,options);
  }


}
