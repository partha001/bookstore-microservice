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
  }

  ngOnInit() {
  }


  updates: any[];


  getUpdates() {
    console.log('inside UserHomeComponent.getUpdates()')
    this.updateService.getUpdates().subscribe(response =>{
      this.updates= response.json();
    });
  }
  
}
