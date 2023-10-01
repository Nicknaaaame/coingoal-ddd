import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError, concat, debounceTime, distinctUntilChanged, Observable, of, Subject, switchMap, tap} from "rxjs";
import {NotificationType} from "../../core/model/notification.enum";
import {PositionRequest} from "../../core/model/request/position.request";
import {NotificationsService} from "../../core/service/notification.service";
import WrapUtil from "../../util/wrap-util";
import {Coin} from "../model/coin";
import {PositionPage} from "../model/page/position.page";
import {CoinService} from "../service/coin.service";
import {PositionService} from "../service/position.service";

@Component({
  selector: 'position',
  templateUrl: './position-page.component.html'
})
export class PositionPageComponent {
  positionPage: PositionPage = {} as PositionPage
  coinSelect$!: Observable<Coin[]>
  selectCoins: Coin[] = []
  coinSelectLoading = false
  coinSelectInput$ = new Subject<string>()
  selectedCoin!: number
  public form!: FormGroup

  public constructor(
    private readonly fb: FormBuilder,
    private readonly notificationService: NotificationsService,
    private readonly positionService: PositionService,
    private readonly coinService: CoinService
  ) {
    positionService.fetchPositionPage().subscribe(value => {
      this.positionPage = value
    })
    this.form = this.createForm()
  }

  loadCoins(): void {
    const defaultValues = this.coinService.fetchCoins()
    this.coinSelect$ = concat(
      defaultValues,
      this.coinSelectInput$.pipe(
        distinctUntilChanged(),
        debounceTime(800),
        tap(() => this.coinSelectLoading = true),
        switchMap(name => {

          return this.coinService.fetchCoins(name).pipe(
            catchError(() => of([])),
            tap(() => this.coinSelectLoading = false),
            tap(value => this.selectCoins = value)
          )
        })
      )
    )
  }

  addPosition() {
    const request: PositionRequest = {
      coinId: this.form.value.coinId,
      holdings: this.form.value.holdings,
      avgBuyPrice: this.form.value.avgBuyPrice
    }
    this.positionService.addPosition(request).subscribe({
      next: goalId => {
        this.notificationService.show({
          text: `Successfully added goal with id [${goalId}]!`,
          type: NotificationType.SUCCESS
        })
        this.positionService.fetchPositionPage().subscribe({
          next: positionResponse => {
            this.positionPage = positionResponse
          }
        })
      },
      error: err => {
        this.notificationService.show({
          text: WrapUtil.wrapError(err),
          type: NotificationType.ERROR
        })
      }
    })
  }

  deletePosition(positionId: number): void {
    this.positionService.deletePosition(positionId).subscribe({
        next: res => {
          this.notificationService.show({
            text: `Successfully deleted position with id [${positionId}]!`,
            type: NotificationType.SUCCESS
          })
          this.positionService.fetchPositionPage().subscribe({
            next: positionResponse => {
              this.positionPage = positionResponse
            }
          })
        },
        error: err => {
          this.notificationService.show({
            text: WrapUtil.wrapError(err),
            type: NotificationType.ERROR
          })
        }
      }
    )
  }

  private createForm(): FormGroup {
    return this.fb.group({
      coinId: this.fb.control('', Validators.required),
      holdings: this.fb.control('', Validators.required),
      avgBuyPrice: this.fb.control('', Validators.required),
    });
  }
}
