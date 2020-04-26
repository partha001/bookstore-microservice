import { Component, OnInit } from '@angular/core';
import { PartnerService } from '../../service/partner.service';


@Component({
  selector: 'app-partners',
  templateUrl: './partners.component.html',
  styleUrls: ['./partners.component.css']
})
export class PartnersComponent implements OnInit {

  constructor(public partnerService: PartnerService) {
    this.getPartners();
  }

  partners: any[];


  ngOnInit() {
  }


  getPartners() {
    console.log('inside PartnersComponent.getPartners()')
    this.partnerService.getPartners().subscribe((response : Array<PartnersResponse> )=>{
      this.partners = response;
    });
  }

}

export  interface PartnersResponse{
  id : number;
  name : string;
  lineOfBusiness : string;
  remarks : string;
}
