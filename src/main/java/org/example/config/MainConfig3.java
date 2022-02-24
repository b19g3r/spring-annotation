package org.example.config;

import org.example.bean.LazyPerson;
import org.example.bean.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(value = "org.example", useDefaultFilters = false, includeFilters = {
        // @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MainConfig3.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class)
})
public class MainConfig3 {

    /**
     * {@code @Lazy} 懒加载 使用时加载 只加载一次 仅针对单实例bean生效 可用于 {@code @Configuration}  表明该 @Configuration中的所有 @Bean 方法都应该被延迟初始化
     * 可用于 Autowired javax.inject.Inject
     */
    @Bean("lazyPerson")
    @Lazy
    public LazyPerson lazyPerson() {
        System.out.println("lazyPerson inited");
        return new LazyPerson("美美侠", 25);
    }

    @Lazy
    @Bean("person")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person person() {
        System.out.println("person inited");
        return new Person("美美侠", 25);
    }

}
