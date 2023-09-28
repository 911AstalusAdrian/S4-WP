import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AddPageComponent } from './add-page/add-page.component';
import { DeletePageComponent } from './delete-page/delete-page.component';
import { FilterPageComponent } from './filter-page/filter-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UpdatePageComponent } from './update-page/update-page.component'
import { UsersService } from './services/users.service';
import { LogsService } from './services/logs.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddPageComponent,
    DeletePageComponent,
    FilterPageComponent,
    HomePageComponent,
    UpdatePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [UsersService, LogsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
