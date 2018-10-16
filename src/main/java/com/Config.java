package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by java技术.
 */
@Configuration("name")//表示这是一个配置信息类,可以给这个配置类也起一个名称
//@ComponentScan("spring4")//类似于xml中的<context:component-scan base-package="spring4"/>
public class Config {

//    @Autowired//自动注入，如果容器中有多个符合的bean时，需要进一步明确
//    @Qualifier("compent")//进一步指明注入bean名称为compent的bean
//    private Compent compent;
//
//    @Autowired
//    @Qualifier("newbean")
//    private Compent newbean;

    @Bean//类似于xml中的<bean id="newbean" class="spring4.Compent"/>
    public Compent newbean(){
        return new Compent();
    }

    public void print(){
        System.out.println("i am beanname");
//        compent.show();
//        newbean.show();
    }
}