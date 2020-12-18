import { ResumeService } from '../_services/resume.service';
import { Education } from '../classes/education';
import { TokenStorageService } from '../_services/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { Resume } from '../classes/resume';

@Component({
  selector: 'app-list-account-resumes',
  templateUrl: './list-account-resumes.component.html',
  styleUrls: ['./list-account-resumes.component.css']
})
export class ListAccountResumesComponent implements OnInit {

  resumes: Resume[];
  resume: Resume;
  education: Education;
  accountId = this.tokenStorage.getAccountID();

  constructor(private resumeService: ResumeService,
    private tokenStorage: TokenStorageService) {

  }


  ngOnInit(): void {
    this.getResumeForAccount();
  }

  deleteResume(resumeId) {
    this.resumeService.deleteResumeById(this.accountId, resumeId).subscribe(response => {
      this.getResumeForAccount();
    })
  }

  getResumeForAccount() {
    this.resumeService.getResumesForAccount(this.tokenStorage.getAccountID()).subscribe(response => {
      this.resumes = response.body;
    })
  }

}