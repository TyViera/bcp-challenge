import {Component, OnInit} from '@angular/core';
import {ExchangeRateModel} from '../../models/exchange-rate.model';
import {ExchangeRateService} from '../../services/exchange-rate.service';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import Swal from 'sweetalert2';
import {ExchangeRateResponseModel} from '../../models/exchange-rate-response.model';

@UntilDestroy()
@Component({
  selector: 'app-exchange-rate-page',
  templateUrl: './exchange-rate.page.component.html'
})
export class ExchangeRatePageComponent implements OnInit {

  exchangeRateResponseModel: ExchangeRateResponseModel;

  constructor(private exchangeRateService: ExchangeRateService) {
    this.exchangeRateResponseModel = new ExchangeRateResponseModel();
  }

  ngOnInit(): void {
  }

  onExchangeRate(exchangeRateModel: ExchangeRateModel) {
    console.log('on');
    this.exchangeRateService.getExchangeRate(exchangeRateModel)
    .pipe(untilDestroyed(this))
    .subscribe(response => {
      this.exchangeRateResponseModel = response;
      Swal.fire({
        title: 'Exchange rate',
        text: `The response exchange rate was: ${response.exchangedAmount}`
      });
    });
  }

}
