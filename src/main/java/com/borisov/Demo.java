package com.borisov;

import com.borisov.services.DecodingSystem;
import com.borisov.services.EncodingSystem;
import com.borisov.services.decoding.DecodingSystemImpl;
import com.borisov.services.encoding.EncodingSystemImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Demo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Demo.class);
        DecodingSystem decodingSystem = context.getBean(DecodingSystemImpl.class);
        EncodingSystem encodingSystem = context.getBean(EncodingSystemImpl.class);
        encodingSystem.encode();
    }
}
