package com.nookio.utils.tasks;

import com.nookio.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yeeson on 24/2/16.
 */
@Component
public class PeriodicalTask extends BaseTask implements IPeriodicalTask {
    private static Logger logger = LoggerFactory.getLogger(PeriodicalTask.class);
//    @Autowired
//    MYBOnlinePushWorker mybPushWorker;
//
//    @Autowired
//    MyWorker statisticWorker;

    //TODO 实现各种提醒
    @Scheduled(cron = "0 15 1 ? * *")   //每天早上1点15
    public void birthdayRemindTask() {
        logger.info("生日提醒");
    }


    @Scheduled(cron = "0 15 2 ? * *")   //每天早上2点15
    public void callBackRemindTask() {
        logger.info("回访提醒");
    }

    @Scheduled(cron = "0 15 3 ? * * ")   //每天早上3点15
    public void estrangementRemindTask() {
        logger.info("疏远提醒");
    }

    @Scheduled(fixedDelay = 30000, initialDelay = 5000)
    public void pushTask() {
        logger.info("启动推送");
//        mybPushWorker.start();//启动推送
    }

//    @Scheduled(cron = "0 0 2 ? * *")
    @Scheduled(fixedDelay = 20000, initialDelay = 5000)
    public void statisticsTask() {
        logger.info("统计项目");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        Date dayStart = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date first = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date end = calendar.getTime();
        try {
//            statisticWorker.start(first, end, dayStart, tomorrow, "NORMAL");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Scheduled(cron = "0 0 3 ? * *")    //每天凌晨3点
    @Scheduled(fixedDelay = 15000, initialDelay = 5000)
    public void updateCardStatus() {

        logger.info("#定时处理卡券相关状态#");

//        logger.info("!------------------------start---------------------------!");
//
//        Date date = new Date();
//        String dateStr = "'" + StringUtil.formatTime(date) + "'";
//        logger.info(dateStr);
//
//        /*Coupon卡品牌更改为发行状态*/
//        logger.info("Coupon卡品牌更改为发行状态->start");
//        String coupon_faxing = "update cards_coupon set status=:status where status=:status2 and start_date < " + dateStr + " and end_date > " + dateStr;
//        getEbeanServer().createSqlUpdate(coupon_faxing).setParameter("status2", Coupon.STATUS_WAIT_DELIVER).setParameter("status", Coupon.STATUS_IN_DELIVERING).execute();
//        logger.info("Coupon卡品牌更改为发行状态->end");
//
//        /*Coupon卡品牌更改为过期状态*/
//        logger.info("Coupon卡品牌更改为过期状态->start");
//        String coupon_jiezhi = "update cards_coupon set status=:status where status=:status2 and end_date < " + dateStr;
//        getEbeanServer().createSqlUpdate(coupon_jiezhi).setParameter("status2", Coupon.STATUS_IN_DELIVERING).setParameter("status", Coupon.STATUS_DELIVER_END).execute();
//        logger.info("Coupon卡品牌更改为过期状态->end");
//
//        /*CourseCard 限时优惠更改为发行状态*/
//        logger.info("CourseCard 限时优惠更改为发行状态->start");
//        String coursecard_faxing = "update cards_coursecard set status=:status where status=:status2 and course_card_type=:course_card_type and start_date < " + dateStr + " and end_date > " + dateStr;
//        getEbeanServer().createSqlUpdate(coursecard_faxing).setParameter("status2", CourseCard.STATUS_WAIT_DELIVER).setParameter("course_card_type", CourseCard.COURSE_CARD_TYPE_XIANSHIYOUHUI).setParameter("status", CourseCard.STATUS_IN_DELIVERING).execute();
//        logger.info("CourseCard 限时优惠更改为发行状态->end");
//
//        /*CourseCard 限时优惠更改为过期状态*/
//        logger.info("CourseCard 限时优惠更改为过期状态->start");
//        String coursecard_jiezhi = "update cards_coursecard set status=:status where status=:status2 and course_card_type=:course_card_type and end_date < " + dateStr;
//        getEbeanServer().createSqlUpdate(coursecard_jiezhi).setParameter("status2", CourseCard.STATUS_IN_DELIVERING).setParameter("course_card_type", CourseCard.COURSE_CARD_TYPE_XIANSHIYOUHUI).setParameter("status", CourseCard.STATUS_DELIVER_END).execute();
//        logger.info("CourseCard 限时优惠更改为过期状态->end");
//
//        /*ObjCoupon 卡实例更改为可用状态*/
//        logger.info("ObjCoupon 卡实例更改为可用状态->start");
//        String objCoupon_keyong = "update cards_objcoupon set status=:status where status=:status2 and available_date < " + dateStr + " and expired_date > " + dateStr;
//        getEbeanServer().createSqlUpdate(objCoupon_keyong).setParameter("status2", ObjCoupon.STATUS_COLLECTED).setParameter("status", ObjCoupon.STATUS_NORMAL).execute();
//        logger.info("ObjCoupon 卡实例更改为可用状态->end");
//
//        /*ObjCoupon 卡实例更改为过期状态*/
//        logger.info("ObjCoupon 卡实例更改为过期状态->start");
//        String objCoupon_guoqi = "update cards_objcoupon set status=:status where status=:status2 and expired_date < " + dateStr;
//        getEbeanServer().createSqlUpdate(objCoupon_guoqi).setParameter("status2", ObjCoupon.STATUS_NORMAL).setParameter("status", ObjCoupon.STATUS_EXPIRED).execute();
//        logger.info("ObjCoupon 卡实例更改为过期状态->end");

        logger.info("!-------------------------end----------------------------!");
    }


    @Scheduled(cron = "0 0 1 ? * *")    //每天凌晨1点
    public void updateCompanyStatusBySfotRelationExpiredOrNot() {

//        logger.info("#定时处理美容院状态，如果购买期限已到，则锁定美容院，使用者不得登陆#");
//
//        logger.info("!------------------------start---------------------------!");
//
//        Date date = new Date();
//        String dateStr = "'" + StringUtil.formatTime(date) + "'";
//        logger.info(dateStr);
//
//        logger.info("检测sfot关系的relation数据，将过期的数据更改为LOCKED->start");
//        String coupon_faxing = "update parties_relations set status=:status where status=:status2 and relation_type=:type and end_date < " + dateStr;
//        getEbeanServer().createSqlUpdate(coupon_faxing).setParameter("status2", Relation.STATUS_NORMAL).setParameter("status", Relation.STATUS_LOCKED).setParameter("type", RelationType.RELATION_SOFT).execute();
//        logger.info("检测sfot关系的relation数据，将过期的数据更改为LOCKED->end");
//
//        logger.info("!-------------------------end----------------------------!");
    }

}
