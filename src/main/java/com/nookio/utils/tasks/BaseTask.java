package com.nookio.utils.tasks;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.springsupport.factory.EbeanServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yeeson on 24/2/16.
 */
@Component
public class BaseTask {
    @Autowired
    private EbeanServerFactoryBean ebeanServerFactoryBean;

    protected EbeanServer getEbeanServer() {
        EbeanServer ebeanServer = null;
        try {
            ebeanServer = ebeanServerFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ebeanServer;
    }
}
