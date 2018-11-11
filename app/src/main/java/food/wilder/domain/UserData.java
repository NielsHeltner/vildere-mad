package food.wilder.domain;

public class UserData {

    private String name;
    private int level;

    public UserData(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
