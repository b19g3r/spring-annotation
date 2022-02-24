package org.example.config;

import org.example.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 21024358
 */
@Configuration
public class MainConfig {

    /**
     * {@code @bean}给IOC容器中注册一个bean，类型自然就是返回值的类型，id默认是用方法名作为id
     */
    @Bean
    public Person addedPerson() {
        return new Person("zhangsan", 18);
    }

    /**
     * {@code @bean} 可以使用 name 指定的值作为 bean 的id
     */
    @Bean("namedPerson")
    public Person person() {
        return new Person("named", 18);
    }
}
