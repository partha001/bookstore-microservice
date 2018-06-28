import { Injectable } from '@angular/core';
import { Partners } from "../model/model.partners";
import { Http } from "@angular/http";
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import { AppConstant } from './app-constant.service';

@Injectable()
export class RegistrationService {

  constructor(public http: Http,public appConstant:AppConstant) { }

  register() {
    console.log('inside RegistrationService.register()');
    return this.http.get(this.appConstant.SERVICE_ENDPOINT_API+'/userService/users/register')
  }

}
