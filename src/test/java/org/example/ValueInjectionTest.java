package org.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.config3.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValueInjectionTest {

    private final Log log = LogFactory.getLog(getClass());

    @Test
    public void value() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }

        final Object person = applicationContext.getBean("person");
        System.out.println(person);

        final Object config3 = applicationContext.getBean("config3");
        System.out.println(config3);
    }
}
