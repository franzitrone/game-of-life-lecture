package de.uulm.sp.swt.statepattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GOL {
  private Random random = new Random(1337);
  private int numCellsX;
  private int numCellsY;
  private State[][] cells;
  private State[][] oldCells;
  private final State outOfBoundsState = new DeadState();

  private View<State> view;

  private GOL(State[][] cells) {
    this.numCellsX = cells[0].length;
    this.numCellsY = cells.length;
    this.cells = cells;
    this.oldCells = new State[cells.length][cells[0].length];
    this.view = new View<State>(cells);
  }

  public int getNumCellsX() {
    return this.numCellsX;
  }

  public int getNumCellsY() {
    return this.numCellsY;
  }

  public View<State> getCells() {
    return this.view;
  }

  public void updateStep() {
    for (int y = 0; y < this.numCellsY; y++) {
      for (int x = 0; x < this.numCellsX; x++) {
        this.oldCells[y][x] = this.cells[y][x];
      }
    }

    List<State> neighbourStates = new ArrayList<>();
    for (int y = 0; y < this.numCellsY; y++) {
      for (int x = 0; x < this.numCellsX; x++) {
        neighbourStates.clear();
        for (int dy = y - 1; dy <= y + 1; dy++) {
          for (int dx = x - 1; dx <= x + 1; dx++) {
            if (dy == y && dx == x) {
              continue;
            }
            if (dy < 0 || dx < 0 || dy >= this.numCellsY || dx >= this.numCellsX) {
              neighbourStates.add(outOfBoundsState);
            } else {
              neighbourStates.add(this.oldCells[dy][dx]);
            }
          }
        }
        this.cells[y][x] = this.oldCells[y][x].operation(neighbourStates);
      }
    }
  }

  public static class View<T> {
    private T[][] cells;

    public View(T[][] cells) {
      this.cells = cells;
    }

    public T get(int x, int y) {
      return this.cells[y][x];
    }
  }

  private static State[] readLine(String line) {
    var length = line.length();
    var states = new State[length];
    for (int i = 0; i < length; ++i) {
      switch (line.charAt(i)) {
        case ' ':
          states[i] = new DeadState();
          break;
        case 'X':
          states[i] = new AliveState();
          break;
        default:
          throw new IllegalArgumentException("Invalid character " + line.charAt(i) + " found.");
      }
    }
    return states;
  }

  public static GOL fromStrings(String line1, String... lines) {
    int width = line1.length();
    if (width == 0) {
      throw new IllegalArgumentException("Line must be at least of length 1.");
    }

    var height = 1 + lines.length;
    State[][] cells = new State[height][];
    cells[0] = readLine(line1);
    for (int i = 0; i < lines.length; ++i) {
      if (lines[i].length() != width) {
        throw new IllegalArgumentException("Lines must be of equal length.");
      }
      cells[i + 1] = readLine(lines[i]);
    }
    return new GOL(cells);
  }
}
