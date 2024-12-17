package de.uulm.sp.swt.statepattern;

import java.util.List;

public class AliveState implements State {
  @Override
  public State operation(List<State> neighbours) {
    int alive = 0;
    for (var neighbour : neighbours) {
      if (neighbour instanceof AliveState) {
        alive++;
      }
    }

    State nextState = this;
    if (alive < 2 || alive > 3) {
      nextState = new JustDeadState();
    }
    return nextState;
  }
}
