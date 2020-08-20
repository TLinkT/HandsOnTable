import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Environment} from '@angular/compiler-cli/src/ngtsc/typecheck/src/environment';
import {environment} from '../../environments/environment';

@Component({
  selector: 'app-file-import-component',
  templateUrl: './file-import.component.html',
  styles: []
})
export class FileImportComponentComponent implements OnInit {
  myForm: FormGroup;
  response: any;

  constructor(private http: HttpClient,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.createForm();
  }

  private createForm(): void {
    this.myForm = this.formBuilder.group({
      file: new FormControl('', [Validators.required]),
      fileSource: new FormControl('', [Validators.required])
    });
  }

  get f() {
    return this.myForm.controls;
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.myForm.patchValue({
        fileSource: file
      });
    }
  }

  submit() {
    const formData = new FormData();
    formData.append('file', this.myForm.get('fileSource').value);

    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');

    const options = {
      headers,
      // params: new HttpParams().append('key', 'value')
    };

    this.http.post(environment.apiUrl, formData, options)
      .subscribe(res => {
        this.response = res;
        console.log(res);
      });
  }
}
