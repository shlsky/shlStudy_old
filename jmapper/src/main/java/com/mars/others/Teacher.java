package com.mars.others;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by chengbinjiao on 16/10/24.
 */
@Data
public class Teacher {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int age;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
