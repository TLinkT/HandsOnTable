import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})

export class LayoutObject {

  nome: string;
  colunas: any[];

  criar(nome: string, colunas: any[]) {
    this.nome = nome;
    this.colunas = colunas;
  }

}
