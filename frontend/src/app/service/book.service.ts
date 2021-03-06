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
    return this.http.post(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/searchBook',payload,options);
  }

  getBooksPages(itemsPerPage:number){
    console.log('inside BookService.getBooksPages()');
    let options = this.commonService.buildProtectedRequestHeader();
    var queryString = '?itemsPerPage='+itemsPerPage;
    return this.http.get(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/books/pageCount'+queryString,options);
  }

  getCartItems(userid:number){
    console.log('inside BookService.getcartItems()');
    let options = this.commonService.buildProtectedRequestHeader();
    return this.http.get(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems/'+userid,options);
  }


  addToCart(userid:number , bookid:number , quantity:number){
    console.log('inside BookService.addToCart()');
    let options = this.commonService.buildProtectedRequestHeader();

    let payload = {
      "bookId": bookid,
      "quantity": quantity,
      "userId": userid
    };
    return this.http.post(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems',payload,options);
  }

  deleteItemFromCart(cartItemId:number){
    console.log('inside BookService.deleteItemFromCart()');
    let options = this.commonService.buildProtectedRequestHeader();   
    return this.http.delete(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems/'+cartItemId,options);
  }

  updateCartItem(cartItemId:number, quantity:number){
    console.log('inside BookService.updateCartItem()');
    let options = this.commonService.buildProtectedRequestHeader();
    let requestparam ="?quantity="+quantity;
    return this.http.put(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems1/'+cartItemId+requestparam,"",options);
  }

}
