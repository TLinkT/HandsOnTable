import { LayoutObject } from './../layout-creator/layout-object';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  consultar() {
    return this.http.get<LayoutObject[]>('http://localhost:3000/layouts');
  }

  cadastrar(dados: any): Promise<any> {
    return this.http.post('http://localhost:3000/dados', dados)
      .toPromise();
  }

}
