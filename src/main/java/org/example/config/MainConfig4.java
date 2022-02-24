package org.example.config;

import org.example.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * {@code @Conditional} 表示满足当前条件，这个类中配置的所有bean注册才能生效
 */
@Configuration
@Conditional({LinuxCondition.class})
public class MainConfig4 {

    /**
     * {@code @Conditional} 表示这个类满足条件时加载
     */
    @Bean("linus")
    @Conditional(LinuxCondition.class)
    public Person person02() {
        return new Person("linus", 48);
    }

    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加咱们这个Person对象...");
        return new Person("美美侠", 25);
    }

    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

}
