package org.example.config;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {

    /**
     * @param metadataReader        读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到其他任何类的信息的工厂
     * @return whether this filter matches
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        final ClassMetadata classMetadata = metadataReader.getClassMetadata();
        final boolean isDao = classMetadata.getClassName().contains("dao");

        final MetadataReader reader = metadataReaderFactory.getMetadataReader("org.example.dao.BookDao");
        reader.getClassMetadata();

        String className = classMetadata.getClassName();
        System.out.println("--->" + className);
        System.out.println("--->" + isDao);

        return isDao;
    }
}
