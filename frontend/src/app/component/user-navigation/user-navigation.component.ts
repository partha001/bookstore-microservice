import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import {Router} from "@angular/router";
import { LogoutService } from '../../service/logout.service';

@Component({
  selector: 'app-user-navigation',
  templateUrl: './user-navigation.component.html',
  styleUrls: ['./user-navigation.component.css']
})
export class UserNavigationComponent implements OnInit {

  constructor( public router: Router, private logoutService: LogoutService) { }

  ngOnInit() {
  }

  public logout(){
    console.log("logout called");
    this.logoutService.logout().subscribe(

    response => {
      console.log('local storage cleared');
      localStorage.removeItem('currentUser');
      localStorage.removeItem('currentUserWithUserID');
    },
    () => {
      localStorage.removeItem('currentUser');
      localStorage.removeItem('currentUserWithUserID');
      console.log('completed successfully');
    }
  );

    this.router.navigate(['']);
  }

}
