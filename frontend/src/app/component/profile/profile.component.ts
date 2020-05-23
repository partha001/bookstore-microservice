import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ForgetPassword } from "../../model/model.forget-password";
import { UserService } from "../../service/user-service"
import { ToastrService } from 'ngx-toastr';
import { FormGroup, FormControl, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { FormValidatorService } from '../../service/form-validator.service';


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
  public existingAddress : AddressDetails;
  public userDetails : UserDetails;



  constructor(public userService: UserService,
    public toastrService : ToastrService) {
    this.currentModule ='profileDetails';
   }

  ngOnInit() {
    let principal:any = JSON.parse(localStorage.getItem('currentUser')).principal;
    console.log(principal.firstName);
    this.userDetails = new UserDetails();
    this.userDetails.firstName = principal.firstName;
    this.userDetails.lastName = principal.lastName;
    this.userDetails.email = principal.username;
   // this.userDetails.firstName = principal.firstName;
  }

  myform = new FormGroup({
    'addressLine1': new FormControl('', [Validators.required,Validators.minLength(5)]),
    'addressLine2': new FormControl(),
    'pincode': new FormControl('', [Validators.required,Validators.minLength(5),Validators.maxLength(9)]),
    'state': new FormControl('',  [Validators.required,Validators.minLength(5)]),
    'country': new FormControl('', [Validators.required,Validators.minLength(5)])
  });

  get addressLine1() {
    return this.myform.get('addressLine1');
  }

  get addressLine2() {
    return this.myform.get('addressLine2');
  }

  get pincode() {
    return this.myform.get('pincode');
  }

  get state() {
    return this.myform.get('state');
  }

  get country() {
    return this.myform.get('country');
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

    else if(currentMenu=='changeAddress'){
      console.log("inside changeAddress");
      let userid : number = JSON.parse(localStorage.getItem('currentUser')).id;
      this.userService.getAddressDetails(userid).subscribe( response => {
        let httpResponse : HttpResponse<any> = response;
        if(httpResponse.status==200){
          this.existingAddress = httpResponse.body;
          this.addressLine1.setValue(this.existingAddress.addressLine1);
          this.addressLine2.setValue(this.existingAddress.addressLine2);
          this.pincode.setValue(this.existingAddress.pincode);
          this.country.setValue(this.existingAddress.country);
          this.state.setValue(this.existingAddress.state);
        }

        }, error => { });
    }
    else if(currentMenu=='profileDetails'){
      console.log(JSON.parse(localStorage.getItem('currentUser')).principal);
    }

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

  updateAddress(){
    if(this.myform.valid){
      let id : number = JSON.parse(localStorage.getItem('currentUser')).id;
      let postdata = {
          userId: id,
          addressLine1:this.addressLine1.value,
          addressLine2:this.addressLine2.value,
          state:this.state.value,
          country:this.country.value,
          pincode:this.pincode.value
        };
      //console.log(JSON.stringify(postdata));
      this.userService.updateAddressDetails(JSON.stringify(postdata))
      .subscribe((response : HttpResponse<any>) => {
        if(response.status==200){
          this.toastrService.success("Address details updated successfully", "",  {positionClass : "toast-top-center"});
        }else{
          this.toastrService.error("Update failed. Please try later!", "",  {positionClass : "toast-top-center"});
        }
      }, error => {
        this.toastrService.error("Update failed. Please try later!", "",  {positionClass : "toast-top-center"});
       
      })

    }else{
      console.log("form is invalid");
      this.toastrService.error("Invalid form details!", "",  {positionClass : "toast-top-center"});
    }
  }

}

export class SecurityDetails{
  email : string ;
  securityQuestion : string;
  securityAnswer : string ;
}

export class AddressDetails{
  addressLine1 : string;
  addressLine2 : string;
  pincode : number;
  state : string;
  country : string;
}

export class UserDetails {
  firstName : string ;
  lastName : string ;
  email : string ;
}
