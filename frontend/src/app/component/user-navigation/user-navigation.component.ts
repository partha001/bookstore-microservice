import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-navigation',
  templateUrl: './user-navigation.component.html',
  styleUrls: ['./user-navigation.component.css']
})
export class UserNavigationComponent implements OnInit {

  constructor( public router: Router) { }

  ngOnInit() {
  }

  public logout(){
    console.log("logout called");
    this.router.navigate(['/']);
  }

}
