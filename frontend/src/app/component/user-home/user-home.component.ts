import { Component, OnInit } from '@angular/core';
import {OrganizationalUpdateService } from '../../service/organizational-update.service';

@Component({
  selector: 'user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  constructor(public updateService: OrganizationalUpdateService) { 
   this.getUpdates();
   //let a =localStorage.getItem('currentUser');
//console.log(a);
  }

  ngOnInit() {
  }


  updates: any[];


  getUpdates() {
    console.log('inside UserHomeComponent.getUpdates()')
    this.updateService.getUpdates().subscribe(response =>{
      this.updates= response.json();
      console.log(this.updates);
    });
  }

}
