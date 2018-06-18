import { Component, OnInit } from '@angular/core';
import { PartnerService } from '../../service/partner.service';
import { Partners } from '../../model/model.partners';


@Component({
  selector: 'app-partners',
  templateUrl: './partners.component.html',
  styleUrls: ['./partners.component.css']
})
export class PartnersComponent implements OnInit {

  constructor(public partnerService: PartnerService) {
    this.getPartners();
  }

  //private partners: Partners[] = [];
  partners: any[];


  ngOnInit() {
  }


  getPartners() {
    console.log('inside PartnersComponent.getPartners()')
    this.partnerService.getPartners().subscribe(response =>{
      this.partners= response.json();
    });
  }

}
