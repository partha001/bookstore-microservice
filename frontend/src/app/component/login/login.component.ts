import { Component, OnInit } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { FormValidatorService } from '../../service/form-validator.service';
import { Http } from '@angular/http';
import { Router } from "@angular/router";
import { LoginService } from '../../service/login.service';
import { User } from "../../model/model.user";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  message_flag: boolean = false;
  message: string = "";

  constructor(public router: Router, private loginService: LoginService) { }

  ngOnInit() {
  }

  myform = new FormGroup({
    'email': new FormControl('', [Validators.required,
    Validators.minLength(3),
    FormValidatorService.cannotContainSpace
    ]),
    'password': new FormControl('', [Validators.required,
    Validators.minLength(3)
    ])
  });

 
  get password() {
    return this.myform.get('password');
  }

  get email() {
    return this.myform.get('email');
  }

  login(){
     //checking the form group to see if the entire form is valid or not
     if (this.myform.valid) {

      //building json postdata
      console.log("form is valid");
     
      // let postdata = {
      //   username: this.email.value,
      //   password: this.password.value
      // };
      // console.log(postdata);

      // let formData: FormData = new FormData(); 
      // formData.append('username', this.email.value); 
      // formData.append('password', this.password.value); 
      // var body = 'username='+this.email.value+'&password='+this.password.value;
      
      // this.loginService.login(postdata).subscribe(
      //   response => {
      //     console.log("response received");
      //     console.log(response.json());
      //     // if (response.status == 201) {
      //     //   this.message_flag = true;
      //     //   this.message = "registration successfull !"
      //     // } else {
      //     //   this.message_flag = true;
      //     //   this.message = "registration failed . Please try agin !"
      //     // }
      //   }
      // );

let user : User= new User(this.email.value,this.password.value);

this.loginService.login1(user).subscribe(
    response => {
      console.log("response received");
      //console.log(response.json());
      // if (response.status == 201) {
      //   this.message_flag = true;
      //   this.message = "registration successfull !"
      // } else {
      //   this.message_flag = true;
      //   this.message = "registration failed . Please try agin !"
      // }
    },
    () => {
      console.log('completed successfully');
    }
  );

//this.loginService.login1(user).map();

// this.loginService.login1(user)
// .subscribe(data=>{
//   this.router.navigate(['/profile']);
//   },err=>{
//   console.log('username or password incorrect');
//   }
// )


      //this.myform.reset();
    } else {
      console.log("form is invalid");
      this.message_flag = true;
      this.message = "there are errors in form";
    }

   }

}
