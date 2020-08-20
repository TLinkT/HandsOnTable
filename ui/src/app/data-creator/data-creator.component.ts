import { LayoutObject } from './../layout-creator/layout-object';
import { DataService } from './data-service';
import { Component, OnInit } from '@angular/core';
import { DataObject } from './data-object';

@Component({
  selector: 'app-data-creator',
  templateUrl: './data-creator.component.html',
  styleUrls: ['./data-creator.component.css']
})
export class DataCreatorComponent implements OnInit {

  layouts: LayoutObject[];
  layoutSelecionado = [];

  dataset: any[] = [
    {}
  ];

  constructor(private dataService: DataService, private dataObject: DataObject) { }

  ngOnInit() {
    this.dataService.consultar()
    .subscribe(dados => this.layouts = dados);
  }

  buscarLayout(nome) {
    this.layoutSelecionado = [];
    for (const layout in this.layouts) {
      if (this.layouts[layout].nome === nome) {
        this.layoutSelecionado = this.layouts[layout].colunas;
      }
    }
  }

  salvar(dados) {
    console.log(dados);
    this.dataObject.criar(dados, this.layoutSelecionado);
    this.dataService.cadastrar(this.dataObject)
      .then(layout => {
        alert('adicionado com sucesso');
      });
  }

}
