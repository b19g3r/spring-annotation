package org.example.config2;

import org.example.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("org.example.config2")
@Configuration
public class MainConfigOfLifeCycle {

    // @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Car car() {
        return new Car();
    }
}
