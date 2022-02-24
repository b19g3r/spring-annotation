package org.example.config;

import org.example.bean.ColorFactoryBean;
import org.springframework.context.annotation.Bean;

public class MainConfig6 {

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
