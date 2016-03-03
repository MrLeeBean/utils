package com.nookio.utils.push.pushAction;


import com.alibaba.fastjson.JSON;
import com.nookio.utils.push.model.BaseMessage;
import com.nookio.utils.push.pusher.BaiduPusher;
import com.nookio.utils.push.pusher.BasePusher;
import com.nookio.utils.push.pusher.GetuiPusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by nookio on 16-1-4.
 */
@Component
public class MYBOnlinePushWorker<T> extends PushOnlineWorker<T> {

    Logger logger = Logger.getLogger(MYBOnlinePushWorker.class.getName());

    Map<String, T> feeds = new HashMap<>();

    List<T> saveFeed = new ArrayList<>();

//    @Autowired
//    private IFeedsApi implFeedsApi;

    @Autowired
    BaiduPusher baiduPusher;

    @Autowired
    GetuiPusher getuiPusher;

    @Override
    protected void finalTodo() {
        //implFeedsApi.updatePushedFeeds(saveFeed);
        getuiPusher.finalTodo();
        baiduPusher.finalTodo();
        successCodes.clear();
        feeds.clear();
        saveFeed.clear();
    }

    @Override
    public void successToDo() {
        if (successCodes.size()>0){
            successCodes.forEach(code -> {
//                Feed feed = feeds.get(code);
//                feed.setPushStatus(Feed.SENDED);
//                feed.setSentTime(new Date());
//                feed.setIsParsed(true);
//                saveFeed.add(feed);
                feeds.remove(code);
            });
        }
    }

    @Override
    public void failToDo() {
        feeds.values().parallelStream().forEach(feed -> {
//            feed.setTriedTimes((short) (feed.getTriedTimes() + 1));
//            if (feed.getTriedTimes().intValue() >= Feed.DEFAULT_MAX_RECOUNT.intValue()){
//                feed.setPushStatus(Feed.FAIL);
//            }
            saveFeed.add(feed);
        });
    }

    @Override
    public void onError(Exception e) {
        logger.info("出错了");
        logger.info(e.getMessage());
    }

    @Override
    public void fetchMessage() {
        //todo 需要处理数据串
       // feeds = implFeedsApi.listFeedNeedToPushAndNotParse(Feed.STATUS_NORMAL);
    }

    @Override
    protected boolean checkPusher(BasePusher basePusher) {
        return basePusher.checkTask();
    }

    @Override
    protected boolean push() throws Exception {
        if (checkPusher(baiduPusher)) {
            baiduPusher.push();
            successCodes.addAll(baiduPusher.getSuccessCodes());
        }
        if (checkPusher(getuiPusher)) {
            getuiPusher.push();
            successCodes.addAll(getuiPusher.getSuccessCodes());
        }
        return false;
    }

    @Override
    protected boolean parseMessage() {
        feeds.values().parallelStream().forEach(feed -> {
//            if (Feed.CHANNEL_PUSH_BAIDU.equals(feed.getSendChannel())) {
//                baiduPusher.addTask(parseFeed(feed));
//            } else if (Feed.CHANNEL_PUSH_GERUI.equals(feed.getSendChannel())) {
//                getuiPusher.addTask(parseFeed(feed));
//            }
        });
        return false;
    }

    private BaseMessage parseFeed(T feed){
        BaseMessage baseMessage = new BaseMessage();
//        baseMessage.setAppType(feed.getAppType());
//        baseMessage.setSender(feed.getSourcePartyCode());
//        baseMessage.setSenderType(feed.getSourcePartyType());
//        String json = feed.getContent();
//        Map<String, String> content = JSON.parseObject(json, Map.class);
//        baseMessage.setContent(content.containsKey("text") ? content.get("text") : "");
//        baseMessage.setFromCode(feed.getCode());
//        baseMessage.setPushCode(feed.getPushCode());
//        baseMessage.setPushType(feed.getSendChannel());
//        baseMessage.setType(feed.getParentType());
//        baseMessage.setSubType(feed.getFeedType());
//        baseMessage.setTitle(feed.getTitle());
//        baseMessage.setExtraCodeOne(feed.getAdditional1());
//        baseMessage.setExtraTypeOne(feed.getAdditionalType1());
//        baseMessage.setExtraCodeTwo(feed.getAdditional2());
//        baseMessage.setExtraTypeTwo(feed.getAdditionalType2());
//        baseMessage.setExtraCodeThree(feed.getAdditional3());
//        baseMessage.setExtraTypeThree(feed.getAdditionalType3());
//        baseMessage.setExtraCodeFour(feed.getAdditional4());
//        baseMessage.setExtraTypeFour(feed.getAdditionalType4());
//        baseMessage.setExtraCodeFive(feed.getAdditional5());
//        baseMessage.setExtraTypeFive(feed.getAdditionalType5());
//        if (baseMessage.getSubType().equals(FeedType.TYPE_APP_UPDATE)) {
//            baseMessage.setUrl(feed.getAdditional1());
//        }
//        baseMessage.setOrderCode(feed.getOrderCode());
        return baseMessage;
    }

//    @Override
//    protected void process() throws Exception {
//        Date startTime = new Date();
//        super.process();
//        Date endTime = new Date();
//        //30秒执行一次
//        Long time = endTime.getTime() - startTime.getTime();
//        if (time < 1000 * 30) {
//            Thread.sleep(1000 * 30 - time);
//        }
//    }

}
