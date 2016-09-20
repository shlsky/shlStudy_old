package com.shl.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jackson on 16/8/22.
 */
public class ShlCache {

    @Getter @Setter
    public LoadingCache<String, List<String>> cacheLoader;

    private Cache callback;

    public ShlCache(){
        cacheLoader = CacheBuilder.newBuilder()
                .maximumSize(100l)
                .weakValues()
                .expireAfterAccess(5, TimeUnit.SECONDS) //一分钟失效
                .concurrencyLevel(8)
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String s) throws Exception {
                        return Lists.newArrayList("builder","builder");
                    }
                });

    }

    /**
     * 此处使用的是callback机制
     * @param key
     * @return
     * @throws ExecutionException
     */
    public List<String> callback(final String key) throws ExecutionException {
        return cacheLoader.get(key, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return Lists.newArrayList("callback","callback");
            }
        });
    }

    /**
     * 此处使用的是callback机制
     * @param key
     * @return
     * @throws ExecutionException
     */
    public List<String> builder(final String key) throws ExecutionException {
        cacheLoader.get(key).add("builder add");
        return cacheLoader.get(key);
    }


    /**
     * 此例说明callback和Loader不可以同时使用
     * @param key
     * @return
     * @throws ExecutionException
     */
    @Deprecated
    public List<String> callbackAndLoader(final String key) throws ExecutionException {
        return cacheLoader.get(key, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return cacheLoader.get(key);
            }
        });
    }

    public void testExpire(ShlCache shlCache){

        try {
            System.out.println(shlCache.getCacheLoader().get("key"));
            System.out.println(shlCache.getCacheLoader().get("key1"));
            Thread.sleep(3000);

            System.out.println("-------------");
            System.out.println(shlCache.getCacheLoader().get("key"));
            Thread.sleep(3000);

            System.out.println("-------------");
            System.out.println("key value isn't expire so key's value="+ shlCache.getCacheLoader().get("key"));
            System.out.println("key1 value is expire so key1's value is default="+ shlCache.getCacheLoader().get("key1"));

            Thread.sleep(10000);
            System.out.println("-------------");
            System.out.println(shlCache.getCacheLoader().get("key"));
            System.out.println(shlCache.getCacheLoader().get("key1"));
            System.out.println(shlCache.getCacheLoader().get("key2"));


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(shlCache.getCacheLoader().size());



    }
    public static void main(String[] args) {
        ShlCache shlCache = new ShlCache();

        try {
            System.out.println(shlCache.callback("callback"));

            System.out.println(shlCache.builder("builder"));




        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(shlCache.getCacheLoader().size());

    }
}
