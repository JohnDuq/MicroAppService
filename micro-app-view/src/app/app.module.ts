import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

//////////////////PAGE////////////////////
import { AppComponent } from './app.component';
import { HeaderComponent } from './template/header/header.component';
import { HomeComponent } from './template/home/home.component';
import { FooterComponent } from './template/footer/footer.component';
import { RoleComponent } from './role/role.component';

import { RoleService } from './role/role.service';
import { FormsModule } from '@angular/forms';

//////////////////NAV////////////////////
import { RouterModule, Routes } from '@angular/router';

//////////////////PIMENG////////////////////
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputSwitchModule } from 'primeng/inputswitch';
import { SelectButtonModule } from 'primeng/selectbutton';
import { UserComponent } from './user/user.component';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressBarModule } from 'primeng/progressbar';

//////
import { HttpClientModule } from '@angular/common/http';
import { AuthorizationComponent } from './authorization/authorization.component';

const routes: Routes = [
  {path: '', redirectTo:'/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'auth', component: AuthorizationComponent},
  {path: 'user', component: UserComponent}
];

@NgModule({
   declarations: [
      AppComponent,
      HeaderComponent,
      HomeComponent,
      FooterComponent,
      RoleComponent,
      UserComponent,
      AuthorizationComponent
   ],
   imports: [
      FormsModule,
      BrowserModule,
      RouterModule.forRoot(routes),
      HttpClientModule,
      ButtonModule,
      InputTextModule,
      InputSwitchModule,
      SelectButtonModule,
      BlockUIModule,
      ProgressBarModule
   ],
   providers: [
      RoleService
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
