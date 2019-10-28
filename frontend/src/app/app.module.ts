import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { LoginService } from "./login.service";
import { FormsModule } from "@angular/forms";
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatRadioModule} from '@angular/material/radio';




import {
  MatButtonModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule,
  MatIconModule,
  MatNativeDateModule,
  
  
} from '@angular/material';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegistrationComponent, DashboardComponent],
  imports: [BrowserModule,
     AppRoutingModule, 
     FormsModule, 
     BrowserAnimationsModule,  
     MatButtonModule,
     MatFormFieldModule,
     MatInputModule,
     MatRippleModule,
     MatIconModule,
     HttpClientModule,
     MatTableModule,
     MatDatepickerModule,
     MatNativeDateModule,
     MatRadioModule
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule {}
