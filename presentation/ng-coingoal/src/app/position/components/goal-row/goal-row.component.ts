import {Component, EventEmitter, Input, Output} from '@angular/core';
import {GoalRow} from "../../../core/model/row/goal.row";

@Component({
  selector: 'goal-row',
  templateUrl: './goal-row.component.html'
})
export class GoalRowComponent {
  @Input() goalRow!: GoalRow
  @Output() deleteGoalEvent = new EventEmitter<number>();

  deleteGoal() {
    this.deleteGoalEvent.emit(this.goalRow.id)
  }
}
