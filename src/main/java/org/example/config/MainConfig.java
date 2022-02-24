package org.example.config;

import org.example.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * {@code @Configuration} 标识配置类
 * <p>
 * {@code @ComponentScan} value 指定要扫描的包
 * @author 21024358
 */
@Configuration
@ComponentScans(
        value = {
                @ComponentScan(value = "org.example", useDefaultFilters = false, excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)
                })
        }
)
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
    @Scope
    public Person person() {
        return new Person("named", 18);
    }
}
