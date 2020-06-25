import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ExchangeRateModel} from '../../models/exchange-rate.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-exchange-rate',
  templateUrl: './exchange-rate.component.html'
})
export class ExchangeRateComponent implements OnInit {

  @Output()
  exchangeRateAction = new EventEmitter<ExchangeRateModel>();

  exchangeRateForm: FormGroup;


  submitExchangeRate() {
    if (this.exchangeRateForm.valid) {
      this.exchangeRateAction.emit(this.exchangeRateForm.value);
    } else {
      Swal.fire({
        title: 'Invalid form',
        text: 'You must to finish of fill required fields',
        icon: 'warning'
      });
    }
  }

  ngOnInit(): void {
    this.loadForm();
  }

  private loadForm() {
    const currencyValidators = [Validators.required, Validators.pattern('[A-Z]{3}'), Validators.minLength(3), Validators.maxLength(3)];
    const amountValidators = [Validators.required];
    this.exchangeRateForm = new FormGroup({
      homeCurrency: new FormControl('PEN', currencyValidators),
      targetCurrency: new FormControl('USD', currencyValidators),
      amount: new FormControl('30', amountValidators)
    });
  }

}
