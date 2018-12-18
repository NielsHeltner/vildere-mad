package food.wilder.persistence.model;

import food.wilder.common.IUserData;

public class UserData implements IUserData {

    private String name;
    private int level;

    public UserData(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

}
