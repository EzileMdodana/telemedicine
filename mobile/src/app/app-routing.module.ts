import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { TabsPageRoutingModule } from './tabs/tabs-routing.module';
import { Tab1PageModule } from './tab1/tab1.module';
import { TabsPageModule } from './tabs/tabs.module';

const routes: Routes = [
  { path: '', loadChildren: () => import('./tabs/tabs-routing.module').then(m => m.TabsPageRoutingModule) },
  { path: 'doctor-list', loadChildren: () => import('./doctor-list/doctor-list.module').then(m => m.DoctorListPageModule) },
  { path: 'appointment', loadChildren: () => import('./appointment/appointment.module').then(m => m.AppointmentPageModule) },
  { path: 'consultation-history', loadChildren: () => import('./consultation-history/consultation-history.module').then(m => m.ConsultationHistoryPageModule) },
];

@NgModule({
  imports: [
    TabsPageModule,
    
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
