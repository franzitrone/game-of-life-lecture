package de.uulm.sp.swt.statepattern;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GOLCanvas extends Canvas {
  private GOL game;
  private int cellSize;

  private Random _random = new Random(1337);

  public GOLCanvas(GOL game, int cellSize) {
    this.game = game;
    this.cellSize = cellSize;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    int numX = this.game.getNumCellsX();
    int numY = this.game.getNumCellsY();
    g.clearRect(0, 0, numX * this.cellSize, numY * this.cellSize);
    for (int y = 0; y < numY; ++y) {
      for (int x = 0; x < numX; ++x) {
        State cell = game.getCells().get(x, y);
        if (cell instanceof AliveState) {
          g.setColor(Color.BLACK);
        } else if (cell instanceof JustDeadState) {
          g.setColor(Color.LIGHT_GRAY);
        } else {
          g.setColor(Color.WHITE);
        }
        g.fillRect(x * this.cellSize, y * this.cellSize, this.cellSize, this.cellSize);
      }
    }
  }
}
