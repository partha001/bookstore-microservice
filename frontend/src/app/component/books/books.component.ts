import { Component, OnInit } from '@angular/core';
import { BookService } from '../../service/book.service';

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  constructor(public bookService: BookService) {
    this.getAllBooks();
   }
   

  ngOnInit() {
  }


  books: any[];

  getAllBooks() {
    console.log('inside BookComponent.getAllBooks()')
    this.bookService.getAllBooks().subscribe(response =>{
      this.books= response.json();
      console.log(this.books);
    });
  }

}

