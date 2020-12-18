import { TokenStorageService } from './token-storage.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  observe: 'response' as 'response'
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API_URL= environment.apiUrl;


  redirectToUrl: string = '/list-account-resumes';

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  login(credentials): Observable<any> {
    return this.http.post(this.API_URL + '/accounts/login', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions);
  }

  register(account): Observable<any> {
    return this.http.post(this.API_URL + "/accounts", {
      email: account.email,
      password: account.password,
      firstName: account.firstName,
      lastName: account.lastName
    }, httpOptions);
  }


  public isLoggedIn(): boolean {
    return !!this.tokenStorage.getToken();
}
}