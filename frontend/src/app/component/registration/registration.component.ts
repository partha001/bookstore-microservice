import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { FormValidatorService } from '../../service/form-validator.service';
import { Http } from '@angular/http';
import {Router} from "@angular/router";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private http: Http, public router: Router) { }

  ngOnInit() {
  }

  myform = new FormGroup({
    'firstName': new FormControl('', Validators.required),
    'lastName': new FormControl(),
    'email': new FormControl('', [Validators.required,
    Validators.minLength(3),
    FormValidatorService.cannotContainSpace,
    ]),
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

  validateRpassword() {
    if (this.myform.get('password').value != this.myform.get('rpassword').value)
      this.myform.get('rpassword').setErrors({ 'doesntMatch': true });
  }


  register() {
    //checking the form group to see if the entire form is valid or not
    if (this.myform.valid) {
      console.log("form is valid");
      let postdata = {
        firstname: this.firstName.value,
        lastname: this.lastName.value,
        username : this.email.value,
        password : this.password.value
      };
      console.log(postdata);
      this.http.post('http://localhost:8083/api/external/customer/customer', JSON.stringify(postdata)).subscribe(
        response => {
          console.log(response.json());
        }
      );
    } else {
      console.log("form is invalid");
    }
  }

  cancelRegistration(){
    console.log('inside cancelRegistration()');
    this.router.navigate(['/']);
  }


}
