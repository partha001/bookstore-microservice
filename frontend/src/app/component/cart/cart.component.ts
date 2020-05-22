import { Component, OnInit } from '@angular/core';
import {BookService} from '../../service/book.service';
import { HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';

declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

   public cartItems : any[];

  constructor(public bookService: BookService,
              public toastrService: ToastrService) { }

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
    let userid : number = (JSON.parse(localStorage.getItem('currentUser'))).id;
    this.bookService.getCartItems(userid).subscribe((response : any )=>{
      let httpResponse : HttpResponse<any> = response
      this.cartItems= httpResponse.body;
      console.log(this.cartItems);
    });
  }

  deleteItemFromCart(event){
    console.log($(event.target).closest('tr').find('td:first').text());
    let cartItemid = $(event.target).closest('tr').find('td:first').text();
    this.bookService.deleteItemFromCart(cartItemid).subscribe(response =>{
      let httpResponse : HttpResponse<any> = response;
      if(httpResponse.status==200){
        console.log('item delete successfully');
        this.toastrService.success("item updated successfully!", "",  {positionClass : "toast-top-center"});
        $(event.target).closest('tr').hide();
      }else{
        console.log('item deletion failed');
      }
    });
  }

  updateCartItem(event){
    //console.log($(event.target).closest('tr').find('td:first').text());
    let cartItemid = $(event.target).closest('tr').find('td:first').text();

    //console.log($(event.target).closest('tr').find("select").children("option:selected").val());
    let quantity = $(event.target).closest('tr').find("select").children("option:selected").val();

    this.bookService.updateCartItem(cartItemid,quantity)
                    .subscribe(response =>{
                      let httpResponse : HttpResponse<any> = response;
                      if(httpResponse.status==200){
                        console.log("updated successfully");
                        this.toastrService.success("Change updated successfully!", "",  {positionClass : "toast-top-center"});           
                      }else{
                        console.log("updated failed");
                        this.toastrService.error("Change updation failed. Please try later", "",  {positionClass : "toast-top-center"});           
                      }
                    },
                    error => { console.log("some error occurred");
                      this.toastrService.error("Change updation failed. Please try later", "",  {positionClass : "toast-top-center"});
                    },
                    () => {});

  }

  placeOrder(event){
    console.log("CartComponent.placeOrder() :: start")
    let userid : number = (JSON.parse(localStorage.getItem('currentUser'))).id;
    this.bookService.placeOrder(userid).subscribe((response : any )=>{
      let httpResponse : HttpResponse<any> = response;
      // this.cartItems= httpResponse.body;
      // console.log(this.cartItems);
      if(httpResponse.status==200){
        //this.cartItems = [{}];
        this.cartItems.splice(0,this.cartItems.length);
        this.toastrService.success("Order placed successfully!", "",  {positionClass : "toast-top-center"});
      }else{
        this.toastrService.warning("Error occurred.Please try later!", "",  {positionClass : "toast-top-center"});
      }
    },
    error => {
      this.toastrService.warning("Error occurred.Please try later!", "",  {positionClass : "toast-top-center"});
    });
  }


}
