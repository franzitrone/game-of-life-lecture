package de.uulm.sp.swt.statepattern;

import java.util.List;

public class DeadState implements State {
  @Override
  public State operation(List<State> neighbours) {
    int alive = 0;
    for (var neighbour : neighbours) {
      if (neighbour instanceof AliveState) {
        alive++;
      }
    }

    return (alive == 3) ? new AliveState() : this;
  }
}
