import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PreventiveCarePage } from './preventive-care.page';

const routes: Routes = [
  {
    path: '',
    component: PreventiveCarePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PreventiveCarePageRoutingModule {}
