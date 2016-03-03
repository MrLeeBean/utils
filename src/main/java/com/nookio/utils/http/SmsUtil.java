package com.nookio.utils.http;

import com.nookio.utils.string.StringUtil;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nookio on 15/11/9.
 */
public class SmsUtil {

    public static String IDENTIFY_TEMP_ID = "39623";
    public static String SUCCESS_STATUS = "\"000000\"";
    public static String AGAIN_SUCCESS_STATUS = "\"160021\"";
    public static int SMS = 0;
    public static int VOICE = 1;
    private static String SERVER_IP = "app.cloopen.com";
    private static String SERVER_PORT = "8883";
    private static String SOFT_VERSION = "2013-12-26";
    private static String ACCOUNT_SID = "aaf98f895012018d01501209535c0030";
    private static String ACCOUNT_TOKEN = "3a86c6759f374a999b842d4b10a5e124";
    private static String APP_ID = "aaf98f895012018d0150120c1d1b0045";


    @Deprecated
    //todo 这个地方所有的数据链，需要注意的时tempId 和data是有相关联的，这个temp——id只能用来发送6位的验证码。
    public static boolean sendMessage(String timeNow, String tempId, String mobile, String data, boolean sendVoice) {
        if (sendSms(timeNow, tempId, mobile, data)) {
            return true;
        } else if ( sendVoice && sendVoice(timeNow, tempId, mobile, data)) {
            return true;
        } else {
            return false;
        }
    }

    //TODO 发送短信暂时关闭
    @Deprecated
    //todo 这个地方所有的数据链，需要注意的时tempId 和data是有相关联的，这个temp——id只能用来发送6位的验证码。
    public static boolean sendSms(String timeNow, String tempId, String mobile, String data) {
//        String result = HttpUtil.doPostOnSSL(getServerUrl(SMS, timeNow), getBody(mobile, data, tempId), getHeaders(timeNow));
//        JsonNode jsonNode = Json.parse(result);
//        if ( jsonNode.has("statusCode") && (SUCCESS_STATUS.equals(jsonNode.get("statusCode").toString()) || AGAIN_SUCCESS_STATUS.equals(jsonNode.get("statusCode").toString()))){
//            logger.info("发送短信回调成功" + jsonNode.asText());
//            return true;
//        } else{
//            logger.info("发送失败" + jsonNode.asText());
//            return false;
//        }
        return true;
    }

    public static boolean sendVoice(String timeNow, String tempId, String mobile, String data) {
        HttpUtil.doPostOnSSL(getServerUrl(VOICE, timeNow), getBody(mobile, data, tempId), getHeaders(timeNow));
        return true;
    }


    private static String getServerUrl(int type, String timeNow) {
        String md5 = StringUtil.MD5(ACCOUNT_SID + ACCOUNT_TOKEN + timeNow);
        switch (type) {
            case 0:
                return "https://" + SERVER_IP + ":" + SERVER_PORT + "/" + SOFT_VERSION + "/Accounts/" + ACCOUNT_SID + "/SMS/TemplateSMS?sig=" + md5;
            case 1:
                return "https://" + SERVER_IP + ":" + SERVER_PORT + "/" + SOFT_VERSION + "/Accounts/" + ACCOUNT_SID + "/Calls/VoiceVerify?sig=" + md5;
            default:
                return "";
        }
    }

    private static Map<String, String> getHeaders(String timeNow) {
        System.out.println(timeNow);
        String authorization = new BASE64Encoder().encode((ACCOUNT_SID + ":" + timeNow).getBytes());
        //String authorization = Base64.getEncoder().encode((ACCOUNT_SID + ":" + timeNow).getBytes()).toString();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("Authorization", authorization);
        return headers;
    }

    private static String getBody(String mobile, String data, String tempId) {
        return "{\"to\":\"" + mobile + "\",\"templateId\":\"" + tempId + "\",\"appId\":\"" + APP_ID + "\",\"datas\":[\"" + data + "\"]}";
    }

}
