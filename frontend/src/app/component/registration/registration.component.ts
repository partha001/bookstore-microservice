import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { FormValidatorService } from '../../service/form-validator.service';
import { Http } from '@angular/http';
import { Router } from "@angular/router";
import { RegistrationService } from "../../service/registration.service";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  message_flag: boolean = false;
  message: string = "";

  constructor(private http: Http, public router: Router, private registrationService: RegistrationService) { }

  ngOnInit() {
  }

  myform = new FormGroup({
    'firstName': new FormControl('', Validators.required),
    'lastName': new FormControl(),
    'email': new FormControl('', [Validators.required,
    Validators.minLength(3),
    FormValidatorService.cannotContainSpace,
    ]),
    'securityQuestion': new FormControl('', Validators.required),
    'securityAnswer': new FormControl('', Validators.required),
    'password': new FormControl('', [Validators.required,
    Validators.minLength(3)
    ]),
    'rpassword': new FormControl()
  });

  get firstName() {
    return this.myform.get('firstName');
  }

  get lastName() {
    return this.myform.get('lastName');
  }

  get password() {
    return this.myform.get('password');
  }

  get rpassword() {
    return this.myform.get('rpassword');
  }

  get email() {
    return this.myform.get('email');
  }

  get securityQuestion(){
    return this.myform.get('securityQuestion');
  }

  get securityAnswer(){
    return this.myform.get('securityAnswer');
  }


  validateRpassword() {
    if (this.myform.get('password').value != this.myform.get('rpassword').value)
      this.myform.get('rpassword').setErrors({ 'doesntMatch': true });
  }

  checkEmailAvailability() {
    console.log("checking if the username exists");
    let username: string = this.myform.get('email').value;
    if (this.myform.get('email').touched && username != '') {
      this.registrationService.checkUsernameAvailability(username).subscribe(
        response => {
          console.log(response);
          if(response.json().usernameExists){
            this.myform.get('email').setErrors({ 'usernameExists': true });
          }
          
        }
      );
    }
  }


  register() {
    //checking the form group to see if the entire form is valid or not
    if (this.myform.valid) {
      console.log("form is valid");
      let postdata = {
        firstname: this.firstName.value,
        lastname: this.lastName.value,
        username: this.email.value,
        securityQuestion: this.securityQuestion.value,
        securityAnswer: this.securityAnswer.value,
        password: this.password.value
      };
      console.log(postdata);
      this.registrationService.register(postdata).subscribe(
        response => {
          console.log(response.json());
          if (response.status == 201) {
            this.message_flag = true;
            this.message = "registration successfull !"
          } else {
            this.message_flag = true;
            this.message = "registration failed . Please try agin !"
          }
        }
      );
      //this.myform.reset();
    } else {
      console.log("form is invalid");
      this.message_flag = true;
      this.message = "there are errors in form";
    }
  }

  cancelRegistration() {
    console.log('inside cancelRegistration()');
    this.router.navigate(['/']);
  }


  // getErrors  (formGroup: FormGroup, errors: any = {}) {
  //   Object.keys(formGroup.controls).forEach(field => {
  //     const control = formGroup.get(field);
  //     if (control instanceof FormControl) {
  //       errors[field] = control.errors;
  //     } else if (control instanceof FormGroup) {
  //       errors[field] = this.getErrors(control);
  //     }
  //   });
  //   return errors;
  // }

}
