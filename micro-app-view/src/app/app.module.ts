import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

//////////////////PAGE////////////////////
import { AppComponent } from './app.component';
import { HeaderComponent } from './template/header/header.component';
import { HomeComponent } from './template/home/home.component';
import { FooterComponent } from './template/footer/footer.component';
import { RoleComponent } from './authorization/role/role.component';

import { RoleService } from './authorization/role/role.service';
import { FormsModule } from '@angular/forms';

//////////////////NAV////////////////////
import { RouterModule, Routes } from '@angular/router';

//////////////////PIMENG////////////////////
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputSwitchModule } from 'primeng/inputswitch';
import { SelectButtonModule } from 'primeng/selectbutton';
import { UserComponent } from './authorization/user/user.component';
import { BlockUIModule } from 'primeng/blockui';
import { ProgressBarModule } from 'primeng/progressbar';
import { PickListModule } from 'primeng/picklist';
import { AccordionModule } from 'primeng/accordion';
import { ToastModule } from 'primeng/toast';
import { InputMaskModule } from 'primeng/inputmask';

//////
import { HttpClientModule } from '@angular/common/http';
import { AuthorizationComponent } from './authorization/authorization.component';

const routes: Routes = [
  {path: '', redirectTo:'/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'auth', component: AuthorizationComponent}
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
      BrowserAnimationsModule,
      RouterModule.forRoot(routes),
      HttpClientModule,
      ButtonModule,
      InputTextModule,
      InputSwitchModule,
      SelectButtonModule,
      BlockUIModule,
      ProgressBarModule,
      PickListModule,
      AccordionModule,
      ToastModule,
      InputMaskModule
   ],
   providers: [
      RoleService
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { }
