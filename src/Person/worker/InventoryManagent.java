
package Person.worker;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryManagent extends Employee {

    private String type;
    private static ArrayList<InventoryManagent> InventoryManagent = new ArrayList<>();
    private static HashMap<String, Integer> inventoryMap = new HashMap<>();

    public InventoryManagent(String username, String password) {
        super(username, password);
        this.type = "InventoryManagent";
        InventoryManagent.add(this);
        inventoryMap.put(username, getId());
    }

    public String getType() {
        return type;
    }

    public static ArrayList<InventoryManagent> getInventoryManagent() {
        return InventoryManagent;
    }

    public static HashMap<String, Integer> getInventoryMap() {
        return inventoryMap;
    }

}
