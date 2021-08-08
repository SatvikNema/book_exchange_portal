package com.satvik.bookexchange.configuration;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
    
    @Value("${memcached.port}")
    private Integer port;

    @Value("${memcached.ip}")
    private String ip;

    @Bean(name="initMemcachedClient")
    public MemcachedClient memcachedClient(){
        try {
            return new MemcachedClient(new InetSocketAddress(ip,port));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
