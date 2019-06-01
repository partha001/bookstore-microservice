import { Component, OnInit } from '@angular/core';
import { BookService } from '../../service/book.service';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  totalPages:number;
  pages: number[];
  books: any[];

  constructor(public bookService: BookService  ) {
    //this.getAllBooks();
    this.getBooks(10,1);
    this.getBooksPages();
   }
   

  ngOnInit() {
  }



  getAllBooks() {
    console.log('inside BookComponent.getAllBooks()')
    this.bookService.getAllBooks().subscribe(response =>{
      this.books= response.json();
      console.log(this.books);
    });
  }


  getBooks(itemsPerPage:number , currentPage:number) {
    console.log('inside BookComponent.getBooks()')
    this.bookService.getBooks(itemsPerPage,currentPage).subscribe(response =>{
      this.books= response.json();
      console.log(this.books);
    });
  }

  getBooksPages(){
    console.log('inside BookComponent.getBooksPages()');
    //let totalPage:number;
    this.bookService.getBooksPages(10).subscribe(response =>{
      //this.books= response.json();
      this.pages = response.json();
      console.log('total pages'+ this.totalPages);
      // for(var i=1;i<=this.totalPages;i++){
      //   console.log(i);
      //   //this.pages[i-1]=i;
      //   //this.pages.push(i);
      // }
     // this.pages = [...Array(this.totalPages)].map((_,i) => i);
    });
  }

  gotoPage(event){
    console.log("page change request logged");
    console.log(event.target.attributes.value);
    let currentPage:number=event.target.value;
    console.log('requested page'+currentPage);
    this.getBooks(10,currentPage);
  }

  addToCart(event){
    //console.log(  $(event.target));

   //console.log($(event.target).closest('tr').find('td:first').text());
    let bookid = $(event.target).closest('tr').find('td:first').text();

    //console.log($(event.target).closest('tr').find("select").children("option:selected").val());
    let quantity = $(event.target).closest('tr').find("select").children("option:selected").val();

    let userid : number = (JSON.parse(localStorage.getItem('currentUserWithUserID'))).id;

    this.bookService.addToCart(userid, bookid, quantity).subscribe(response =>{
      console.log('response received');
    })
   
  }

}

