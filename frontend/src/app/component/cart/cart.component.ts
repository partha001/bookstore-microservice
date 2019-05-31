import { Component, OnInit } from '@angular/core';
import {BookService} from '../../service/book.service'

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  private cartItems : any[];

  constructor(public bookService: BookService) { }

  ngOnInit() {
    this.getCartItems();
  }

  getCartItems(){
    let userid : number = (JSON.parse(localStorage.getItem('currentUserWithUserID'))).id;
    this.bookService.getCartItems(userid).subscribe(response =>{
      this.cartItems= response.json();
      console.log(this.cartItems);
    });
  }

}
