package org.example;

import org.example.config2.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifeCycleTest {

    /**
     * 在Spring容器创建完成时，会自动调用单实例bean的构造方法，对单实例bean进行了实例化操作
     * <p>
     * bean对象的初始化方法调用的时机：对象创建完成，如果对象中存在一些属性，并且这些属性也都赋好值之后，那么就会调用bean的初始化方法。
     * <p>
     * 对于单实例bean来说，在Spring容器创建完成后，Spring容器会自动调用bean的初始化方法；
     * <p>
     * 对于多实例bean来说，在每次获取bean对象的时候，调用bean的初始化方法。
     * <p>
     * bean对象的销毁方法调用的时机：
     * <p>
     * 对于单实例bean来说，在容器关闭的时候，会调用bean的销毁方法；
     * <p>
     * 对于多实例bean来说，Spring容器不会管理这个bean，也就不会自动调用这个bean的销毁方法了。不过，小伙伴们可以手动调用多实例bean的销毁方法。
     */
    @Test
    public void test01() {
        // 1. 创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成");

        // 多实例bean在获取的时候才创建对象
        applicationContext.getBean("car");

        // 关闭容器
        applicationContext.close();

        ///// singleton bean test result:
        // car constructor...
        // car ... init...
        // 容器创建完成
        // car ... destroy...
        //// prototype bean test result
        // 容器创建完成
        // car constructor...
        // car ... init...
    }
}
