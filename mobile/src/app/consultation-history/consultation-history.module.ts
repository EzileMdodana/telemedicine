import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ConsultationHistoryPageRoutingModule } from './consultation-history-routing.module';

import { ConsultationHistoryPage } from './consultation-history.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ConsultationHistoryPageRoutingModule
  ],
  declarations: [ConsultationHistoryPage]
})
export class ConsultationHistoryPageModule {}
