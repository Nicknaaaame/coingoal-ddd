import WrapUtil from "../../../util/wrap-util";

export default class PercentRow {
  value!: string
  color!: string

  static fromPercent(percent: number): PercentRow {
    const row = new PercentRow()
    row.value = WrapUtil.wrapPercent(percent)
    row.color = row.value.startsWith('-') ? 'red' : 'green'
    return row
  }
}
