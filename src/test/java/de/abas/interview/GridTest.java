package de.abas.interview;

import de.abas.interview.model.Cell;
import de.abas.interview.model.Grid;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridTest {

    @Test
    public void initGrid() {
        // 000
        // 010
        // 000
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(0, 0, false));
        cells.add(new Cell(1, 0, false));
        cells.add(new Cell(2, 0, false));
        cells.add(new Cell(0, 1, false));
        cells.add(new Cell(1, 1, true));
        cells.add(new Cell(2, 1, false));
        cells.add(new Cell(0, 2, false));
        cells.add(new Cell(1, 2, false));
        cells.add(new Cell(2, 2, false));

        Grid grid = new Grid(cells);

        String display = grid.toString();
        assertThat(display, is("000\n010\n000"));
    }

}
