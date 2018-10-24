import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public abstract class Utils {
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

    public static <T> void sortByText(List<T> list){
        list.sort((o1, o2) -> o1.toString().compareTo(o2.toString()));
    }
}
