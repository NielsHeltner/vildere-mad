package food.wilder.domain;

public class UserData {

    private String name;
    private int level;

    public String getName() {
        return name;
    }

    public UserData setName(String name) {
        this.name = name;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public UserData setLevel(int level) {
        this.level = level;
        return this;
    }
}
