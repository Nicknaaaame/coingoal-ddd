import {Component, EventEmitter, Input, Output} from '@angular/core';
import {GoalRow} from "../../../core/model/row/goal.row";

@Component({
  selector: 'goal-list',
  templateUrl: './goal-list.component.html'
})
export class GoalListComponent {
  _goalsRows!: GoalRow[]
  @Output() deleteGoalEvent = new EventEmitter<number>();

  @Input()
  set goalRows(value: GoalRow[]) {
    this._goalsRows = value.sort((a, b) => a.weight - b.weight)
  }

  deleteGoal(goalId: number) {
    this.deleteGoalEvent.emit(goalId)
  }
}
