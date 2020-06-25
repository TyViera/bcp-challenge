import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ExchangeRatePageComponent} from './pages/exchange-rate/exchange-rate.page.component';


const routes: Routes = [{
  path: 'exchange-rate',
  component: ExchangeRatePageComponent
}, {
  path: '**',
  pathMatch: 'full',
  redirectTo: 'exchange-rate'
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
