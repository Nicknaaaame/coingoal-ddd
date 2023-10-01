import {Coin} from "../../../../position/model/coin";
import FiatCoinPercentValue from "../../../../position/model/value/fiat-coin-percent-value";
import WrapUtil from "../../../../util/wrap-util";

export default class FiatCoinPercentValueRow {
  fiatAmount!: string;
  coinAmount!: string;
  percent!: string;

  static fromFiatCoinPercentValue(value: FiatCoinPercentValue, coin: Coin): FiatCoinPercentValueRow {
    const row = new FiatCoinPercentValueRow();
    row.fiatAmount = WrapUtil.wrapFiat(value.fiatAmount);
    row.coinAmount = WrapUtil.wrapCoin(value.coinAmount, coin.symbol);
    row.percent = WrapUtil.wrapPercent(value.percent, false)
    return row;
  }
}
