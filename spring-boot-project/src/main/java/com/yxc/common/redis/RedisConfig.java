package com.yxc.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean("myRedisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        //设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //把key String化，把value 序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //连接Redis
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
    /**
     *生成key的策略
     *设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
     * 使用：进行分割，可以很多显示出层级关系
     * className.methodName[参数]
     * **/
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator(){
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(".");
//                sb.append(method.getName());
//                sb.append("[");
//                for (Object obj : params) {
//                    sb.append(String.valueOf(obj));
//                }
//                sb.append("]");
//                return sb.toString();
//            }
//        } ;
//    }
    /**
     * 管理缓存
     * 1、生成一个默认配置，通过config对象即可对缓存进行自定义配置
     * 2、设置缓存的默认过期时间，也是使用Duration设置, 设置为30分钟
     * 3、设置一个初始化的缓存空间set集合, 就是注解@Cacheable(value = "my-redis-cache2")中的value值,
     * 4、对每个缓存空间应用不同的配置
     * 5、使用自定义的缓存配置初始化一个cacheManager
     * **/
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory) {
//        //对缓存进行自定义配置
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        //设置缓存的默认过期时间，也是使用Duration设置, 设置为30分钟
//        config = config.entryTtl(Duration.ofMinutes(30)).disableCachingNullValues();//不缓存空值
//        // 设置一个初始化的缓存空间set集合, 就是注解@Cacheable(value = "my-redis-cache2")中的value值,
//        Set<String> cacheNames = new HashSet<String>();
//        cacheNames.add("my-redis-cache0");
//        cacheNames.add("my-redis-cache1");
//        //对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<String, RedisCacheConfiguration>(16);
//        configMap.put("my-redis-cache0", config);
//        // 设置过期时间为 30s
//        configMap.put("my-redis-cache1", config.entryTtl(Duration.ofSeconds(30)));
//
//        // 使用自定义的缓存配置初始化一个cacheManager
//        return RedisCacheManager.builder(factory)
//                // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                .initialCacheNames(cacheNames)
//                .withInitialCacheConfigurations(configMap)
//                .transactionAware()
//                .build();
//    }
    /**
     * RedisTemplate配置 自定义<String,Object>的
     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        //设置序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
//                new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 配置redisTemplate
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        // key序列化
//        redisTemplate.setKeySerializer(stringSerializer);
//        // value序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        // Hash key序列化
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        // Hash value序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
    /**
     * 异常处理，当Redis发生异常时，打印日志，但是程序正常走
     * **/
//    @Override
//    @Bean
//    public CacheErrorHandler errorHandler() {
//        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
//        return new CacheErrorHandler() {
//            //get异常
//            @Override
//            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
//                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
//            }
//            //set异常
//            @Override
//            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
//                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
//            }
//            //回收异常
//            @Override
//            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
//                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
//            }
//            //清除异常
//            @Override
//            public void handleCacheClearError(RuntimeException e, Cache cache) {
//                log.error("Redis occur handleCacheClearError：", e);
//            }
//        };
//    }
    /**
     * 读取properties的配置信息初始化JedisConnectionFactory和JedisPool，
     * 以供外部类初始化缓存管理器使用
     * **/
//    @ConfigurationProperties
//    class DataJedisProperties {
//        @Value("${spring.redis.host}")
//        private String host;
//        @Value("${spring.redis.password}")
//        private String password;
//        @Value("${spring.redis.port}")
//        private int port;
//        @Value("${spring.redis.timeout}")
//        private int timeout;
//        @Value("${spring.redis.jedis.pool.max-idle}")
//        private int maxIdle;
//        @Value("${spring.redis.jedis.pool.max-wait}")
//        private long maxWaitMillis;
//        @Value("${spring.redis.jedis.pool.max-active}")
//        private int maxTotal;
//        @Bean
//        JedisConnectionFactory jedisConnectionFactory() {
//            log.info("Create JedisConnectionFactory successful");
//            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//            redisStandaloneConfiguration.setHostName(host);
//            redisStandaloneConfiguration.setPort(port);
//            redisStandaloneConfiguration.setDatabase(0);
//            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//
//            JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//            jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
//            return new JedisConnectionFactory(redisStandaloneConfiguration,
//                    jedisClientConfiguration.build());
//        }
//
//        @Bean
//        public JedisPool redisPoolFactory() {
//            log.info("JedisPool init successful，host -> [{}]；port -> [{}]", host, port);
//            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//            jedisPoolConfig.setMaxIdle(maxIdle);
//            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//            jedisPoolConfig.setMaxTotal(maxTotal);
//            return new JedisPool(jedisPoolConfig, host, port, timeout, password);
//        }
//    }
}

