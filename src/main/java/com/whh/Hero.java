package com.whh;

import java.io.Serial;
import java.io.Serializable;

public class Hero implements Serializable {
    @Serial
    private static final long serialVersionUID = -8045556716170446941L;
    private String name;
    private Integer level;
    private Long blood;
     public Hero(){}
    public Hero(String name, Integer level, Long blood) {
        this.name = name;
        this.level = level;
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Long getBlood() {
        return blood;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", blood=" + blood +
                '}';
    }
}
