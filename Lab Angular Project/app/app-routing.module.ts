import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from './home-page/home-page.component';
import {LoginComponent} from './login/login.component';
import {AddPageComponent} from './add-page/add-page.component';
import {DeletePageComponent} from './delete-page/delete-page.component';
import {UpdatePageComponent} from './update-page/update-page.component';
import {FilterPageComponent} from './filter-page/filter-page.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'homepage', component: HomePageComponent},
  { path: 'addPage', component: AddPageComponent},
  { path: 'deletePage', component: DeletePageComponent},
  { path: 'updatePage', component: UpdatePageComponent},
  { path: 'filterPage', component: FilterPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }