import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LayoutService {

  constructor(private http: HttpClient) { }

  cadastrar(layout: any): Promise<any> {
    return this.http.post('http://localhost:3000/layouts', layout)
      .toPromise();
  }

  excluir(id: number) {
    this.http.delete('http://localhost:3000/layouts/${id}')
    .toPromise();
  }

}
