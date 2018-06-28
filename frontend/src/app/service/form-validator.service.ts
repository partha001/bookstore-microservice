import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from "@angular/forms";

@Injectable()
export class FormValidatorService {

  constructor() { }

  //method either returns ValidationErrors or null
  static cannotContainSpace(control: AbstractControl): ValidationErrors | null {
    if ((control.value as string).indexOf(' ') > 0)
      return { cannotContainSpace: true };
    return null;
  };


  // //method to simulate asynchornous validation happeing against some
  // //backend call.the backend call has been simulated using setTimeout()
  // static shouldBeUnique(control: AbstractControl): Promise<ValidationErrors | null> {

  //   //this is simple validation logic 
  //   // if(control.value === 'biswas.partha1@gmail.com')
  //   //   return {shouldBeUnique : true};
  //   // return null

  //   return new Promise((resolve, reject) => {
  //     setTimeout(() => {
  //       if (control.value === 'biswas.partha1@gmail.com')
  //         resolve({ shouldBeUnique: true });
  //       else resolve(null);
  //     }, 2000);
  //   });
  // }

}
