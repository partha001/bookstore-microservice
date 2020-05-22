import { Component, OnInit } from '@angular/core';
import {BookService} from '../../service/book.service';
import { HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrderComponent implements OnInit {

  orders : Order[];

  constructor(public bookService : BookService) {}

  ngOnInit(): void {
    this.getOrderHistory();
  }

  getOrderHistory(){
    let userid : number = (JSON.parse(localStorage.getItem('currentUser'))).id;
    this.bookService.getOrderHistory(userid).subscribe( (response:any) => {
      let httpResponse : HttpResponse<any> = response ;
      //this.orders = JSON.parse(httpResponse.body).orders;
      // console.log("response received");
      // console.log(httpResponse.body.orders);
      this.orders = httpResponse.body.orders;
      console.log(this.orders);
    },
    error => {
      console.log("some error");
    })
  }

}

export interface Order {
  orderId : number;
  orderDate : string;
  deliverAddress : string;
  totalAmount : number;
  orderDetails : OrderDetail[];
}

export interface OrderDetail {
  id : number;
  bookId : number;
  quantity : number;
  title : string;
  author : string;
  price : number;
  image : string;
  description : string;
}


