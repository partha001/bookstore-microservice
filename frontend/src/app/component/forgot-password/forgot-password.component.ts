import { Component, OnInit } from '@angular/core';
import { AppConstant } from '../../service/app-constant.service';
//import { Http } from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ForgetPassword } from "../../model/model.forget-password";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  
  constructor(public appConstant : AppConstant ,
    //public http: Http
    public httpClient: HttpClient) { }

  ngOnInit() {
    this.model = new ForgetPassword("Reset password","","","");
    this.displaySecurityDetails = false;
    this.enteredSecurityAnswer ="";
  }

  model : ForgetPassword;
  enteredSecurityAnswer : string;
  displaySecurityDetails : boolean;

  verifyEmail(){
    this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+'/userService/users/forgotPassword/'+this.model.email)
    .subscribe( (response:VerifyEmailResponse) =>{
        console.log(response);
        this.model.securityQuestion = response.securityQuestion;
        this.displaySecurityDetails = true;
        this.model.securityAnswer = response.securityAnswer;
       
      },
      error =>{
        this.model.message = "This is not a registered email address !";
        this.displaySecurityDetails = false;
      },
      () =>  {
        console.log("completed successfully");
      }
    );
  }

  generatePassword(){
    // if(this.enteredSecurityAnswer== this.model.securityAnswer){
    //   console.log('entered password is correct');
    //   this.http.get(this.appConstant.USER_SERVICE_ENDPOINT +'/users/'+this.model.email+ '/generatePassword')
    //             .subscribe(response =>{
    //               this.model.message = 'Password generation successful. The new password is: '+response.json().generatedPassword;
    //             },
    //             error => {
    //               console.log('some error has occured');
    //             },
    //             () =>{
    //             });
      //this.model.message = 'Password generation successful . Please use the below password to login';

      if(this.enteredSecurityAnswer== this.model.securityAnswer){
        console.log('entered password is correct');
        this.httpClient.get(this.appConstant.USER_SERVICE_ENDPOINT +'/users/generatePassword/'+this.model.email )
                  .subscribe((response : GeneratePasswordResponse) =>{
                    this.model.message = 'Password generation successful. The new password is: '+response.generatedPassword;
                  },
                  error => {
                    console.log('some error has occured');
                  },
                  () =>{
                  });

    }else{
      console.log('entered password is incorrect');
      this.model.message = 'The entered answer is incorrect';
    }
  }

}

export interface GeneratePasswordResponse {
  generatedPassword: string;
}


export interface VerifyEmailResponse{
  securityQuestion : string;
  securityAnswer : string;
}
