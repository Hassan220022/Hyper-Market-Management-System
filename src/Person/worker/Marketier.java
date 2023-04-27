package Person.worker;

import java.util.ArrayList;
import java.util.HashMap;

public class Marketier extends Employee {

    private String type;
    private static ArrayList<Marketier> marketieArrayList = new ArrayList<>();
    private static HashMap<String, Integer> markMap = new HashMap<>();

    public Marketier(String username, String password) {
        super(username, password);
        this.type = "Marketier";
        marketieArrayList.add(this);
        markMap.put(username, getId());
    }
}