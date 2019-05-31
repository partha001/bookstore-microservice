import { Component, OnInit } from '@angular/core';
import {OrganizationalUpdateService } from '../../service/organizational-update.service';
import {LoginService} from '../../service/login.service'


@Component({
  selector: 'user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(public updateService: OrganizationalUpdateService , 
              public loginService: LoginService) { 
   this.getUpdates();
   this.saveUserId();
  }

  ngOnInit() {
  }


  updates: any[];


  getUpdates() {
    console.log('inside UserHomeComponent.getUpdates()')
    this.updateService.getUpdates().subscribe(response =>{
      this.updates= response.json();
    });
  }


  saveUserId(){
    console.log('inside saveUserId()');
    //let user =localStorage.getItem("currentUser");
    //console.log((JSON.parse(localStorage.getItem('currentUser'))).principal.username)
    let username = (JSON.parse(localStorage.getItem('currentUser'))).principal.username;
    
    this.loginService.getUserIdFromUsername(username)
    .subscribe(response => {
                 console.log(response);
                 let currentUserWithUserID = response.json();
                 if(currentUserWithUserID){
                      // store user details  in local storage to keep user logged in between page refreshes
                       localStorage.setItem('currentUserWithUserID', JSON.stringify(currentUserWithUserID));
                 }
                // this.router.navigate(['/home']);
               },
               () => {
                console.log('completed successfully!');
               });

  }
  
}
