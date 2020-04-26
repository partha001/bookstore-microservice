import { Component, OnInit } from '@angular/core';
import {BookService} from '../../service/book.service';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

   public cartItems : any[];

  constructor(public bookService: BookService) { }

  ngOnInit() {
    this.getCartItems();
  }

  // getCartItems(){
  //   let userid : number = (JSON.parse(localStorage.getItem('currentUserWithUserID'))).id;
  //   this.bookService.getCartItems(userid).subscribe(response =>{
  //     this.cartItems= response.json();
  //     console.log(this.cartItems);
  //   });
  // }
  getCartItems(){
    let userid : number = (JSON.parse(localStorage.getItem('currentUserWithUserID'))).id;
    this.bookService.getCartItems(userid).subscribe((response : any )=>{
      this.cartItems= response.json();
      console.log(this.cartItems);
    });
  }

  deleteItemFromCart(event){
    //console.log($(event.target).closest('tr').find('td:first').text());
    let cartItemid = $(event.target).closest('tr').find('td:first').text();
    this.bookService.deleteItemFromCart(cartItemid).subscribe(response =>{
      console.log('item delete successfully');
    });
  }

  updateCartItem(event){
    //console.log($(event.target).closest('tr').find('td:first').text());
    let cartItemid = $(event.target).closest('tr').find('td:first').text();

    //console.log($(event.target).closest('tr').find("select").children("option:selected").val());
    let quantity = $(event.target).closest('tr').find("select").children("option:selected").val();

    this.bookService.updateCartItem(cartItemid,quantity)
                    .subscribe(response =>{
                      console.log("updated successfully");
                    })

  }
}
