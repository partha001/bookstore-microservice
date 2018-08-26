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

  login() {
    //checking the form group to see if the entire form is valid or not
    if (this.myform.valid) {

      //building json postdata
      console.log("form is valid");

      let user: User = new User(this.email.value, this.password.value);

      this.loginService.login(user).subscribe(
        response => {
          console.log("response received", response);
          // the returned user object is a principal object
          let user = response.json().principal;
          if (user) {
            // store user details  in local storage to keep user logged in between page refreshes
            localStorage.setItem('currentUser', JSON.stringify(user));
          }
          this.router.navigate(['/home']);
        },
        () => {
          console.log('completed successfully');
        }
      );


      //this.myform.reset();
    } else {
      console.log("form is invalid");
      this.message_flag = true;
      this.message = "there are errors in form";
    }

  }

}
