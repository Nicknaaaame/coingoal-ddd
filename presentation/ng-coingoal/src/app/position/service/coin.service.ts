import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map, Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Coin} from "../model/coin";
import {CoinPage} from "../model/page/coin.page";

@Injectable({providedIn: 'root'})
export class CoinService {
  constructor(private readonly http: HttpClient) {
  }

  fetchCoinPage(name?: string): Observable<CoinPage> {
    return this.http.get<CoinPage>(`${environment.apiUrl}/api/v1/coin`, {
      params: new HttpParams()
        .set('name', name ?? '')
        .set('size', 10)
        .set('page', 0)
        .set('sort', 'id,asc'),
    });
  }

  fetchCoins(name?: string): Observable<Coin[]> {
    return this.fetchCoinPage(name).pipe(
      map((coinPage: CoinPage) => {
        return coinPage.content
      })
    )
  }
}
