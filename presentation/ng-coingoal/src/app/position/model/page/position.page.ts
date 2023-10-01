import {Position} from "../position";

export interface PositionPage {
  content: Position[],
  pageable: any,
  numberOfElements: number,
  totalPages: number,
  totalElements: number
}
