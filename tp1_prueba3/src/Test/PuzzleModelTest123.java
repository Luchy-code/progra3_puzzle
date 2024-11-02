package Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.PuzzleModel;

public class PuzzleModelTest123 {
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
        model.getTiles().add("1");
        model.getTiles().add("2");
        model.getTiles().add("3");
        model.getTiles().add(""); // Casillero vacío en posición 3
        model.getTiles().add("4");
        model.getTiles().add("5");
        model.getTiles().add("6");
        model.getTiles().add("7");
        model.getTiles().add("8");
        model.getTiles().add("9");
        model.getTiles().add("10");
        model.getTiles().add("11");
        model.getTiles().add("12");
        model.getTiles().add("13");
        model.getTiles().add("14");
        model.getTiles().add("15");

        assertFalse("El estado no debe ser resuelto", model.isSolved());
    }

    @Test
    public void testIsSolvedEmptyTileFirst() {
        // Estado con el casillero vacío en la primera posición
        model.getTiles().clear();
        model.getTiles().add(""); // Casillero vacío en la primera posición
        for (int i = 1; i <= 15; i++) {
            model.getTiles().add(String.valueOf(i));
        }

        assertFalse("El estado no debe ser resuelto si el vacío está al inicio", model.isSolved());
    }

    @Test
    public void testIsSolvedEmptyTileLast() {
        // Estado resuelto con el vacío en la última posición
        model.getTiles().clear();
        for (int i = 1; i <= 15; i++) {
            model.getTiles().add(String.valueOf(i));
        }
        model.getTiles().add(""); // Casillero vacío en la última posición

        assertTrue("El estado debe ser resuelto con el vacío al final", model.isSolved());
    }
}