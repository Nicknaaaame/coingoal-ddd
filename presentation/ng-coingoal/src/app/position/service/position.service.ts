import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {GoalRequest} from "../../core/model/request/goal.request";
import {PositionPatchRequest} from "../../core/model/request/position-patch.request";
import {PositionRequest} from "../../core/model/request/position.request";
import {PositionPage} from "../model/page/position.page";
import {Position} from "../model/position";

@Injectable({providedIn: 'root'})
export class PositionService {
  constructor(private readonly http: HttpClient) {
  }

  fetchPositionPage(pageIndex?: number): Observable<PositionPage> {
    return this.http.get<PositionPage>(`${environment.apiUrl}/api/v1/position`, {
      params: new HttpParams()
        .set('page', pageIndex ?? 0)
        .set('sort', 'id,asc'),
    });
  }

  fetchPosition(positionId: number): Observable<Position> {
    return this.http.get<Position>(`${environment.apiUrl}/api/v1/position/${positionId}`);
  }

  addPosition(positionRequest: PositionRequest): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/api/v1/position`, positionRequest)
  }

  patchPosition(positionRequest: PositionPatchRequest): Observable<Position> {
    return this.http.patch<Position>(`${environment.apiUrl}/api/v1/position`, positionRequest)
  }

  deletePosition(positionId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/position/${positionId}`)
  }

  addGoal(positionId: number, goalRequest: GoalRequest): Observable<number> {
    return this.http.post<number>(`${environment.apiUrl}/api/v1/position/${positionId}/goal`, goalRequest);
  }

  deleteGoal(positionId: number, goalId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/api/v1/position/${positionId}/goal/${goalId}`)
  }
}
