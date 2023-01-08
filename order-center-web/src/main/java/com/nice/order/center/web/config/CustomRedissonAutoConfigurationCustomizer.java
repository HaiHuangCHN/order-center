package com.nice.order.center.web.config;

import org.redisson.config.BaseMasterSlaveServersConfig;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.spring.starter.RedissonAutoConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;

/**
 * Redisson自定义配置
 */
@Component
public class CustomRedissonAutoConfigurationCustomizer implements RedissonAutoConfigurationCustomizer {

    /**
     * 从节点最小空闲连接数
     */
    @Value("${redisson.slaveConnectionMinimumIdleSize:1}")
    private int slaveConnectionMinimumIdleSize;

    /**
     * 主节点最小空闲连接数
     */
    @Value("${redisson.masterConnectionMinimumIdleSize:1}")
    private int masterConnectionMinimumIdleSize;

    /**
     * 连接空闲超时
     */
    @Value("${redisson.idleConnectionTimeout:15000}")
    private int idleConnectionTimeout;

    private final RedisProperties redisProperties;

    @Autowired
    public CustomRedissonAutoConfigurationCustomizer(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Override
    public void customize(Config configuration) {
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        if (cluster != null) {
            BaseMasterSlaveServersConfig<ClusterServersConfig> clusterServersConfig = configuration.useClusterServers();
            clusterServersConfig.setMasterConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);
            clusterServersConfig.setSlaveConnectionMinimumIdleSize(slaveConnectionMinimumIdleSize);
            clusterServersConfig.setIdleConnectionTimeout(idleConnectionTimeout);
        }
    }

}
