import {Coin} from "../../../position/model/coin";
import {Goal} from "../../../position/model/goal";
import FiatCoinPercentValueRow from "./value/fiat-coin-percent-value.row";
import FiatCoinValueRow from "./value/fiat-coin-value.row";
import FiatPercentValueRow from "./value/fiat-percent-value.row";
import PnlValueRow from "./value/pnl-value.row";

export class GoalRow {
  id!: number
  sellPrice!: FiatPercentValueRow
  sellAmount!: FiatCoinPercentValueRow
  weight!: number
  holdingsRemain!: FiatCoinValueRow
  pnl!: PnlValueRow

  static fromGoal(goal: Goal, coin: Coin): GoalRow {
    const row = new GoalRow()
    row.id = goal.id
    row.sellPrice = FiatPercentValueRow.fromFiatPercentValue(goal.sellPrice)
    row.sellAmount = FiatCoinPercentValueRow.fromFiatCoinPercentValue(goal.sellAmount, coin)
    row.weight = goal.weight
    row.holdingsRemain = FiatCoinValueRow.fromFiatCoinValue(goal.holdingsRemain, coin)
    row.pnl = PnlValueRow.fromPnlValue(goal.pnl)
    return row
  }
}
