import { EducationService } from './../_services/education.service';
import { Education } from './../classes/education';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-education',
  templateUrl: './add-education.component.html',
  styleUrls: ['./add-education.component.css']
})
export class AddEducationComponent implements OnInit {

  education = {} as Education;
  resumeId: string;
  educationId: string;


  constructor(private educationService: EducationService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.educationId = this.route.snapshot.params['educationId'];
    this.resumeId = this.route.snapshot.params['resumeId'];
    console.log(this.educationId);
    console.log(this.resumeId);

    if (this.educationId != undefined) {
      this.educationService.getEducationById(this.educationId)
        .subscribe(
          data => {
            this.education = data.body;
            console.log(this.education)
          }
        )
    }
  }


  onSubmit() {
    if (this.educationId == undefined) {
      this.educationService.addEducation(this.education, this.resumeId).subscribe(
        data => {
          this.gotoEducationList()
        })
    } else {
      this.educationService.editEducation(this.education, this.educationId).subscribe(
        data => {
          this.gotoEducationList()
        })
    }


  }

  gotoEducationList() {
    this.router.navigate(['/list-resume-educations', this.resumeId]);
  }

}
