import { Component, OnInit } from '@angular/core';
import {OrganizationalUpdateService } from '../../service/organizational-update.service';
import {LoginService} from '../../service/login.service'
import { HttpResponse } from '@angular/common/http';


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
    this.updateService.getUpdates().subscribe((response : any )=>{
      let httpResponse : HttpResponse<any> = response ;
      this.updates= httpResponse.body;
    },
    error => {console.log("some error occurred")},
    ()=>{});
  }


  saveUserId(){
    console.log('inside saveUserId()');
    //let user =localStorage.getItem("currentUser");
    //console.log((JSON.parse(localStorage.getItem('currentUser'))).principal.username)
    let username = (JSON.parse(localStorage.getItem('currentUser'))).principal.username;
    
    this.loginService.getUserIdFromUsername(username)
    .subscribe((response :any )=> {
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
