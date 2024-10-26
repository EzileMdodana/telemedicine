import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PreventiveCarePageRoutingModule } from './preventive-care-routing.module';

import { PreventiveCarePage } from './preventive-care.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PreventiveCarePageRoutingModule
  ],
  declarations: [PreventiveCarePage]
})
export class PreventiveCarePageModule {}
