package org.example.config3;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Data
public class Config3 {

    /**
     * 注入操作系统属性
     */
    @Value("#{systemProperties['os.name']}")
    private String systemPropertiesName;

    /**
     * 注入SpEL表达式结果
     */
    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    /**
     * 注入其他bean中属性的值，即注入person对象的name属性中的值
     */
    @Value("#{person.name}")
    private String username;

    /**
     * 注入文件资源
     */
    @Value("classpath:/config.properties")
    private Resource resourceFile;

    /**
     * 注入URL资源
     */
    @Value("http://www.baidu.com")
    private Resource url;
}
