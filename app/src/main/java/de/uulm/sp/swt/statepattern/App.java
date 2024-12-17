package de.uulm.sp.swt.statepattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements Runnable, ActionListener {
  private final int CELL_SIZE = 5;
  private boolean[][] cells;

  private Timer timer = new Timer(1000 / 10, this);
  private JFrame frame = new JFrame();
  private GOLCanvas canvas = null;

  private GOL game;

  @Override
  public void run() {
    this.game = GOL.fromStrings(
        "                                      ",
        "                         X            ",
        "                       X X            ",
        "             XX      XX            XX ",
        "            X   X    XX            XX ",
        " XX        X     X   XX               ",
        " XX        X   X XX    X X            ",
        "           X     X       X            ",
        "            X   X                     ",
        "             XX                       ",
        "                                      ",
        "                                      ",
        "                                      ",
        "                                      ",
        "                                      ",
        "                                      ",
        "                                      ");

    this.canvas = new GOLCanvas(this.game, CELL_SIZE);
    this.frame.setSize(this.game.getNumCellsX() * CELL_SIZE,
        this.game.getNumCellsY() * CELL_SIZE + 30);
    this.frame.add(this.canvas);
    this.frame.setVisible(true);

    timer.start();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new App());
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == timer) {
      this.game.updateStep();
      this.canvas.repaint();
      this.frame.getToolkit().sync();
    }
  }
}
