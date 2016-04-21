package com.shl.generic;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shl.generic.APIResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackson on 16/4/12.
 */
public class Gsonmain {

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<APIResult<Person>> apiResult = new ArrayList<APIResult<Person>>();
        for (int i =0 ;i<3;i++){
            Person person = new Person("shl"+i,i);
            APIResult<Person> personAPIResult = new APIResult<Person>();
            personAPIResult.setData(person);
            apiResult.add(personAPIResult);
        }
        String str = JSON.toJSONString(apiResult);
        try {
            List<APIResult<Person>> tem = (List<APIResult<Person>>) gson.fromJson(str,new TypeToken<List<APIResult<Person>>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("success");
    }
}
