import { EducationService } from '../_services/education.service';
import { Education } from '../classes/education';
import { TokenStorageService } from '../_services/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-account-educations',
  templateUrl: './list-account-educations.component.html',
  styleUrls: ['./list-account-educations.component.css']
})
export class ListAccountEducationsComponent implements OnInit {

  educations: Education[];

  constructor(private educationService : EducationService,
              private tokenStorage: TokenStorageService) {

   }


  ngOnInit(): void {
    this.educationService.getEducationsForAccount(this.tokenStorage.getAccountID()).subscribe(response => {
      this.educations = response.body;
    })

  }

}
