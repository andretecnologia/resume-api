import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const ACCOUNT_ID = 'ACCOUNT_ID';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {2
    window.sessionStorage.removeItem(TOKEN_KEY);

    if (token.startsWith("Bearer ")){
      token = token.substring(7, token.length);
      } else {
        throw new Error("invalid token!");
      }

    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveAccount(accountID: string) {
    window.sessionStorage.removeItem(ACCOUNT_ID);
    window.sessionStorage.setItem(ACCOUNT_ID,accountID);
  }

  public getAccountID() {
    return sessionStorage.getItem(ACCOUNT_ID);
  }
}
