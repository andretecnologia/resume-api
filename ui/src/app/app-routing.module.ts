import { RegisterComponent } from './register/register.component';
import { AddEducationComponent } from './add-education/add-education.component';
import { ListResumeEducationsComponent } from './list-resume-educations/list-resume-educations.component';
import { AddResumeComponent } from './add-resume/add-resume.component';
import { ListAccountResumesComponent } from './list-account-resumes/list-account-resumes.component';
import { ListAccountEducationsComponent } from './list-account-educations/list-account-educations.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AuthGuard } from './_services/auth_guard.service';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'list-account-resumes', component: ListAccountResumesComponent, canActivate: [AuthGuard] },
  { path: 'list-account-educations', component: ListAccountEducationsComponent, canActivate: [AuthGuard] },
  { path: 'addResume/:resumeId', component: AddResumeComponent, canActivate: [AuthGuard] },
  { path: 'addResume', component: AddResumeComponent, canActivate: [AuthGuard] },
  { path: 'addEducation/:resumeId', component: AddEducationComponent, canActivate: [AuthGuard] },
  { path: 'editEducation/:resumeId/:educationId', component: AddEducationComponent, canActivate: [AuthGuard] },
  { path: 'list-resume-educations/:resumeId', component: ListResumeEducationsComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }