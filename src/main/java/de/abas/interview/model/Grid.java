package de.abas.interview.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

    private List<Cell> cells;

    public Grid(List<Cell> cells) {
        this.cells = cells;
    }

    public void nextGeneration() {
        expandGrid();
        List<Cell> cells = new ArrayList<>();
        for (Cell cell : this.cells) {
            cells.add(new Cell(cell.getPosX(), cell.getPosY(), cell.isAlive()));
        }
        for (int i = 0; i < cells.size(); i++) {
            int aliveNeighbors = getAliveNeighbors(cells.get(i), cells);
            if (cells.get(i).isAlive()) {
                if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                    this.cells.get(i).setAlive(false);
                }
            } else {
                if (aliveNeighbors == 3) {
                    this.cells.get(i).setAlive(true);
                }
            }
        }
        cleanupGrid();
    }

    private void cleanupGrid() {
        cleanupRows(this.cells.get(0).getPosY());
        cleanupRows(this.cells.get(this.cells.size() - 1).getPosY());
        cleanupColumns(this.cells.get(0).getPosX());
        cleanupColumns(this.cells.get(this.cells.size() - 1).getPosX());
    }

    private void cleanupRows(int posY) {
        boolean allDead = true;
        int posX = this.cells.get(0).getPosX();
        Cell cell;
        List<Cell> markedForRemoval = new ArrayList<>();
        while ((cell = getCell(posX + ":" + posY, this.cells)) != null) {
            if (cell.isAlive()) {
                allDead = false;
                break;
            }
            markedForRemoval.add(cell);
            posX++;
        }
        if (allDead) {
            this.cells.removeAll(markedForRemoval);
        }
    }

    private void cleanupColumns(int posX) {
        boolean allDead = true;
        int posY = this.cells.get(0).getPosY();
        Cell cell;
        List<Cell> markedForRemoval = new ArrayList<>();
        while ((cell = getCell(posX + ":" + posY, this.cells)) != null) {
            if (cell.isAlive()) {
                allDead = false;
                break;
            }
            markedForRemoval.add(cell);
            posY++;
        }
        if (allDead) {
            this.cells.removeAll(markedForRemoval);
        }
    }

    private void expandGrid() {
        ArrayList<Cell> expandedGrid = new ArrayList<>();
        for (Cell cell : this.cells) {
            if (getCell((cell.getPosX() - 1) + ":" + (cell.getPosY() - 1), this.cells) == null && getCell((cell.getPosX() - 1) + ":" + (cell.getPosY() - 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() - 1, cell.getPosY() - 1, false));
            }
            if (getCell((cell.getPosX() - 1) + ":" + cell.getPosY(), this.cells) == null && getCell((cell.getPosX() - 1) + ":" + cell.getPosY(), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() - 1, cell.getPosY(), false));
            }
            if (getCell((cell.getPosX() + 1) + ":" + (cell.getPosY() - 1), this.cells) == null && getCell((cell.getPosX() + 1) + ":" + (cell.getPosY() - 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() + 1, cell.getPosY() - 1, false));
            }
            if (getCell(cell.getPosX() + ":" + (cell.getPosY() - 1), this.cells) == null && getCell(cell.getPosX() + ":" + (cell.getPosY() - 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX(), cell.getPosY() - 1, false));
            }
            if (getCell((cell.getPosX() + 1) + ":" + cell.getPosY(), this.cells) == null && getCell((cell.getPosX() + 1) + ":" + cell.getPosY(), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() + 1, cell.getPosY(), false));
            }
            if (getCell((cell.getPosX() - 1) + ":" + (cell.getPosY() + 1), this.cells) == null && getCell((cell.getPosX() - 1) + ":" + (cell.getPosY() + 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() - 1, cell.getPosY() + 1, false));
            }
            if (getCell(cell.getPosX() + ":" + (cell.getPosY() + 1), this.cells) == null && getCell(cell.getPosX() + ":" + (cell.getPosY() + 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX(), cell.getPosY() + 1, false));
            }
            if (getCell((cell.getPosX() + 1) + ":" + (cell.getPosY() + 1), this.cells) == null && getCell((cell.getPosX() + 1) + ":" + (cell.getPosY() + 1), expandedGrid) == null) {
                expandedGrid.add(new Cell(cell.getPosX() + 1, cell.getPosY() + 1, false));
            }
        }
        this.cells.addAll(expandedGrid);
        Collections.sort(this.cells);

    }

    private int getAliveNeighbors(Cell cell, List<Cell> cells) {
        int aliveNeighbors = 0;
        List<String> neighbors = cell.getNeighbors();
        for (String neighbor : neighbors) {
            Cell neighborCell = getCell(neighbor, cells);
            if (neighborCell != null && neighborCell.isAlive()) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }

    private Cell getCell(String position, List<Cell> cells) {
        for (Cell cell : cells) {
            if (cell.hasPosition(position)) {
                return cell;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.isAlive()) {
                string = string.concat("1");
            } else {
                string = string.concat("0");
            }
            if (i + 1 < cells.size() && cells.get(i + 1).getPosY() > cell.getPosY()) {
                string = string.concat("\n");
            }
        }
        return string;
    }
}
