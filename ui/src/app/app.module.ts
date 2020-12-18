import { AuthGuard } from './_services/auth_guard.service';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './login/login.component';
import { AppComponent } from './app.component';


import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { ListAccountEducationsComponent } from './list-account-educations/list-account-educations.component';
import { ListAccountResumesComponent } from './list-account-resumes/list-account-resumes.component';
import { AddResumeComponent } from './add-resume/add-resume.component';
import { AddEducationComponent } from './add-education/add-education.component';
import { ListResumeEducationsComponent } from './list-resume-educations/list-resume-educations.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListAccountEducationsComponent,
    ListAccountResumesComponent,
    AddResumeComponent,
    AddEducationComponent,
    ListResumeEducationsComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthGuard, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }