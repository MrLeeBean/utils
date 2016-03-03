package com.nookio.utils.push.model;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by yeeson on 15/11/13.
 */
@Entity
@Table(name = "platform_devices")
public class Device extends Model {

    public static String STATUS_INIT = "INIT";//初始化
    public static String STATUS_NORMAL = "NORMAL";//正常使用中
    public static String STATUS_LOCKED = "LOCKED";//锁定
    public static String STATUS_DELETED = "DELETED";//逻辑删除

    //ios 设备
    public static String TYPE_IOS_B_PHONE_STORE = "IOS_B_PHONE_STORE";
    public static String TYPE_IOS_B_PHONE_STORE_NEW = "IOS_B_PHONE_STORE_NEW";
    public static String TYPE_IOS_C_PHONE_STORE = "IOS_C_PHONE_STORE";
    public static String TYPE_IOS_PHONE_E  = "3";
    public static String TYPE_IOS_B_PHONE_COMPANY = "IOS_B_PHONE_COMPANY";
    public static String TYPE_IOS_B_PHONE_COMPANY_NEW = "IOS_B_PHONE_COMPANY_NEW";
    //android设备
    public static String TYPE_ANDROID_B_PHONE = "ANDROID_B_PHONE";
    public static String TYPE_ANDROID_B_PHONE_NEW = "ANDROID_B_PHONE_NEW";
    public static String TYPE_ANDROID_C_PHONE = "ANDROID_C_PHONE";
    //android pad 设备
    public static String TYPE_ANDROID_B_PAD = "ANDROID_B_PAD";
    //ios pad 设备
    public static String TYPE_IOS_B_PAD_COMPANY = "IOS_B_PAD_COMPANY";
    //html
    public static String TYPE_HTML_C = "HTML5_C";
    public static String TYPE_HTML_B = "HTML5_B";


    public static String TYPE_IOS_PAD_E = "5";

    public static String MESSAGE_FEED = "1";

    public Device(String belongToPartyCode, String belongToPartyType, String code, String deviceCode, String deviceType,
                  String deviceVersion, String groupCode, String pushCode, String pushType, String status) {
        this.belongToPartyCode = belongToPartyCode;
        this.belongToPartyType = belongToPartyType;
        this.code = code;
        this.deviceCode = deviceCode;
        this.deviceType = deviceType;
        this.deviceVersion = deviceVersion;
        this.groupCode = groupCode;
        this.pushCode = pushCode;
        this.pushType = pushType;
        this.status = status;
    }

    @Id
    public Integer id;
    @Column
    String code;
    @Column
    String deviceCode; //设备ID
    @Column
    String pushCode; //推送设备code
    @Column
    String pushType; //推送方
    @Column
    String groupCode; //组code
    @Column
    String deviceType; //设备类型
    @Column
    String deviceVersion; //设备版本
    @Column
    String belongToPartyType;  //归属方
    @Column
    String belongToPartyCode; //归属方
    @Column
    String status;
    @CreatedTimestamp
    public Date createdAt;
    @CreatedTimestamp
    public Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBelongToPartyType() {
        return belongToPartyType;
    }

    public void setBelongToPartyType(String belongToPartyType) {
        this.belongToPartyType = belongToPartyType;
    }

    public String getBelongToPartyCode() {
        return belongToPartyCode;
    }

    public void setBelongToPartyCode(String belongToPartyCode) {
        this.belongToPartyCode = belongToPartyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }
}
