package com.nookio.utils.push;


import com.nookio.utils.push.pushAction.MYBOnlinePushWorker;
import com.nookio.utils.push.pushAction.PushProcessor;

/**
 * Created by nookio on 16/1/5.
 */
public class PushDeployer {

//todo 这里还欠缺定时启动

    public static void start(MYBOnlinePushWorker mybPushWorker) {
        PushProcessor pushProcessor = PushProcessor.getInstance();
        //pushProcessor.submit(mybPushWorker);
    }

}
