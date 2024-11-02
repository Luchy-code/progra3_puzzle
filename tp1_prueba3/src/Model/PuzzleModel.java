package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleModel {
    private List<String> tiles;
    private int emptyTileIndex;
    private final int size = 4; 
    private List<Integer> history; // Para deshacer movimientos

    public PuzzleModel(boolean isAlphabet) {
        tiles = new ArrayList<>();
        history = new ArrayList<>();
       if (isAlphabet) {
            for (char c = 'a'; c <= 'o'; c++) {
                tiles.add(String.valueOf(c));
            }
        } else {
            for (int i = 1; i <= 15; i++) {
                tiles.add(String.valueOf(i));
            }
        }
        tiles.add(""); // empty tile
        shuffle(); //si se comenta esta linea el juego ya esta ganado
    }

    private void shuffle() {
        Collections.shuffle(tiles);
        emptyTileIndex = tiles.indexOf("");
    }

    public List<String> getTiles() {
        return tiles;
    }

    public boolean moveTile(int index) {
        if (isAdjacent(emptyTileIndex, index)) {
            history.add(emptyTileIndex); // Guardar el índice del vacío antes de mover
            Collections.swap(tiles, emptyTileIndex, index);
            emptyTileIndex = index;
            return true;
        }
        return false;
    }
    
    public boolean moveTileUp() {
        int newIndex = emptyTileIndex - size;
        if (newIndex >= 0) { // Asegura que el nuevo índice esté dentro de los límites
            return moveTile(newIndex);
        }
        return false;
    }

    public boolean moveTileDown() {
        int newIndex = emptyTileIndex + size;
        if (newIndex < tiles.size()) { // Asegura que el nuevo índice esté dentro de los límites
            return moveTile(newIndex);
        }
        return false;
    }

    public boolean moveTileLeft() {
        int newIndex = emptyTileIndex - 1;
        if (emptyTileIndex % size > 0 && newIndex >= 0) { // Asegura que el nuevo índice esté dentro de los límites
            return moveTile(newIndex);
        }
        return false;
    }

    public boolean moveTileRight() {
        int newIndex = emptyTileIndex + 1;
        if (emptyTileIndex % size < size - 1 && newIndex < tiles.size()) {
            return moveTile(newIndex);
        }
        return false;
    }

    public boolean undoMove() {
        if (!history.isEmpty()) {
            int previousEmptyIndex = history.remove(history.size() - 1);
            Collections.swap(tiles, emptyTileIndex, previousEmptyIndex);
            emptyTileIndex = previousEmptyIndex;
            return true;
        }
        return false;
    }

    private boolean isAdjacent(int emptyIndex, int tileIndex) {
        int emptyRow = emptyIndex / size;
        int emptyCol = emptyIndex % size;
        int tileRow = tileIndex / size;
        int tileCol = tileIndex % size;
        return (Math.abs(emptyRow - tileRow) == 1 && emptyCol == tileCol) ||
               (Math.abs(emptyCol - tileCol) == 1 && emptyRow == tileRow);
    }
    
    public boolean isSolved() {
        // Comprobar si el estado actual es resuelto para números o letras
        return isSolvedNumeric() || isSolvedAlphabetic();
    }

    private boolean isSolvedNumeric() {
        List<String> solvedState = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            solvedState.add(String.valueOf(i));
        }
        solvedState.add(""); // Agregar el casillero vacío al final

        return tiles.equals(solvedState);
    }

    private boolean isSolvedAlphabetic() {
        List<String> solvedState = new ArrayList<>();
        for (char c = 'a'; c <= 'o'; c++) {
            solvedState.add(String.valueOf(c));
        }
        solvedState.add(""); // Agregar el casillero vacío al final

        return tiles.equals(solvedState);
    }




}
