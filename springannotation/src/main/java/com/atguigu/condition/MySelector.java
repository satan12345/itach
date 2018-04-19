package com.atguigu.condition;

import com.atguigu.comp.Blue;
import com.atguigu.comp.Yellow;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

//自定义逻辑返回需要导入的组件
public class MySelector implements ImportSelector {
    /* *
     *
     * @author jipeng
     * @Description MySelector
     * @date 2018/4/9 16:51
     * @param [importingClassMetadata]  当前标注@Import注解的类的所有注解信息
     * @return java.lang.String[]  返回值就是导入到容器中的组件的全类名
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
        annotationTypes.forEach(System.out::println);
//        String[] arr=new String[2];
//        arr[0]=Blue.class.getName();
//        arr[1]= Yellow.class.getName();
//        return arr;
        return new String[]{
                Blue.class.getName(),
                Yellow.class.getName()
        };
    }
}
