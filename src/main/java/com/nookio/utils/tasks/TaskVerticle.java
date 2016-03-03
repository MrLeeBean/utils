package com.nookio.utils.tasks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by yeeson on 24/2/16.
 */
public class TaskVerticle extends AbstractVerticle {

    public TaskVerticle(final ApplicationContext context) {
//        feedService = (IFeedsApi) context.getBean("implFeedsApi");
    }

    @Override
    public void start() throws Exception {
        super.start();
        HttpServer server = vertx.createHttpServer();
//        server.requestHandler(req -> {
//            if (req.method() == HttpMethod.GET && req.path().equals("/info")) {
//                List<Feed> feedList = feedService.getFeedListByShop("", "SHOP201602040000002", 8, "NORMAL", 1);
//                for (Feed feed : feedList) {
//                    System.out.println(feed.getCode());
//                    System.out.println(feed.getShopName());
//                }
//                req.response().setChunked(true);
//                req.response().setStatusCode(200).write("feeds-micro-alive").end();
//            }
//        });
        server.listen(8888);
        System.out.println("-----server start------");
    }
}
