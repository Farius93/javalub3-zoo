package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BearTest {

    @Test
    void bearShouldBeAliveIfHasEatenWithin10Days() {
        Bear bear = new BlackBear(1);
        bear.eat();

        assertThat(bear.isAlive()).isTrue();
    }

    @Test
    void feedingBearShouldSetTheDateOfTheLastMealForNow() {
        Bear bear = new BlackBear(1);
        bear.eat();

        assertThat(new Duration(bear.getLastMealTime(), DateTime.now()).isShorterThan(Duration.standardSeconds(1)))
                .isTrue();
    }

    @Test
    void bearShouldNotBeAliveIfItHasNotEatenForMoreThan10Days() {
        Bear bear = new BlackBear(1, new TestClock());

        boolean result = bear.isAlive();

        assertThat(result).isFalse();
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
        double oldWeight = bear.getWeight();
        bear.eat(4);

        assertTrue(bear.getWeight() > oldWeight);
    }

    @Test
    void bearShouldIncreaseMassAfterDrink(){
        Bear bear = new BlackBear(1);
        double oldWeight = bear.getWeight();

        bear.drink(10);
//        assertTrue(bear.getWeight() > oldWeight);
        assertThat(bear.getWeight() > oldWeight).isTrue();
    }
}
