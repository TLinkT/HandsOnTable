import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})

export class DataObject {

  dados: any;
  colunas: any[];

  criar(dados: any, colunas: any[]) {
    this.dados = dados;
    this.colunas = colunas;
  }

}
