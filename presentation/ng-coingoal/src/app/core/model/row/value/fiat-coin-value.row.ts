import {Coin} from "../../../../position/model/coin";
import FiatCoinValue from "../../../../position/model/value/fiat-coin-value";
import WrapUtil from "../../../../util/wrap-util";

export default class FiatCoinValueRow {
  fiatAmount!: string;
  coinAmount!: string;
  percent!: string;

  static fromFiatCoinValue(value: FiatCoinValue, coin: Coin): FiatCoinValueRow {
    const row = new FiatCoinValueRow();
    row.fiatAmount = WrapUtil.wrapFiat(value.fiatAmount);
    row.coinAmount = WrapUtil.wrapCoin(value.coinAmount, coin.symbol);
    return row;
  }
}
