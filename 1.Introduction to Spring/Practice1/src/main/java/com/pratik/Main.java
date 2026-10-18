package com.pratik;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CakeBaker baker = context.getBean(CakeBaker.class);

        baker.bakeCake();
    }
}