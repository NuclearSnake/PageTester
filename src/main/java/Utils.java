import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>Utils class for common operation</h1>
 *
 * Yes, it violates one responsibility principle in order to increase testability and reduce code repetition
 *
 * @author  Makarenko George
 * @version 1.0
 * @since   2018-10-22
 */
public abstract class Utils {

    /**
     * Creates a new list without duplicates based on the list provided. <p>
     * Compares two items based on {@link Object#toString()}
     *
     * @param list to remove the duplicates from
     * @return <i><b>new</b></i> list without duplicates
     */
    public static <T> List<T> getWithoutStringDuplicates(List<T> list){
        List<T> result = new ArrayList<>();
        boolean duplicate;
        for(T t : list){
            duplicate = false;
            for(T already : result){
                if(already.toString().equals(t.toString())) {
                    duplicate = true;
                    break;
                }
            }
            if(!duplicate)
                result.add(t);
        }

        return result;
    }

    /**
     * Does primitive sorting of a list with the usage of {@link List#sort(Comparator)} method.
     * The comparison is done using {@link Comparator} based on {@link Object#toString()}
     *
     * @param list to be sorted
     * @param <T> generic type inside the list
     */
    public static <T> void sortByText(List<T> list){
        list.sort(Comparator.comparing(Object::toString));
    }
}
