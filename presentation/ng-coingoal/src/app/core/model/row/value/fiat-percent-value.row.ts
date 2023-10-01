import FiatPercentValue from "../../../../position/model/value/fiat-percent-value";
import WrapUtil from "../../../../util/wrap-util";
import PercentRow from "../percent.row";

export default class FiatPercentValueRow {
  fiatAmount!: string
  percentRow!: PercentRow

  static fromFiatPercentValue(value: FiatPercentValue): FiatPercentValueRow {
    const row = new FiatPercentValueRow()
    row.fiatAmount = WrapUtil.wrapFiat(value.fiatAmount)
    row.percentRow = PercentRow.fromPercent(value.percent)
    return row
  }
}
