import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ExchangeRateService} from './exchange-rate.service';

@NgModule({
  imports: [
    HttpClientModule
  ],
  providers: [
    ExchangeRateService
  ],
  declarations: []
})
export class ServicesModule {
}
