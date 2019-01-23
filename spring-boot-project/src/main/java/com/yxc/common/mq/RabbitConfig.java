package com.yxc.common.mq;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    public RabbitConfig() {
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(this.addresses);
        connectionFactory.setPort(this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setPublisherConfirms(this.publisherConfirms);
        return connectionFactory;
    }

    //自动创建队列 config.getQueueName()
    @Bean
    public Queue queue() {
        return new Queue("my.queue",true,false,false);
    }



    /* 针对消费者配置
       FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
       HeadersExchange ：通过添加属性key-value匹配
       DirectExchange:按照routingkey分发到指定队列
       TopicExchange:多关键字匹配 config.getExchangeName()
    */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("my.Exchange");
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with("my.Routing");
    }

    //=============================这是一套==========================================//
    @Bean
    public Queue queueUserInsert() {
        return new Queue("queueUserInsert",true,false,false);
    }
    @Bean
    public TopicExchange topicExchangeUserInsert() {
        return new TopicExchange("exchangeUsrInsert");
    }
    @Bean
    public Binding bindingUserInsertQueue() {
        return BindingBuilder.bind(queueUserInsert()).to(topicExchangeUserInsert()).with("routingKeyUsrInsert");
    }
    //=============================这是一套==========================================//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory());
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        return rabbitTemplate;
//    }
}
