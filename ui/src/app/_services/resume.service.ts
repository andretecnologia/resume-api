import { Observable } from 'rxjs';
import { Address } from './../classes/address';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resume } from '../classes/resume';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private API_URL= environment.apiUrl;


  constructor(private http: HttpClient) { }


  getResumesForAccount(accountId: string): Observable<any> {
    return this.http.get<Resume[]>(this.API_URL + `/resumes/${accountId}`, { observe: "response" });
  }


  addResume(resume: Resume, accountId: string): Observable<any> {
    return this.http.post<any>(this.API_URL + `/resumes/${accountId}`, resume);
  }

  getResumeById(resumeId: string): Observable<any> {
    return this.http.get<Resume>(this.API_URL + `/resumes/resume/${resumeId}`, { observe: "response" });
  }

  updateResume(resumeId: string, resume: Resume): Observable<any> {
    return this.http.put<Resume>(this.API_URL + `/resumes/${resumeId}`, resume);
  }

  deleteResumeById(accountId: string, resumeId: string): Observable<any> {
    return this.http.delete<Resume>(this.API_URL + `/resumes/${accountId}/${resumeId}`);
  }
}
