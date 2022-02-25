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
     * <p>
     * Spring为bean提供了两种初始化的方式，第一种方式是实现InitializingBean接口（也就是要实现该接口中的afterPropertiesSet方法），第二种方式是在配置文件或@Bean注解中通过
     * initMethod来指定，这两种方式可以同时使用，同时使用先调用afterPropertiesSet方法，后执行initMethod指定的方法。 如果两种配置指定的是同一方法则只会执行一次
     * <p>
     * 实现InitializingBean接口是直接调用afterPropertiesSet()方法，与通过反射调用init-method指定的方法相比，效率相对来说要高点。但是init-method方式消除了对Spring的依赖
     * <p>
     * 实现org.springframework.beans.factory.DisposableBean接口的bean在销毁前，Spring将会调用DisposableBean接口的destroy()方法
     * <p>
     * 先调用 destroy() 再 调用 destroyMethod
     * <p>
     * destroy() 和 destroyMethod 都可以在销毁 bean 时做一些收尾工作。前者与Spring耦合高，使用类型强转.方法名()，效率高；后者耦合低，使用反射，效率相对来说较低
     * <p>
     * 初始化:: construct -> @PostConstruct -> InitializingBean.afterPropertiesSet -> initMethod => (inuse)
     * <p>
     * 销毁::   preDestroy -> DisposableBean.destroy -> destroyMethod => (freed)
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
