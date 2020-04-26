import { BrowserModule } from '@angular/platform-browser';
import { NgModule  } from '@angular/core';
import { RouterModule } from '@angular/router';
//import { HttpModule } from '@angular/common'
import { HttpClientModule } from '@angular/common/http';
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
import {OrganizationalUpdateService } from './service/organizational-update.service';
import {RegistrationService } from './service/registration.service';
import {LoginService } from './service/login.service';
import {LogoutService } from './service/logout.service';
import {BookService } from './service/book.service';
import {UrlPermissionGuard} from "./url-permission/url.permission";
import { UserHomeComponent } from './component/user-home/user-home.component';
import { BooksComponent } from './component/books/books.component';
import { CommonService } from './service/common.service';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ProfileComponent } from './component/profile/profile.component';
import { CartComponent } from './component/cart/cart.component';



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
    RegistrationComponent,
    UserHomeComponent,
    BooksComponent,
    ForgotPasswordComponent,
    ProfileComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
   // HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    routing
  ],
  providers: [PartnerService,
              AppConstant,
              RegistrationService,
              LoginService,
              LogoutService,
              OrganizationalUpdateService,
              BookService,
              UrlPermissionGuard,
              CommonService],
  bootstrap: [AppComponent]
})
export class AppModule { } 


