package com.shl.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by jackson on 16/4/19.
 */
public class ListMain {

    public static void main(String[] args) {
        List<OptionStudy> filterTest = Lists.newArrayList(new OptionStudy("shl",25),new OptionStudy("shl",26));

        for (OptionStudy _i : filterTest){
            System.out.println(_i.toString());
        }

        List<OptionStudy> filter =Lists.newArrayList(Iterables.filter(filterTest, new Predicate<OptionStudy>() {
            @Override
            public boolean apply(OptionStudy input) {
                if (input.getAge()>25)
                    return true;
                return false;
            }
        }));

        for (OptionStudy _i : filter){

            System.out.println(_i.toString()+_i.getAge());
        }

        System.out.println(filter.size());
    }

}
