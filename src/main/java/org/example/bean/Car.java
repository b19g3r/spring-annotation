package org.example.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Car implements InitializingBean, DisposableBean {

    private String name;
    private Integer age;

    public Car(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Car() {
        System.out.println("car constructor...");
    }

    /**
     * @see preDes
     */
    @PostConstruct
    public void postConstruct() {
        System.out.println("car @PostConstruct");
        //// test result :: construct -> @PostConstruct -> InitializingBean.afterPropertiesSet -> initMethod => inuse
        //// @PreDestroy -> DisposableBean.destroy -> destroyMethod => freed
        // destroy() 再 调用 destroyMethod 指定的方法
        // car @PostConstruct...
        // car postConstruct
        // car: InitializingBean.afterPropertiesSet..
        // car ... initMethod...
        // 容器创建完成
        // car preDestroy
        // car ... DisposableBean.destroy...
        // car ... destroyMethod...
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("car @PreDestroy");
    }

    public void initMethod() {
        System.out.println("car ... initMethod...");
    }

    /**
     * @see DisposableBeanAdapter#destroy()
     */
    @Override
    public void destroy() {
        System.out.println("car ... DisposableBean.destroy...");
        //// test result: 先调用 destroy() 再 调用 destroyMethod 指定的方法
        // car constructor...
        // car: InitializingBean.afterPropertiesSet..
        // car ... initMethod...
        // 容器创建完成
        // car ... DisposableBean.destroy...
        // car ... destroyMethod...
    }

    public void destroyMethod() {
        System.out.println("car ... destroyMethod...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

    /**
     * @see AbstractAutowireCapableBeanFactory#invokeInitMethods(java.lang.String, java.lang.Object,
     *         org.springframework.beans.factory.support.RootBeanDefinition)
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("car: InitializingBean.afterPropertiesSet..");

        //// 此时 org.example.BeanLifeCycleTest.test01 测试结果如下； 即先调用 afterPropertiesSet 再调用 initMethod 指定的方法,此点可以在 org
        //// .springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods 源码中印证
        // car constructor...
        // car: InitializingBean.afterPropertiesSet..
        // car ... initMethod...
        // 容器创建完成
        // car ... destroyMethod...
    }
}
