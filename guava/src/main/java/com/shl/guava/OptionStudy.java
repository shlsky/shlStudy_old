package com.shl.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created by jackson on 16/4/17.
 */
public class OptionStudy {

    private String name = "shl";

    private Integer age = 25;
    public void guavatest() throws  Exception{
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());

        Preconditions.checkArgument(possible.isPresent(),"Null");

        System.out.println(Objects.toStringHelper(this));

    }
    public OptionStudy(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public static void main(String[] args) {

        OptionStudy optionStudy = new OptionStudy("shl",25);
        try {
            optionStudy.guavatest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
