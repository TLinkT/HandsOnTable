import { FileImportComponentComponent } from './file-import-component/file-import.component';
import { DataCreatorComponent } from './data-creator/data-creator.component';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutCreatorComponent } from './layout-creator/layout-creator.component';


const routes: Routes = [
  { path: '', component: LayoutCreatorComponent},
  { path: 'dados', component: DataCreatorComponent},
  { path: 'import', component: FileImportComponentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
