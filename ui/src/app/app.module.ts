import { DataService } from './data-creator/data-service';
import { HttpClientModule } from '@angular/common/http';
import { LayoutService } from './layout-creator/layout.service';
import { FileImportComponentComponent } from './file-import-component/file-import.component';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from '@angular/forms';
import { LayoutCreatorComponent } from './layout-creator/layout-creator.component';
import {InputTextModule} from 'primeng/inputtext';
import { HotTableModule } from '@handsontable/angular';
import { DataCreatorComponent } from './data-creator/data-creator.component';
import { routing } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    FileImportComponentComponent,
    LayoutCreatorComponent,
    DataCreatorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    InputTextModule,
    HotTableModule,
    routing,
  ],
  providers: [
    LayoutService,
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
