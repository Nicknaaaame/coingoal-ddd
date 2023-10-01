import FiatCoinPercentValue from "./value/fiat-coin-percent-value";
import FiatCoinValue from "./value/fiat-coin-value";
import FiatPercentValue from "./value/fiat-percent-value";
import {PnlValue} from "./value/pnl-value";

export interface Goal {
  id: number,
  sellPrice: FiatPercentValue,
  sellAmount: FiatCoinPercentValue,
  weight: number,
  holdingsRemain: FiatCoinValue,
  pnl: PnlValue
}
