import { BrowserModule } from '@angular/platform-browser';
import { NgModule  } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { IndexComponent } from './component/index/index.component';
import { HomeComponent } from './component/home/home.component';
import { IndexNavigationComponent } from './component/index-navigation/index-navigation.component';
import { UserNavigationComponent } from './component/user-navigation/user-navigation.component';
import { PartnersComponent } from './component/partners/partners.component';
import { AchievementsComponent } from './component/achievements/achievements.component';
import { AboutusComponent } from './component/aboutus/aboutus.component';
import { ContactusComponent } from './component/contactus/contactus.component';
import {routing} from "./app.routing";
import { LoginComponent } from './component/login/login.component';
import {AppConstant } from './service/app-constant.service';
import { RegistrationComponent } from './component/registration/registration.component';

import {PartnerService } from './service/partner.service';
import {RegistrationService } from './service/registration.service';


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    HomeComponent,
    IndexNavigationComponent,
    UserNavigationComponent,
    PartnersComponent,
    AchievementsComponent,
    AboutusComponent,
    ContactusComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    routing
  ],
  providers: [PartnerService,AppConstant,RegistrationService],
  bootstrap: [AppComponent]
})
export class AppModule { }


