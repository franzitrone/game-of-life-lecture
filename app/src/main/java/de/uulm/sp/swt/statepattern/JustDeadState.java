package de.uulm.sp.swt.statepattern;

import java.util.List;

public class JustDeadState extends DeadState {
  @Override
  public State operation(List<State> neighbours) {
    var nextState = super.operation(neighbours);
    if (!(nextState instanceof AliveState)) {
      return new DeadState();
    } else {
      return nextState;
    }
  }
}
