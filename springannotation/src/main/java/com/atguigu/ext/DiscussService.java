package com.atguigu.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DiscussService {
    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent applicationEvent){
        System.out.println("discussService监听到的事件:"+applicationEvent);

    }
}
