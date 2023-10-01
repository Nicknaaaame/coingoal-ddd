import CoinRow from "../../../core/model/row/coin.row";
import {GoalRow} from "../../../core/model/row/goal.row";
import FiatCoinValueRow from "../../../core/model/row/value/fiat-coin-value.row";
import PnlValueRow from "../../../core/model/row/value/pnl-value.row";
import WrapUtil from "../../../util/wrap-util";
import {Position} from "../position";

export default class PositionRow {
  id!: number
  coin!: CoinRow
  holdings!: FiatCoinValueRow
  avgBuyPrice!: string
  goals!: GoalRow[]
  totalProfit!: PnlValueRow

  static fromPosition(position: Position): PositionRow {
    const row = new PositionRow()
    row.id = position.id
    row.coin = CoinRow.fromCoin(position.coin)
    row.holdings = FiatCoinValueRow.fromFiatCoinValue(position.holdings, position.coin)
    row.avgBuyPrice = WrapUtil.wrapFiat(position.avgBuyPrice)
    row.goals = position.goals.map(value => GoalRow.fromGoal(value, position.coin))
    row.totalProfit = PnlValueRow.fromPnlValue(position.totalProfit)
    return row
  }
}
