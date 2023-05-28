package User;

public enum UserType {
    admin, sales, inventory, marketing;

    public static String getUserTypeString(int index) {
        UserType[] types = UserType.values();
        if (index >= 0 && index < types.length) {
            return types[index].name();
        } else {
            throw new IllegalArgumentException("Invalid user type index");
        }
    }

    public static UserType getUserType(String type) {
        try {
            return UserType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user type: " + type);
        }
    }

    public static String getUserType(UserType type) {
        return type.name();
    }
}
