package com.nookio.utils.push.pusher;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.nookio.utils.push.model.BaseMessage;
import com.nookio.utils.push.model.Device;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


/**
 * Created by nookio on 16/1/4.
 */
@Component
public class GetuiPusher extends BasePusher<BaseMessage> {

    Logger logger = Logger.getLogger(GetuiPusher.class.getName());

    static String PHONE_APP_ID = "";
    static String PHONE_APP_KEY = "";
    static String PHONE_MASTER = "";

    static String PHONE_APP_ID_NEW = "";
    static String PHONE_APP_KEY_NEW = "";
    static String PHONE_MASTER_NEW = "";

    static String IOS_APP_ID = "";
    static String IOS_APP_KEY = "";
    static String IOS_APP_MASTER = "";

    static String PAD_APP_ID = "";
    static String PAD_APP_KEY = "";
    static String PAD_MASTER = "";

    String host = null;
    String master = null;
    String appId = null;
    String appKey = null;

    @Override
    public boolean pushOne(BaseMessage baseMessage) {
        try {
            String appType = baseMessage.getAppType();
            if (appType.equals(Device.TYPE_IOS_B_PHONE_STORE) || appType.equals(Device.TYPE_ANDROID_B_PHONE)){
                appId = PHONE_APP_ID;
                appKey = PHONE_APP_KEY;
                master = PHONE_MASTER;
            } else if (appType.equals(Device.TYPE_IOS_B_PHONE_COMPANY)){
                appId = IOS_APP_ID;
                appKey = IOS_APP_KEY;
                master = IOS_APP_MASTER;
            } else if (appType.equals(Device.TYPE_IOS_B_PHONE_STORE_NEW) || appType.equals(Device.TYPE_ANDROID_B_PHONE_NEW)) {
                appId = PHONE_APP_ID_NEW;
                appKey = PHONE_APP_KEY_NEW;
                master = PHONE_MASTER_NEW;
            } else {
                appId = PAD_APP_ID;
                appKey = PAD_APP_KEY;
                master = PAD_MASTER;
            }
            return pushGetui(baseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean push() throws Exception {
        if (null != tasks && tasks.size()!=0) {
            for (BaseMessage baseMessage : tasks){
                String appType = baseMessage.getAppType();
                if (appType.equals(Device.TYPE_IOS_B_PHONE_STORE) || appType.equals(Device.TYPE_ANDROID_B_PHONE)){
                    appId = PHONE_APP_ID;
                    appKey = PHONE_APP_KEY;
                    master = PHONE_MASTER;
                } else if (appType.equals(Device.TYPE_IOS_B_PHONE_COMPANY)){
                    appId = IOS_APP_ID;
                    appKey = IOS_APP_KEY;
                    master = IOS_APP_MASTER;
                }else{
                    appId = PAD_APP_ID;
                    appKey = PAD_APP_KEY;
                    master = PAD_MASTER;
                }
                //这个地方的错误信息需要处理
                if (pushGetui(baseMessage)){
                    successCodes.add(baseMessage.getFromCode());
                }
            }
            tasks.clear();
        }
        return false;
    }
    /**
     * 发送消息
     *
     * @param
     * @param baseMessage
     * @return
     */
    private boolean pushGetui(BaseMessage baseMessage) throws Exception {
        IGtPush push = new IGtPush(host, appKey, master);
        push.connect();
        AbstractTemplate template = createTransmissionTemplate(appId, appKey, baseMessage);

        SingleMessage singleMessage = new SingleMessage();
        singleMessage.setOffline(true);
        singleMessage.setOfflineExpireTime(1000 * 3600);
        singleMessage.setData(template);

        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(baseMessage.getPushCode());
        IPushResult ret = push.pushMessageToSingle(singleMessage, target);

        logger.info("getui push one message " + ret.getResponse().toString() + "  message_id:" + baseMessage.getPushCode());
        if ("ok".equals(ret.getResponse().get("result"))) {
            return true;
        }
        return false;
    }

    /**
     * android  消息
     *
     * @param
     * @return
     */
    public NotificationTemplate createNotificationTemplate(String appId, String appKey, BaseMessage baseMessage) {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(baseMessage.getTitle());
        template.setText(baseMessage.getContent());
        template.setLogo("img_core_logo.png");
        template.setTransmissionType(1);
        template.setTransmissionContent(getMessage(baseMessage));
        return template;
    }

    /**
     * iso 透传消息
     *
     *
     private String type; //类型
     private String subType; //子类型

     * @param
     * @return
     * @throws Exception
     * todo处理字符串格式
     */
    @Override
    public String getMessage(BaseMessage baseMessage) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("{\"appType\":\"");
//        sb.append(baseMessage.getAppType());
//        sb.append("\",\"title\":\"");
//        sb.append(baseMessage.getTitle());
//        sb.append("\",\"content\":\"");
//        sb.append(baseMessage.getContent());
//        sb.append("\",\"url\":\"");
//        sb.append(baseMessage.getUrl());
//        sb.append("\",\"type\":\"");
//        sb.append(baseMessage.getType());
//        sb.append("\",\"subType\":\"");
//        sb.append(baseMessage.getSubType());
//        sb.append("\"}");
//        return sb.toString();
        return JSON.toJSONString(baseMessage);
    }


    public TransmissionTemplate createTransmissionTemplate(String appId, String appKey, BaseMessage baseMessage)
            throws Exception {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        template.setTransmissionContent(getMessage(baseMessage));
        template.setPushInfo("", 1, baseMessage.getContent(), "default", "透传消息", "", "", "");

        return template;
    }

}
