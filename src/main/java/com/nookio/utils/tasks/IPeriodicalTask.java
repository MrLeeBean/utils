package com.nookio.utils.tasks;

/**
 * Created by yeeson on 24/2/16.
 */
public interface IPeriodicalTask {
    public void birthdayRemindTask();//生日提醒

//    public void bookingRemindTask();//预约提醒

    public void callBackRemindTask();//回访提醒

    public void estrangementRemindTask();//疏远提醒

    public void pushTask();

    public void statisticsTask();

    public void updateCardStatus();     //更改优惠券过期状态
}
