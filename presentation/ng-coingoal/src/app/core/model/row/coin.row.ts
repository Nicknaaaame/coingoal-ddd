import {Coin} from "../../../position/model/coin";
import WrapUtil from "../../../util/wrap-util";
import PercentRow from "./percent.row"

export default class CoinRow {
  price!: string
  name!: string
  symbol!: string
  change24h!: PercentRow

  static fromCoin(coin: Coin): CoinRow {
    const coinRow = new CoinRow()
    coinRow.price = WrapUtil.wrapFiat(coin.price)
    coinRow.name = coin.name
    coinRow.symbol = coin.symbol
    coinRow.change24h = PercentRow.fromPercent(coin.change24h)
    return coinRow
  }
}
