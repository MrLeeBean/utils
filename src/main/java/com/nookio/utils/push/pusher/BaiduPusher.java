package com.nookio.utils.push.pusher;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.nookio.utils.push.model.BaseMessage;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by nookio on 16/1/4.
 */
@Component
public class BaiduPusher extends BasePusher<BaseMessage> {

    Logger logger = Logger.getLogger(BaiduPusher.class.getName());

    static String apiKey = "";
    static String secretKey = "";


    @Override
    public boolean pushOne(BaseMessage baseMessage) throws Exception {
        baiduPush(baseMessage);
        return true;
    }

    @Override
    public boolean push() throws Exception {
        for (BaseMessage baseMessage : tasks) {
            String appType = baseMessage.getAppType();
            //这个地方的错误信息需要处理
            baiduPush(baseMessage);
        }
        tasks.clear();
        return false;
    }

    @Override
    public String getMessage(BaseMessage baseMessage) {
        return "{\"title\":\"" + baseMessage.getTitle() + "\",\"description\":\""
                + baseMessage.getContent() + "\",\"open_type\": 2,\"aps\":{\"alert\":\"" + baseMessage.getContent() +
                "\",\"sound\":\"\",\"badge\":0}}";
    }

    private void baiduPush(BaseMessage baseMessage) throws ChannelClientException, ChannelServerException {

        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);
        //创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        //若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                logger.info("baidu push event message:" + event.getMessage());
            }
        });

        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setDeviceType(Integer.valueOf(baseMessage.getAppType())); // device_type => 1: web 2: pc 3:android 4:ios 5:wp

        //todo 这里还欠缺
        //request.setChannelId(channelId);
        request.setUserId(baseMessage.getPushCode());

        request.setMessageType(1);

        request.setMessage(getMessage(baseMessage));

        //调用pushMessage接口
        PushUnicastMessageResponse response = channelClient
                .pushUnicastMessage(request);

        //认证推送成功
        logger.info("baidu push amount : " + response.getSuccessAmount());
    }

    public boolean pushshs() {
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        //创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        //若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                logger.info("baidu push event message:" + event.getMessage());
            }
        });

        try {
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
//            request.setDeviceType(deviceType); // device_type => 1: web 2: pc 3:android 4:ios 5:wp
//            request.setChannelId(channelId);
//            request.setUserId(userId);

            request.setMessageType(1);
//            request.setDeployStatus(1);

//            request.setMessage("{\"title\":\"" + message.getTitle() + "\",\"description\":\""
//                    + message.getContent() + "\",\"open_type\": 2,\"aps\":{\"alert\":\"" + message.getContent() +
//                    "\",\"sound\":\"\",\"badge\":0}}");

            //调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            //认证推送成功
            logger.info("baidu push amount : " + response.getSuccessAmount());
            return true;
        } catch (ChannelClientException e) {
            logger.info("ChannelClientException" + e.getMessage());
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            logger.info(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()) + e.getErrorMsg());
        }
        return false;
    }
}
