package com.satvik.bookexchange.service;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.SocketAddress;
import java.util.*;

@Service
@Slf4j
public class MemcachedService {

    private static final int CACHE_EXPIRY = 86400;

    @Autowired
    @Qualifier("initMemcachedClient")
    private MemcachedClient memcachedClient;

    public String testCachingClient(){
        System.out.println("======set/get Mode demonstration===============================");
        memcachedClient.set("FPCACHE",3,"THIS IS TEST");
        System.out.println("Setting and Reading FPCACHE value:"+memcachedClient.get("FPCACHE"));

        memcachedClient.set("FPCACHE",3,"Use SET Cache added to an existing value");
        System.out.println("Read again FPCACHE value:"+memcachedClient.get("FPCACHE"));

        System.out.println("======add Mode demonstration===============================");
        memcachedClient.add("FPCACHE",3,"Use ADD Cache added to an existing value");
        System.out.println("Read again FPCACHE value:"+memcachedClient.get("FPCACHE"));

        memcachedClient.add("FPCACHE2",3,"Use ADD Add to new cache key FPCACHE2 in");
        System.out.println("Read again FPCACHE2 value:"+memcachedClient.get("FPCACHE2"));

        System.out.println("======replace Mode demonstration===============================");
        memcachedClient.replace("FPCACHE",3,"Use Replace replace FPCACHE Key corresponding cache value");
        System.out.println("replace Mode reading FPCACHE value:"+memcachedClient.get("FPCACHE"));

        try {
            Thread.sleep(3001);
        }catch (Exception ex){}
        System.out.println("3 Get the cache again after seconds FPCACHE: "+memcachedClient.get("FPCACHE"));

        System.out.println("======delete Mode demonstration===============================");
        memcachedClient.delete("FPCACHE");
        System.out.println("replace Mode reading FPCACHE value:"+memcachedClient.get("FPCACHE"));

        return "see console";
    }

    public String setSomeKeys(){
        memcachedClient.set("name",CACHE_EXPIRY,"I am learning memcached....");
        memcachedClient.set("party_time", CACHE_EXPIRY, "ALWAYS BOIS");
        memcachedClient.set("party_time_2", CACHE_EXPIRY, "hmmm whenever I get time!");
        return "see console";
    }

    public String setKey(String key, String value){
        memcachedClient.set(key, CACHE_EXPIRY, value);
        log.info("entry added: {}: {}", key, value);
        return "entry added";
    }

    public List<String> getCachedKeys(){
        Set<Integer> slabIds = new HashSet<>();
        Map<SocketAddress, Map<String, String>> stats;
        List<String> keyNames = new ArrayList<>();

        // Gets all the slab IDs
        stats = memcachedClient.getStats("items");
        stats.forEach((socketAddress, value) -> {
            System.out.println("Socket address: "+socketAddress.toString());
            value.forEach((propertyName, propertyValue) -> {
                slabIds.add(Integer.parseInt(propertyName.split(":")[1]));
            });
        });

        // Gets all keys in each slab ID and adds in List keyNames
        slabIds.forEach(slabId -> {
            Map<SocketAddress, Map<String, String>> keyStats = memcachedClient.getStats("cachedump "+slabId+" 0");
            keyStats.forEach((socketAddress, value) -> {
                value.forEach((propertyName, propertyValue) -> {
                    keyNames.add(propertyName);
                });
            });
        });

        System.out.println("number of keys: "+keyNames.size());
        return keyNames;
    }
}
