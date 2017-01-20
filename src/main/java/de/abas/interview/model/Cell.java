package de.abas.interview.model;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Comparable<Cell> {

    private boolean alive;

    private int posX;
    private int posY;

    public Cell(int posX, int posY, boolean alive) {
        this.posX = posX;
        this.posY = posY;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getPosition() {
        return posX + ":" + posY;
    }

    public boolean hasPosition(String position) {
        if (getPosition().equals(position)) {
            return true;
        }
        return false;
    }

    public List<String> getNeighbors() {
        ArrayList<String> neighbors = new ArrayList<>();
        neighbors.add((posX - 1) + ":" + (posY - 1));
        neighbors.add((posX) + ":" + (posY - 1));
        neighbors.add((posX + 1) + ":" + (posY - 1));
        neighbors.add((posX - 1) + ":" + (posY));
        neighbors.add((posX + 1) + ":" + (posY));
        neighbors.add((posX + 1) + ":" + (posY + 1));
        neighbors.add((posX) + ":" + (posY + 1));
        neighbors.add((posX - 1) + ":" + (posY + 1));
        return neighbors;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public int compareTo(Cell cell) {
        if (this.getPosY() > cell.getPosY()) {
            return 1;
        } else if (this.getPosY() < cell.getPosY()) {
            return -1;
        } else {
            if (this.getPosX() > cell.getPosX()) {
                return 1;
            } else if (this.getPosX() < cell.getPosX()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
