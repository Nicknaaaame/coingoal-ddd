import {Coin} from "../coin";

export interface CoinPage {
  content: Coin[],
  pageable: any,
  numberOfElements: number,
  totalPages: number,
  totalElements: number
}
