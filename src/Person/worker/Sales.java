
package Person.worker;

import java.util.ArrayList;
import java.util.HashMap;

public class Sales extends Employee {

    private String type;
    private static ArrayList<Sales> salesArrayList = new ArrayList<>();
    private static HashMap<String, Integer> salesMap = new HashMap<>();

    public Sales(String username, String password) {
        super(username, password, "Sales");
        salesArrayList.add(this);
        salesMap.put(username, getId());
    }

}
