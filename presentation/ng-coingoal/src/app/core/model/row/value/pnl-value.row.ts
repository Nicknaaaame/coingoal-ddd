import {PnlValue} from "../../../../position/model/value/pnl-value";
import WrapUtil from "../../../../util/wrap-util";
import PercentRow from "../percent.row";

export default class PnlValueRow {
  percent!: PercentRow
  value!: string

  static fromPnlValue(value: PnlValue): PnlValueRow {
    const row = new PnlValueRow()
    if (!value)
      return row
    row.percent = PercentRow.fromPercent(value.percent)
    row.value = WrapUtil.wrapFiat(value.fiatAmount)
    return row
  }
}
