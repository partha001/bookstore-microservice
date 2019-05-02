import { Component } from '@angular/core';

export class ForgetPassword {
    
    constructor(public message: string, 
                public email: string,
                public securityQuestion: string,
                public securityAnswer: string) { }

}