import { ResumeService } from './../_services/resume.service';
import { Address } from './../classes/address';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Resume } from '../classes/resume';

@Component({
  selector: 'app-add-resume',
  templateUrl: './add-resume.component.html',
  styleUrls: ['./add-resume.component.css']
})
export class AddResumeComponent implements OnInit {

  resume = {} as Resume;
  address = {} as Address;


  resumeId: string;

  constructor(private resumeService: ResumeService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute) {
    this.resume = new Resume();
  }

  ngOnInit(): void {
    this.resumeId = this.route.snapshot.params['resumeId'];

    console.log(this.resumeId);

    if (this.resumeId != undefined) {
      this.resumeService.getResumeById(this.resumeId)
        .subscribe(
          data => {
            this.resume = data.body;
            this.address = data.body.address;
            console.log(this.resume)
          }
        )
    }
  }


  onSubmit() {
    this.resume.address = this.address;

    if (this.resumeId == undefined) {
      this.resumeService.addResume(this.resume, this.tokenStorage.getAccountID()).subscribe(
        data => {

          this.gotoResumeList()
        })
    }else {
      this.resumeService.updateResume(this.resumeId, this.resume).subscribe(result => this.gotoResumeList());
    }

  }

  gotoResumeList() {
    this.router.navigate(['/list-account-resumes']);
  }


}
