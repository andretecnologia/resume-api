import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Education } from '../classes/education';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EducationService {

  private API_URL= environment.apiUrl;


  constructor(private http: HttpClient) { }

  addEducation(education: Education, resumeId: string): Observable<any> {
    return this.http.post<any>(this.API_URL + `/educations/${resumeId}`, education);
  }

  getEducationsForResume(resumeId: string): Observable<any> { 
    return this.http.get<Education[]>(this.API_URL + `/educations/${resumeId}`, {observe: "response" });
  }

  getEducationById(educationId:string): Observable<any> {
    return this.http.get<Education>(this.API_URL + `/educations/education/${educationId}`, {observe: "response" });
  }

  editEducation(education: Education, educationId:string): Observable<any> {
    return this.http.put<Education>(this.API_URL + `/educations/${educationId}`, education);
  }

  deleteEducationById(resumeId:string,educationId:string): Observable<any> {
    return this.http.delete<Education[]>(this.API_URL + `/educations/${resumeId}/${educationId}`, {observe: "response" });
  }


  getEducationsForAccount(accountId: string): Observable<any> {
    return this.http.get<Education[]>(this.API_URL + `/educations/account/${accountId}`, { observe: "response" });
  }
}
