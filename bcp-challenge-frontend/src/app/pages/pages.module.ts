import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ExchangeRatePageComponent} from './exchange-rate/exchange-rate.page.component';
import {ComponentsModule} from '../components/components.module';

@NgModule({
  declarations: [
    ExchangeRatePageComponent
  ],
  imports: [
    CommonModule,
    ComponentsModule
  ]
})
export class PagesModule {

}
