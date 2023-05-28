package User;

public enum UserType {
    admin, sales, inventory, marketing;

    public static UserType getUserType(int index) {
        UserType[] types = UserType.values();
        if (index >= 0 && index < types.length) {
            return types[index];
        } else {
            throw new IllegalArgumentException("Invalid user type index");
        }
    }

    public static int getUserTypeIndex(String type) {
        UserType[] types = UserType.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].name().equalsIgnoreCase(type)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid user type: " + type);
    }
}
