export default class WrapUtil {
  public static wrapFiat(value: number, fiat = '$'): string {
    return `${fiat}${value.toLocaleString("en-US", {maximumFractionDigits: 7})}`;
  }

  public static wrapCoin(value: number, symbol: string): string {
    return `${value} ${symbol}`;
  }

  public static wrapPercent(value: number, usePrefix: boolean = true): string {
    let prefix = ''
    if (usePrefix)
      prefix = (value > 0) ? '+' : '-'
    return `${prefix}${Math.abs(value)}%`;
  }

  public static wrapError(err: any): string {
    return err.error.errors ? `Error! ${err.error.errors.join('; ')}!` : `Error! ${err.error.error}`
  }
}
