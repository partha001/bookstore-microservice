import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ForgetPassword } from "../../model/model.forget-password";
import { UserService } from "../../service/user-service"
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public currentModule : string ;
  public securityDetails : SecurityDetails;
  public disablePasswordEntry : boolean ;
  public enteredPassword : string;
  public reenteredPassword : string;
  public enteredSecurityAnswer : string;

  constructor(public userService: UserService,
    public toastrService : ToastrService) {
    this.currentModule ='profileDetails';
   }

  ngOnInit() {
    
  }
  menuChange(currentMenu : string){
    this.currentModule = currentMenu;

    if(currentMenu=='changePassword'){
      this.disablePasswordEntry = true;
      this.securityDetails = new SecurityDetails();
      this.enteredPassword = "";
      this.reenteredPassword ="";
      let email : string = JSON.parse(localStorage.getItem('currentUser')).principal.username ;
      // console.log(JSON.parse(localStorage.getItem('currentUser')).principal.username);
      //this.userService.getSecurityQuestionAnswer()
      this.userService.getSecurityQuestionAnswer(email,true)
      .subscribe( (response:any) => {
        let httpResponse : HttpResponse<any> = response;
        if(httpResponse.status == 200 ){
            this.securityDetails = httpResponse.body;
          }else{
            console.log("some error");
          }
      },
      error => { 
        console.log("some error");
          //this.toastrService.error("Some error occurred.Please try later!", "",  {positionClass : "toast-top-center"});
      })
    }

    // else if(currentMenu=='changePassword'){
      
    // }
  }


  verifySecurityAnswer(){
    if(this.enteredSecurityAnswer==this.securityDetails.securityAnswer){
      this.disablePasswordEntry=false;
    }else{
      this.disablePasswordEntry=true;
      this.toastrService.error("Incorrect security answer.", "",  {positionClass : "toast-top-center"});
    }
  }

  updatePassword(event : any){
    console.log(this.enteredPassword + " "+this.reenteredPassword);
    if(this.enteredPassword.length==0){
      this.toastrService.error("Password can not be left blank", "",  {positionClass : "toast-top-center"});
    }
    else if(this.enteredPassword!=this.reenteredPassword){
      this.toastrService.error("Passwords do not match", "",  {positionClass : "toast-top-center"});
    }else{
      let id : number = JSON.parse(localStorage.getItem('currentUser')).id ;
      this.userService.changePassword(id,this.enteredPassword).subscribe( (response : any) => {
        let httpResponse : HttpResponse<any> = response ;
        if(response.status==200){
          this.toastrService.success("Password change successful!", "",  {positionClass : "toast-top-center"});
        }else{
          console.log("some error");
        }
      }, error => {
        this.toastrService.error("Password change failed.Please try later!", "",  {positionClass : "toast-top-center"});
      })
    }
  }

}

export class SecurityDetails{
  email : string ;
  securityQuestion : string;
  securityAnswer : string ;
}
