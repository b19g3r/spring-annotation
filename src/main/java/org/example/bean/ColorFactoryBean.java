package org.example.bean;

import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {

    /**
     * 返回一个Color对象，这个对象会添加到容器中
     * @see org.springframework.beans.factory.BeanFactory.FACTORY_BEAN_PREFIX
     */
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean.getObject..");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        System.out.println("ColorFactoryBean.getObjectType..");
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
