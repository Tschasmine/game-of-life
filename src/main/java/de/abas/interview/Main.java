package de.abas.interview;

import de.abas.interview.model.Cell;
import de.abas.interview.model.Grid;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 000
        // 010
        // 000
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0, true));
        cells.add(new Cell(1, 0, true));
        cells.add(new Cell(2, 0, true));
        cells.add(new Cell(0, 1, false));
        cells.add(new Cell(1, 1, true));
        cells.add(new Cell(2, 1, false));
        cells.add(new Cell(0, 2, false));
        cells.add(new Cell(1, 2, false));
        cells.add(new Cell(2, 2, true));

        Grid grid = new Grid(cells);

        System.out.println(grid.toString());
        System.out.println("\n");

        for (int i = 0; i < 50; i++) {
            grid.nextGeneration();
            System.out.println(grid.toString());
            System.out.println("\n");
        }

    }

}
