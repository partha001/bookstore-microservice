import { Injectable,Component } from '@angular/core';

@Injectable()
export class AppConstant {

    public  SERVICE_ENDPOINT : string='http://localhost:8083';
    public  SERVICE_ENDPOINT_API : string='http://localhost:8083/api';
    public  USER_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API+"/userService";
    public  PRODUCT_SERVICE_ENDPOINT : string = this.SERVICE_ENDPOINT_API + "productService"; 

}
