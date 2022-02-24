package org.example;

import org.example.bean.Person;
import org.example.config.MainConfig;
import org.example.config.MainConfig2;
import org.example.config.ThreadScope;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void iocTest() {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        // 我们现在就来看一下IOC容器中有哪些bean，即容器中所有bean定义的名字
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

    /**
     * {@code @Scope proxyMode} test
     */
    @Test
    @Ignore
    public void iocTestScopeProxyMode() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        // 获取到的这个Person对象默认是单实例的，因为在IOC容器中给我们加的这些组件默认都是单实例的，
        // 所以说在这儿我们无论多少次获取，获取到的都是我们之前new的那个实例对象
        Person person = (Person) applicationContext.getBean("person");
        Person person2 = (Person) applicationContext.getBean("person");
        System.out.println(person == person2);

        final String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEachOrdered(System.out::println);

        Arrays.stream(beanDefinitionNames).forEach(beanName -> {
            Class<?> beanType = applicationContext.getType(beanName);
            System.out.printf("beanName : %s -----> beanType：%s \n", beanName, beanType.getName());
        });
    }

    @Test
    public void iocTestScope() {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("spring started");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        Person person2 = (Person) applicationContext.getBean("person");
        System.out.println(person2);
        System.out.println(person == person2);
    }

    /**
     * THREAD_SCOPE: bean在同样的线程中获取到的是同一个bean的实例，不同的线程中bean的实例是不同的
     */
    @Test
    public void testCustomScope() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfig2.class);
        // 向容器中注册自定义的Scope
        applicationContext.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());

        // 使用容器获取bean
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("person"));
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("person"));
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
