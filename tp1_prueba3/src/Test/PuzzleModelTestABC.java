package Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.PuzzleModel;

public class PuzzleModelTestABC {
    private PuzzleModel model;

    @Before
    public void setUp() {
        model = new PuzzleModel(false); // Inicia el modelo con números
    }

    @Test
    public void testIsSolvedCorrectState() {
        // Crea un estado resuelto
        model.getTiles().clear();
        for (int i = 1; i <= 15; i++) {
            model.getTiles().add(String.valueOf(i));
        }
        model.getTiles().add(""); // Agrega el casillero vacío al final

        assertTrue("El estado debe ser resuelto", model.isSolved());
    }

    @Test
    public void testIsSolvedIncorrectState() {
        // Crea un estado no resuelto
        model.getTiles().clear();
        model.getTiles().add("a");
        model.getTiles().add("b");
        model.getTiles().add("c");
        model.getTiles().add(""); // Casillero vacío en posición 3
        model.getTiles().add("d");
        model.getTiles().add("e");
        model.getTiles().add("f");
        model.getTiles().add("g");
        model.getTiles().add("h");
        model.getTiles().add("i");
        model.getTiles().add("j");
        model.getTiles().add("k");
        model.getTiles().add("l");
        model.getTiles().add("m");
        model.getTiles().add("n");
        model.getTiles().add("o");

        assertFalse("El estado no debe ser resuelto", model.isSolved());
    }

    @Test
    public void testIsSolvedEmptyTileFirst() {
        // Estado con el casillero vacío en la primera posición
        model.getTiles().clear();
        model.getTiles().add(""); // Casillero vacío en la primera posición
        for (char c = 'a'; c <= 'o'; c++) {
            model.getTiles().add(String.valueOf(c));
        }

        assertFalse("El estado no debe ser resuelto si el vacío está al inicio", model.isSolved());
    }

    @Test
    public void testIsSolvedEmptyTileLast() {
        // Estado resuelto con el vacío en la última posición
        model.getTiles().clear();
        for (char c = 'a'; c <= 'o'; c++) {
            model.getTiles().add(String.valueOf(c));
        }
        model.getTiles().add(""); // Casillero vacío en la última posición

        assertTrue("El estado debe ser resuelto con el vacío al final", model.isSolved());
    }
}