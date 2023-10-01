import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NotificationType} from "../../../core/model/notification.enum";
import {GoalRequest} from "../../../core/model/request/goal.request";
import {PositionPatchRequest} from "../../../core/model/request/position-patch.request";
import {NotificationsService} from "../../../core/service/notification.service";
import WrapUtil from "../../../util/wrap-util";
import {Position} from "../../model/position";
import PositionRow from "../../model/row/position.row";
import {PositionService} from "../../service/position.service";

@Component({
  selector: 'position-row',
  templateUrl: './position-row.component.html'
})
export class PositionRowComponent implements OnInit {
  @Input() position!: Position
  @Output() deletePositionEvent = new EventEmitter<number>()
  positionRow!: PositionRow
  public editPositionForm!: FormGroup
  public saveGoalForm!: FormGroup

  constructor(
    private readonly fb: FormBuilder,
    private readonly notificationService: NotificationsService,
    private readonly positionService: PositionService) {
  }

  ngOnInit(): void {
    this.positionRow = PositionRow.fromPosition(this.position)
    this.editPositionForm = this.createEditPositionForm();
    this.saveGoalForm = this.createSaveGoalForm();
  }

  editPosition(): void {
    const request: PositionPatchRequest = {
      positionId: this.position.id,
      holdings: this.editPositionForm.value.holdings,
      avgBuyPrice: this.editPositionForm.value.avgBuyPrice
    }
    this.positionService.patchPosition(request).subscribe({
      next: newPosition => {
        this.notificationService.show({
          text: `Successfully updated position with id [${newPosition.id}]!`,
          type: NotificationType.SUCCESS
        })
        this.updatePosition(newPosition)
      },
      error: err => {
        this.notificationService.show({
          text: WrapUtil.wrapError(err),
          type: NotificationType.ERROR
        })
      }
    })
  }

  deletePosition(): void {
    this.deletePositionEvent.emit(this.position.id)
  }

  addGoal(): void {
    const request: GoalRequest = {
      sellPrice: this.saveGoalForm.value.price,
      sellAmount: this.saveGoalForm.value.amount
    }
    this.positionService.addGoal(this.position.id, request).subscribe({
      next: goalId => {
        this.notificationService.show({
          text: `Successfully added goal with id [${goalId}]!`,
          type: NotificationType.SUCCESS
        })
        this.positionService.fetchPosition(this.position.id).subscribe({
          next: positionResponse => {
            this.updatePosition(positionResponse)
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

  deleteGoal(goalId: number) {
    this.positionService.deleteGoal(this.position.id, goalId).subscribe({
      next: res => {
        this.notificationService.show({
          text: `Successfully deleted goal with id [${goalId}]!`,
          type: NotificationType.SUCCESS
        })
        this.positionService.fetchPosition(this.position.id).subscribe({
          next: positionResponse => {
            this.updatePosition(positionResponse)
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

  updatePriceInput(): void {
    const percent = this.saveGoalForm.value.pricePercent
    this.saveGoalForm.patchValue({
      price: (percent / 100) * this.position.avgBuyPrice + this.position.avgBuyPrice
    })
  }

  updatePricePercentInput(): void {
    const price = this.saveGoalForm.value.price
    this.saveGoalForm.patchValue({
      pricePercent: (price - this.position.avgBuyPrice) / this.position.avgBuyPrice * 100
    })
  }

  updateAmountInput(): void {
    const percent = this.saveGoalForm.value.amountPercent
    this.saveGoalForm.patchValue({
      amount: (percent / 100) * this.position.holdings.coinAmount
    })
  }

  updateAmountPercentInput(): void {
    const amount = this.saveGoalForm.value.amount
    this.saveGoalForm.patchValue({
      amountPercent: (100 * amount) / this.position.holdings.coinAmount
    })
  }

  private createEditPositionForm(): FormGroup {
    return this.fb.group({
      holdings: this.fb.control(this.position.holdings.coinAmount, Validators.required),
      avgBuyPrice: this.fb.control(this.position.avgBuyPrice, Validators.required),
    });
  }

  private createSaveGoalForm(): FormGroup {
    return this.fb.group({
      price: this.fb.control('', Validators.required),
      amount: this.fb.control('', Validators.required),
      pricePercent: this.fb.control('', Validators.required),
      amountPercent: this.fb.control('', Validators.required),
    });
  }

  private updatePosition(value: Position): void {
    this.position = value
    this.positionRow = PositionRow.fromPosition(this.position)
  }
}
