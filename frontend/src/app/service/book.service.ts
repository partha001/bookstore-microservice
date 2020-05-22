import { Injectable } from '@angular/core';
//import { Http } from "@angular/http";
import { HttpClient } from "@angular/common/http"
import { CommonService} from "../service/common.service";

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';
import { Observable } from 'rxjs';
//import { query } from '@angular/core/src/render3/instructions';

@Injectable()
export class BookService {

  //constructor(public http: Http,public appConstant:AppConstant, public commonService: CommonService ) { }
  constructor(public httpClient: HttpClient,public appConstant:AppConstant, public commonService: CommonService ) { }

  getAllBooks() {
    console.log('inside BookService.getAllBooks()');
    let options = this.commonService.buildProtectedRequestHeader();
    return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+'/productService/books',options);
  }



  getBooks(itemsPerPage: number , currentPage:number) : Observable<any> {
    
    var payload ={
      "currentPage": currentPage,
      "itemsPerPage": itemsPerPage
    };

    let options = this.commonService.buildProtectedRequestHeader();
    return this.httpClient.post(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/searchBook',payload,options);
  }

  getBooksPages(itemsPerPage:number) : Observable<any> {
    console.log('inside BookService.getBooksPages()');
    let options = this.commonService.buildProtectedRequestHeader();
    var queryString = '?itemsPerPage='+itemsPerPage;
    return this.httpClient.get(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/books/pageCount'+queryString,options);
  }

  getCartItems(userid:number){
    console.log('inside BookService.getcartItems()');
    let options = this.commonService.buildProtectedRequestHeader();
    return this.httpClient.get(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems/'+userid,options);
  }


  addToCart(userid:number , bookid:number , quantity:number) : Observable<any>{
    console.log('inside BookService.addToCart()');
    let options = this.commonService.buildProtectedRequestHeader();

    let payload = {
      "bookId": bookid,
      "quantity": quantity,
      "userId": userid
    };
    return this.httpClient.post(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems',payload,options);
  }

  deleteItemFromCart(cartItemId:number) : Observable<any> {
    console.log('inside BookService.deleteItemFromCart()');
    let options = this.commonService.buildProtectedRequestHeader();   
    return this.httpClient.delete(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems/'+cartItemId,options);
  }

  updateCartItem(cartItemId:number, quantity:number) : Observable<any>{
    console.log('inside BookService.updateCartItem()');
    let options = this.commonService.buildProtectedRequestHeader();
    let requestparam ="?quantity="+quantity;
    return this.httpClient.put(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/cartItems/'+cartItemId+requestparam,"",options);
  }

  placeOrder(userId:number) : Observable<any> {
    let options = this.commonService.buildProtectedRequestHeader();
    return this.httpClient.post(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/placeOrder/'+userId,"",options)
  }


  getOrderHistory(userId:number) : Observable<any>{
    let options = this.commonService.buildProtectedRequestHeader();
    return this.httpClient.get(this.appConstant.PRODUCT_SERVICE_ENDPOINT+'/orderHistory/'+userId,options)
  }

}

export interface BookResponse {
  id : number;
  title : string;
  author : string;
  category : string;
  availableUnits : number;
  price : number;
  status : string;
  publisher : string ;
  language : string;
  pages : number;
  isbn : string;
  format : string;
  description : string;
  publicationDate : string;
  image : string
}
