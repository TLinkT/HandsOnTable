import { LayoutObject } from './layout-object';
import { LayoutService } from './layout.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout-creator-component',
  templateUrl: './layout-creator.component.html',
  styleUrls: ['./layout-creator.component.css']
})
export class LayoutCreatorComponent implements OnInit {

  constructor(private layoutService: LayoutService, private layoutObject: LayoutObject) { }

  nome: string;

  columns = [];

  dataset: any[] = [
    {name: ''},
  ];

  ngOnInit() {}

  adicionar(nome: string) {
    this.nome = nome;

    this.columns.push(nome);
  }

  limpar() {
    this.columns = [];
  }

  salvar(nomeLayout: string) {

    this.layoutObject.criar(nomeLayout, this.columns);

    this.layoutService.cadastrar(this.layoutObject)
      .then(layout => {
        alert('adicionado com sucesso');
      });
  }
}
