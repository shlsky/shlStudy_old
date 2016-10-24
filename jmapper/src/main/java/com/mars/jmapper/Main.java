package com.mars.jmapper;

import com.googlecode.jmapper.JMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengbinjiao on 16/10/22.
 */
public class Main {
    /*static JMapper<Destination, Technology> mapper;
    static{
        mapper = new JMapper<Destination, Technology>(Destination.class, Technology.class, "jmapper.bean.xml/Destination.xml");
    }*/
    @Test public void testJMapper(){
        System.out.println("=========init start========"+System.currentTimeMillis());
        long start = System.currentTimeMillis();

        JMapper<Destination, Technology> mapper;
        mapper = new JMapper<Destination, Technology>(Destination.class, Technology.class, "jmapper.bean.xml/Destination.xml");
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("==========init end======="+System.currentTimeMillis());

        List<Language> languageList = new ArrayList<Language>();
        Language language1 = new Language();
        language1.setName("java");
        languageList.add(language1);
        Language language2 = new Language();
        language2.setName("c++");
        languageList.add(language2);

        SystemS systemS = new SystemS();
        systemS.setNameS("linux");

        Technology technology = new Technology();
        technology.setTechName("TechName");
        technology.setLanguageList(languageList);
        technology.setSystemS(systemS);

        Destination destination = new Destination();

        destination = mapper.getDestination(technology);

        System.out.println(destination);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test public void testJMapper11(){
        for (int i=0;i<10;i++) {
            System.out.println("=========init start========" + System.currentTimeMillis());
            long start = System.currentTimeMillis();

            JMapper<Destination, Technology> mapper;
            mapper = new JMapper<Destination, Technology>(Destination.class, Technology.class, "jmapper.bean.xml/Destination.xml");
            System.out.println(System.currentTimeMillis() - start);
            System.out.println("==========init end=======" + System.currentTimeMillis());


            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }


}
