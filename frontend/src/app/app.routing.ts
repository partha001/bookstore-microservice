import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { IndexComponent } from './component/index/index.component';
import { HomeComponent } from './component/home/home.component';
import { IndexNavigationComponent } from './component/index-navigation/index-navigation.component';
import { UserNavigationComponent } from './component/user-navigation/user-navigation.component';
import { PartnersComponent } from './component/partners/partners.component';
import { AchievementsComponent } from './component/achievements/achievements.component';
import { AboutusComponent } from './component/aboutus/aboutus.component';
import { ContactusComponent } from './component/contactus/contactus.component';
import { LoginComponent } from './component/login/login.component';
import { RegistrationComponent } from './component/registration/registration.component';

const appRoutes: Routes = [
        {
          path: '',
          component: IndexComponent,
          children : [
            {
                path: '',
                component: LoginComponent
              },
              {
                path: 'aboutUs',
                component: AboutusComponent
              },
              {
                path: 'partners',
                component: PartnersComponent
              },
              {
                path: 'achievements',
                component: AchievementsComponent
              },
              {
                path: 'contactUs',
                component: ContactusComponent
              },
              {
                path: 'register',
                component: RegistrationComponent
              },
          ]
        },
        {
          path: 'home',
          component: HomeComponent
        }
        

];
  
  export const routing = RouterModule.forRoot(appRoutes);