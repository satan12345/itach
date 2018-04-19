package com.atguigu.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
/* *
 *
 * @author jipeng
 * @Description 自定义过滤规则
 * @date 2018/4/8 19:57
 * @param
 * @return
 */
public class MyFilter implements TypeFilter {
    /* *
     *
     * @author jipeng
     * @Description MyFilter
     * @date 2018/4/8 19:57
     * @param [metadataReader,the metadata reader for the target class
     * 读取当前类的元数据的reader
     *
      * metadataReaderFactory]
      * a factory for obtaining metadata readers
     * for other classes (such as superclasses and interfaces)
     * 一个包含其他类的元数据读取器的工厂
     * @return boolean
     */
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();//获取当前类的主键信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();//获取当前正在扫描的类的信息
        Resource resource = metadataReader.getResource();//获取当前类的资源信息
        String className = classMetadata.getClassName();//获取当前类的类名

        System.out.println("------>"+className);
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
