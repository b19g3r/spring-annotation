package org.example;

import org.example.bean.Person;
import org.example.config.MainConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * {@code @bean} 测试
     */
    @Test()
    public void beanTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        final Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        beansOfType.forEach((key, value) -> System.out.println(key + "" + value));

        // Person这个类型的组件在IOC容器中的名字是什么呢？
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }
}
