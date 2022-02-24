package org.example.config;

import org.example.bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * {@code @Import} 快速地导入组件，id默认是组件的全类名
 */
@Configuration
@Import({Color.class, MyImportSelector.class})
public class MainConfig5 {

    @Bean
    public Color color() {
        return new Color();
    }
}
