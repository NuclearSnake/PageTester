import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeMeasurerTest {

    @Test
    public void setStartNow() {
        TimeMeasurer timeMeasurer = new TimeMeasurer();
        timeMeasurer.setStartNow();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long elapsed = timeMeasurer.getElapsedMS();
        Assert.assertTrue(elapsed > 0);
        Assert.assertTrue(elapsed > 25);
        Assert.assertTrue(elapsed < 35);
    }

    @Test
    public void getElapsedMS() {
        TimeMeasurer timeMeasurer = new TimeMeasurer();
        timeMeasurer.setStartNow();
        long elapsed = timeMeasurer.getElapsedMS();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long elapsed2 = timeMeasurer.getElapsedMS();
        Assert.assertTrue(elapsed >= 0);
        Assert.assertTrue(elapsed2 >= elapsed);
        Assert.assertTrue(elapsed2 > 25);
        long elapsed3 = timeMeasurer.getElapsedMS();
        Assert.assertTrue(elapsed3 >= elapsed2);
        Assert.assertTrue(elapsed3 < 35);
    }
}