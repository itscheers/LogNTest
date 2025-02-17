import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void constructor_NullName_ExceptionHasCorrectMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2, 2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "   "})
    void constructor_BlankName_ExceptionHasCorrectMessage(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 2, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    @Test
    void constructor_NegativeSpeed_ExceptionHasCorrectMessage() {
        int speed = -2;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Spirit", speed, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void constructor_NegativeDist_ExceptionHasCorrectMessage() {
        int speed = 3;
        int distance = -4;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Spirit", speed, distance));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }



    @Test
    void getName() {
        String name = "Cherry";
        Horse horse = new Horse(name, 50, 100);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeed() {
        int speed = 2;
        Horse horse = new Horse("Gamma", speed, 100);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        int distance = 23;
        Horse horse = new Horse("JuiceWRLD", 50, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {

            Horse horse = new Horse("Zheka", 2,4);

            horse.move();


            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }

    }


    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.8, 12})
    public void moveCalcByFormule(double FakeRandom){
        double min = 0.2;
        double max = 0.9;
        String name = "Grisha";
        double speed = 2.5;
        double distance = 30;

        Horse horse = new Horse(name, speed, distance);

        double expectedDist = distance + speed * FakeRandom;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(FakeRandom);


            horse.move();

        }
        assertEquals(expectedDist, horse.getDistance());

    }


}