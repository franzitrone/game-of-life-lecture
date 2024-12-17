package de.uulm.sp.swt.statepattern;

import java.util.List;

public interface State {
  /**
   * Returns the new state of a cell.
   * 
   * @param neighbours The list of neighbouring cell states.
   * @return the new state of the cell according to the game rules.
   */
  public State operation(List<State> neighbours);
}
