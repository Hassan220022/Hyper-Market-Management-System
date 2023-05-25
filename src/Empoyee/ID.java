package Empoyee;

import java.util.*;

class ID {
    private static Map<String, Integer> idCountMap = new HashMap<>();

    private static int getIdPrefix(String type) {
        switch (type) {
            // Define the ID prefix for each employee type
            case "User":
                return 1;
            case "Marketer":
                return 2;
            case "Sales":
                return 3;
            case "InventoryManager":
                return 4;
            case "Admin":
                return 5;
            default:
                return 0;
        }
    }

    public static int generateID(String type) {
        int count = idCountMap.getOrDefault(type, 0) + 1;
        idCountMap.put(type, count);

        int uniqueID = getIdPrefix(type) * 1000 + count;
        return uniqueID;
    }
}