import { EducationService } from '../_services/education.service';
import { Education } from '../classes/education';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-resume-educations',
  templateUrl: './list-resume-educations.component.html',
  styleUrls: ['./list-resume-educations.component.css']
})
export class ListResumeEducationsComponent implements OnInit {

  educations: Education[];
  resumeId: string;


  constructor(private educationService : EducationService,
              private route: ActivatedRoute,
              private router: Router) {

   }

  ngOnInit(): void {
    this.getAllEducationsForResume();
  }

  getAllEducationsForResume() {
    this.resumeId = this.route.snapshot.params['resumeId'];

    this.educationService.getEducationsForResume(this.resumeId).subscribe(response => {
      this.educations = response.body;
    })
  }

  addEducation() {
    this.router.navigate(['/addEducation', this.resumeId]);
  }

  editEducation(educationId) {
    this.router.navigate(['/editEducation', this.resumeId, educationId]);

  }

  deleteEducation(educationId) {
    this.educationService.deleteEducationById(this.resumeId, educationId).subscribe(response => {
      this.educations = response.body;
    })
  }
}
