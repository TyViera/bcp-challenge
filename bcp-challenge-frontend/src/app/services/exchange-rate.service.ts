import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import Swal from 'sweetalert2';
import {environment} from '../../environments/environment';
import {catchError, map} from 'rxjs/operators';
import {ExchangeRateModel} from '../models/exchange-rate.model';
import {ExchangeRateResponseModel} from '../models/exchange-rate-response.model';

export const HTTP_CODE_CONNECTION_REFUSED = 0;
export const HTTP_CODE_INTERNAL_ERROR = 500;
export const SWEET_ALERT_IMAGES_HEIGHT = 150;
export const NO_CONNECTION_IMAGE_URL = '../assets/images/disconnected.png';

@Injectable()
export class ExchangeRateService {

  static handleHttpInternalServerError(err): boolean {
    let handle = false;
    if (err instanceof HttpErrorResponse) {
      if (err.status === HTTP_CODE_INTERNAL_ERROR) {
        Swal.fire({
          title: 'Server error',
          text: 'Unexpected error occurred, please try in a few minutes.',
          icon: 'warning'
        });
        handle = true;
      }
    }
    return handle;
  }


  constructor(private http: HttpClient) {
  }

  getExchangeRate(exchangeRateModel: ExchangeRateModel): Observable<ExchangeRateResponseModel> {
    let httpParams: HttpParams = new HttpParams();
    httpParams = httpParams.append('homeCurrency', exchangeRateModel.homeCurrency);
    httpParams = httpParams.append('targetCurrency', exchangeRateModel.targetCurrency);
    httpParams = httpParams.append('amount', String(exchangeRateModel.amount));
    console.log(httpParams);
    console.log(exchangeRateModel);
    return this.http.get(`${environment.baseUrl}/exchange-rate`, {
      params: httpParams
    }).pipe(
      map(value => value),
      catchError(err => this.handleHttpError(err))
    );
  }

  private handleHttpError(err): Observable<any> {
    if (err instanceof HttpErrorResponse) {
      if (err.status === HTTP_CODE_CONNECTION_REFUSED) {
        Swal.fire({
          text: 'Unavailable server, please try in a few minutes.',
          imageUrl: NO_CONNECTION_IMAGE_URL,
          imageHeight: SWEET_ALERT_IMAGES_HEIGHT,
          imageAlt: 'Server error'
        });
      } else {
        ExchangeRateService.handleHttpInternalServerError(err);
      }
    }
    return throwError(err);
  }


}
