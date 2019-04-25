import { Injectable,Component } from '@angular/core';

@Injectable()
export class AppConstant {

    public  SERVICE_ENDPOINT : string='http://localhost:31111';
    public  SERVICE_ENDPOINT_API : string= this.SERVICE_ENDPOINT + '/api';
    public  USER_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API+"/userService";
    public  PRODUCT_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API + "productService"; 

}
