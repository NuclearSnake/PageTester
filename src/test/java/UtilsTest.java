import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getWithoutStringDuplicates_NoDuplicates() {
        List<String> initial = new ArrayList<>();
        initial.add("bla");
        initial.add("bla-");
        initial.add("bla-b");
        initial.add("bla-bl");
        initial.add("bla-bla");

        List<String> newList = Utils.getWithoutStringDuplicates(initial);

        Collections.sort(initial);
        Collections.sort(newList);

        Assert.assertEquals(initial, newList);
    }

    @Test
    public void getWithoutStringDuplicates_SeveralDuplicates() {
        List<String> initial = new ArrayList<>();
        initial.add("bla");
        initial.add("bla-");
        initial.add("bla-"); // duplicate 1
        initial.add("bla-b");
        initial.add("bla-bl");
        initial.add("bla-bla");
        initial.add("bla-bla"); // duplicate 2

        List<String> newList = Utils.getWithoutStringDuplicates(initial);

        Collections.sort(initial);
        Collections.sort(newList);

        Assert.assertNotEquals(initial, newList);
        Assert.assertEquals(initial.size()-2, newList.size());
    }

    @Test
    public void getWithoutStringDuplicates_NotString() {
        List<Long> initial = new ArrayList<>();
        initial.add(1L);
        initial.add(2L);
        initial.add(2L); // duplicate 1
        initial.add(3L);
        initial.add(4L);
        initial.add(5L);
        initial.add(5L); // duplicate 2

        List<Long> newList = Utils.getWithoutStringDuplicates(initial);

        Collections.sort(initial);
        Collections.sort(newList);

        Assert.assertNotEquals(initial, newList);
        Assert.assertEquals(initial.size()-2, newList.size());
    }

    @Test
    public void getWithoutStringDuplicates_AllDuplicates() {
        List<String> initial = new ArrayList<>();
        initial.add("bla");
        initial.add("bla");
        initial.add("bla");
        initial.add("bla");
        initial.add("bla");
        initial.add("bla");
        initial.add("bla");

        List<String> newList = Utils.getWithoutStringDuplicates(initial);

        Collections.sort(initial);
        Collections.sort(newList);

        Assert.assertNotEquals(initial, newList);
        Assert.assertEquals(1, newList.size());
        Assert.assertEquals("bla", newList.get(0));
    }

    @Test
    public void sortByText_SortedAlready() {
        List<String> initial = new ArrayList<>();
        initial.add("abc");
        initial.add("acc");
        initial.add("acd");
        initial.add("zaa");
        initial.add("zab");
        initial.add("zbc");
        initial.add("zca");
        initial.add("zzz");

        List<String> newList = new ArrayList<>(initial);

        Utils.sortByText(newList);

        Assert.assertEquals(initial, newList);
    }

    @Test
    public void sortByText_NotSorted() {
        List<String> sorted = new ArrayList<>();
        sorted.add("abc");
        sorted.add("acc");
        sorted.add("acd");
        sorted.add("zaa");
        sorted.add("zab");
        sorted.add("zbc");
        sorted.add("zca");
        sorted.add("zzz");

        List<String> initial = new ArrayList<>();
        initial.add("zzz");
        initial.add("acc");
        initial.add("zab");
        initial.add("zca");
        initial.add("abc");
        initial.add("zaa");
        initial.add("zbc");
        initial.add("acd");

        List<String> newList = new ArrayList<>(initial);

        Utils.sortByText(newList);

        Assert.assertEquals(sorted, newList);
    }

    @Test
    public void sortByText_NotString() {
        List<Integer> sorted = new ArrayList<>();
        sorted.add(1);
        sorted.add(2);
        sorted.add(3);
        sorted.add(4);
        sorted.add(5);
        sorted.add(6);
        sorted.add(7);
        sorted.add(8);

        List<Integer> initial = new ArrayList<>();
        initial.add(3);
        initial.add(6);
        initial.add(4);
        initial.add(8);
        initial.add(5);
        initial.add(2);
        initial.add(1);
        initial.add(7);

        List<Integer> newList = new ArrayList<>(initial);

        Utils.sortByText(newList);

        Assert.assertEquals(sorted, newList);
    }
}