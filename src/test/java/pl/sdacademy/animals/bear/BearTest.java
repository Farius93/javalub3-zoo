package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BearTest {


    @Test
    void bearShouldBeDeadAfter10DaysSinceLastMeal(){
        Bear bear = new BlackBear(1,new TestClock());

        assertTrue(bear.isAlive() == false);

    }

    @Test
    void feedingBearShouldSetFeedingTimeForNow() {
        Bear bear = new BlackBear(1);
        bear.eat(0);

        assertTrue(new Duration(DateTime.now(), bear.getLastMealTime()).isShorterThan(Duration.standardSeconds(1)));

    }

    @Test
    void bearShouldIncreaseMassAfterMeal(){
        Bear bear = new BlackBear(1);
        double oldWeight = bear.getWeight();
        double foodQuantity = 4;

        bear.eat(foodQuantity);

        assertTrue(bear.getWeight() == oldWeight + foodQuantity);
    }

    @Test
    void bearShouldIncreaseMassAfterDrink(){
        Bear bear = new BlackBear(1);
        double oldWeight = bear.getWeight();
        double waterQuantity = 10;

        bear.drink(waterQuantity);
        assertThat(bear.getWeight() == (oldWeight + waterQuantity * 0.75)).isTrue();
//        assertTrue(bear.getWeight() > oldWeight);
    }

    @Test
    void bearShouldDecreaseItsMassAfterPoop(){
        Bear bear = new BlackBear(1);
        double tempWeight = bear.getWeight();
        bear.poop();

        assertThat(((bear.getWeight() * 0.95) - tempWeight) < 0.001).isTrue();



    }

}
