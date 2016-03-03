package com.nookio.utils.tasks;

import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yeeson on 24/2/16.
 */
public class TaskServer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-micro-feeds.xml");
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new TaskVerticle(context));
    }
}
