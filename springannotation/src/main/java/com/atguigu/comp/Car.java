package com.atguigu.comp;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class Car implements EmbeddedValueResolverAware{

    public Car(){
        System.out.println("car constructor");
    }

    public void init(){
        System.out.println("car init");
    }

    public void destory(){
        System.out.println("car destory");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("你好,${os.name},我是#{12*10}");
        System.out.println(s);
    }
}
