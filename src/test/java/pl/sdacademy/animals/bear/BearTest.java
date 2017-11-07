package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearTest {

    @Test
    void bearShouldBeAliveIfHasEatenWithin10Days() {
        Bear bear = new BlackBear(1);
        bear.eat();

        boolean result = bear.isAlive();

        assertTrue(result == true);
    }

    @Test
    void feedingBearShouldSetFeedingTimeForNow() {
        Bear bear = new BlackBear(1);
        bear.eat();

        assertTrue(new Duration(DateTime.now(), bear.getLastMealTime()).isShorterThan(Duration.standardSeconds(1)));

    }

    @Test
    void bearShouldBeDeadAfter10DaysSinceLastMeal(){
        Bear bear = new BlackBear(1,new TestClock());

        assertTrue(bear.isAlive() == false);

    }

    @Test
    void bearShouldIncreaseMassAfterMeal(){
        Bear bear = new BlackBear(1);
        int oldWeight = bear.getWeight();
        bear.eat(4);

        assertTrue(bear.getWeight() > oldWeight);
    }

    @Test
    void bearShouldIncreaseMassAfterDrink(){
        Bear bear = new BlackBear(1);
        int oldWeight = bear.getWeight();

        bear.drink(10);
        assertTrue(bear.getWeight() > oldWeight);
    }
}
