package de.uulm.sp.swt.statepattern;

import java.util.List;

public interface State {
  public State operation(List<State> neighbours);
}
