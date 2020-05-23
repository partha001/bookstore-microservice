import { Injectable } from '@angular/core';
import { AppConstant } from './app-constant.service';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ForgetPassword } from "../model/model.forget-password";
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { CommonService} from '../service/common.service';

@Injectable()
export class UserService {

  constructor(public appConstant: AppConstant,
    public httpClient: HttpClient,
    public toastrService : ToastrService,
    public commonService : CommonService
    ) { }

    getSecurityQuestionAnswer(email:string, isSecuredUserRequest:boolean) : Observable<any> {
      if(isSecuredUserRequest){
        let options = this.commonService.buildProtectedRequestHeader();
        return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+'/userService/users/forgotPassword/'+email,options);
      }else{
        return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+'/userService/users/forgotPassword/'+email);
      }
    }

    changePassword(id:number , newPassword : string){
      let options = this.commonService.buildProtectedRequestHeader();
      let postdata = {
        id: id,
        newPassword: newPassword
      };
      return this.httpClient.post(this.appConstant.SERVICE_ENDPOINT_API+'/userService/changePassword/', JSON.stringify(postdata), options);
    }

    updateAddressDetails(postdata:string) :  Observable<any> {
      let options = this.commonService.buildProtectedRequestHeader();
      return this.httpClient.post(this.appConstant.SERVICE_ENDPOINT_API+'/userService/updateAddress/', postdata, options);
    }

    getAddressDetails(userid : number) : Observable<any> {
      let options = this.commonService.buildProtectedRequestHeader();
      return this.httpClient.get(this.appConstant.SERVICE_ENDPOINT_API+'/userService/getAddessDetails/'+userid,options);
    }
}

