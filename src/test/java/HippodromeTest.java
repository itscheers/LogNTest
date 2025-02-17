import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {




    @Test
    void constructor_NullList_ExceptionHasCorrectMessage() {
        String expectedMessage = "Horses cannot be null.";
        List<Horse> horses = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    void constructor_EmptyList_ExceptionHasCorrectMessage() {
        String expectedMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            horses.add(new Horse("Horse" +i, i, i));

        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(25, hippodrome.getHorses().size() );
        assertEquals("Horse0", hippodrome.getHorses().get(0).getName());
        assertEquals("Horse1", hippodrome.getHorses().get(1).getName());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            horses.add(Mockito.mock(Horse.class));

        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses){
            Mockito.verify(hors, Mockito.times(1)).move();
        }


    }

    @Test
    void getWinner() {

        Hippodrome hippodrome = new Hippodrome(List.of(new Horse("horse", 2,20),
                new Horse("horse2", 3,10),
                new Horse("horse3", 2,23)));

        assertEquals("horse3", hippodrome.getWinner().getName());
    }
}