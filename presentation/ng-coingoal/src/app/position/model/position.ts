import {Coin} from "./coin";
import {Goal} from "./goal";
import FiatCoinValue from "./value/fiat-coin-value";
import {PnlValue} from "./value/pnl-value";

export interface Position {
  id: number,
  coin: Coin,
  holdings: FiatCoinValue,
  avgBuyPrice: number,
  goals: Goal[],
  totalProfit: PnlValue
}
