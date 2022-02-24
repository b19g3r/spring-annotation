package org.example.config;

import org.example.bean.Car;
import org.example.bean.Cat;
import org.example.bean.Person;
import org.example.bean.Pet;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class MainConfig2 {

    /**
     * 单实例: Spring容器在创建的时候，就将@Scope注解标注为singleton的组件进行了实例化，并加载到了Spring容器中 多实例: 在使用时实例化, 每次获取都会生成新的
     */
    @Bean("person")
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Person person() {
        System.out.println("person inited");
        return new Person("美美侠", 25);
    }

    /**
     * 生成两个 bean beanName : scopedTarget.pet -----> beanType：org.example.bean.Pet beanName : pet ----->
     * beanType：com.sun.proxy.$Proxy14
     */
    // @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public Pet pet() {
        return new Pet("pet", 2);
    }

    /**
     * 生成两个 bean beanName : scopedTarget.car -----> beanType：org.example.bean.Car beanName : car ----->
     * beanType：org.example.bean.Car$$EnhancerBySpringCGLIB$$445abdf8
     */
    // @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Car car() {
        return new Car("car", 2);
    }

    /**
     * 自定义 Scope
     */
    @Bean
    @Scope(scopeName = ThreadScope.THREAD_SCOPE)
    public Cat cat() {
        System.out.println("add cat with THREAD_SCOPE");
        return new Cat("cat", 2);
    }

}
