import { Injectable,Component } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable()
export class AppConstant {

    public  SERVICE_ENDPOINT : string= environment.service_endpoint;
    public  SERVICE_ENDPOINT_API : string= this.SERVICE_ENDPOINT + '/api';
    public  USER_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API+"/userService";
    public  PRODUCT_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API + "/productService"; 

}
