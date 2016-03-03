package com.nookio.utils.push.parse;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by nookio on 16/1/24.
 */
public class FeedParse<F,T> extends BaseParse<F, T> {
    @Override
    protected List<F> fetchMetaData() {
        return null;
    }

    @Override
    protected List<T> setResultData() {
        return null;
    }

    @Override
    protected boolean saveResultData() {
        return false;
    }
//
//    @Autowired
//    private IFeedsApi implFeedsApi;
//
//    @Autowired
//    private ICommonApi implCommonApi;
//
//    @Autowired
//    private ImplPlatformApi implPlatformApi;
//
//    @Override
//    protected List<Feed> fetchMetaData() {
//        return implFeedsApi.getFeedNeedSendAndNotParsed();
//    }
//
//    @Override
//    protected List<Notification> setResultData() {
//        metaData.parallelStream().forEach(feed -> {
//            //List<Notification> notifications = parseNotification(feed, feed.getToPartyCode(), feed.getToPartyType());
//           // resultData.addAll(notifications);
//        });
//        return null;
//    }
//
//    @Override
//    protected boolean saveResultData() {
//        implFeedsApi.addNotifications(resultData);
//        return true;
//    }


}
