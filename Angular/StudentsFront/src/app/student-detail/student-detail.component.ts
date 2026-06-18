import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location, UpperCasePipe } from '@angular/common'; 
import { FormsModule } from '@angular/forms';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-detail',
  standalone: true,
  // TUTAJ dopisujemy UpperCasePipe oraz FormsModule, aby HTML je widział:
  imports: [UpperCasePipe, FormsModule], 
  templateUrl: './student-detail.component.html',
  styleUrl: './student-detail.component.css'
})
export class StudentDetailComponent implements OnInit {

  student = signal<Student | null>(null);

  // Dodajemy getter 's', ponieważ Twój plik HTML szuka zmiennej o nazwie 's'
  get s() {
    return this.student();
  }

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getStudent();
  }

  getStudent(): void {
    const pathId = this.route.snapshot.paramMap.get('id');
    if (pathId) {
      this.studentService.getStudent(+pathId)
        .subscribe(student => this.student.set(student));
    }
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    const s = this.student();
    if (s) {
      this.studentService.updateStudent(s)
        .subscribe(() => this.goBack());
    }
  }

}