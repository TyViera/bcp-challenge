import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {ExchangeRateComponent} from './exchange-rate/exchange-rate.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule
  ],
  declarations: [
    ExchangeRateComponent
  ],
  exports: [
    ExchangeRateComponent
  ],
  providers: [],
})
export class ComponentsModule {
}
