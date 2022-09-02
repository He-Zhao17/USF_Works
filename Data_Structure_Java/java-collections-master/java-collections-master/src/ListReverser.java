import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ListReverser {

    public static List<Type> listReverser(List<Type> input) {
        List<Type> temp = new LinkedList<>();
        Stack<Type> st = new Stack<>();
        if (input.size() == 0) {
            return temp;
        }
        for (int i = 0; i < input.size(); i++) {
            st.push(input.get(0));
        }
        for (int i = 0; i < st.size(); i++) {
            temp.add(st.pop());
        }
        return temp;
    }

}
