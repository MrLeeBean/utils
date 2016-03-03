package com.nookio.utils.push.model;

/**
 * Created by nookio on 16/1/4.
 */
public class BaseMessage {

    public static String MESSAGE_FEED = "1";
    private String fromCode; //转化方code
    private String fromType; //转化方type
    private String appType; //接受设备
    private String pushCode;  //接收方id，设备id
    private String sender;  //接收方id，设备id
    private String senderType;  //接收方id，设备id

    private String pushType;
    private String title;
    private String content;  //接受信息
    private String url; //跳转方向
    private String groupId;  // 组id
    private Integer type; //类型
    private Integer subType; //子类型
    private String extraCodeOne;  // 额外字段1
    private String extraTypeOne;  // 额外字段类型
    private String extraCodeTwo;  // 额外字段2
    private String extraTypeTwo;  // 额外字段类型
    private String extraCodeThree;  // 额外字段3
    private String extraTypeThree;  // 额外字段类型
    private String extraCodeFour;  // 额外字段4
    private String extraCodeFive;  // 额外字段4
    private String extraTypeFour;  // 额外字段类型
    private String extraTypeFive;  // 额外字段类型
    private String orderCode;

    public BaseMessage() {
    }



    public String getContent() {
        return content;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getPushCode() {
        return pushCode;
    }

    public String getPushType() {
        return pushType;
    }

    public String getTitle() {
        return title;
    }

    public String getAppType() {
        return appType;
    }

    public String getFromCode() {
        return fromCode;
    }

    public String getFromType() {
        return fromType;
    }

    public String getUrl() {
        return url;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtraCodeFour() {
        return extraCodeFour;
    }

    public void setExtraCodeFour(String extraCodeFour) {
        this.extraCodeFour = extraCodeFour;
    }

    public String getExtraCodeOne() {
        return extraCodeOne;
    }

    public void setExtraCodeOne(String extraCodeOne) {
        this.extraCodeOne = extraCodeOne;
    }

    public String getExtraCodeThree() {
        return extraCodeThree;
    }

    public void setExtraCodeThree(String extraCodeThree) {
        this.extraCodeThree = extraCodeThree;
    }

    public String getExtraCodeTwo() {
        return extraCodeTwo;
    }

    public void setExtraCodeTwo(String extraCodeTwo) {
        this.extraCodeTwo = extraCodeTwo;
    }

    public String getExtraTypeFour() {
        return extraTypeFour;
    }

    public void setExtraTypeFour(String extraTypeFour) {
        this.extraTypeFour = extraTypeFour;
    }

    public String getExtraTypeOne() {
        return extraTypeOne;
    }

    public void setExtraTypeOne(String extraTypeOne) {
        this.extraTypeOne = extraTypeOne;
    }

    public String getExtraTypeThree() {
        return extraTypeThree;
    }

    public void setExtraTypeThree(String extraTypeThree) {
        this.extraTypeThree = extraTypeThree;
    }

    public String getExtraTypeTwo() {
        return extraTypeTwo;
    }

    public void setExtraTypeTwo(String extraTypeTwo) {
        this.extraTypeTwo = extraTypeTwo;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getExtraCodeFive() {
        return extraCodeFive;
    }

    public void setExtraCodeFive(String extraCodeFive) {
        this.extraCodeFive = extraCodeFive;
    }

    public String getExtraTypeFive() {
        return extraTypeFive;
    }

    public void setExtraTypeFive(String extraTypeFive) {
        this.extraTypeFive = extraTypeFive;
    }
}
