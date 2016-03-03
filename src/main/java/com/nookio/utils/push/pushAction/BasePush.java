package com.nookio.utils.push.pushAction;


import com.nookio.utils.push.pusher.BasePusher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nookio on 16/1/4.
 */
public abstract class BasePush<M extends Object> {

    protected Map<String, String> result = new HashMap<String, String>();

    protected M getMessage() {
        return null;
    }

    protected List<String> getPushIds() {
        return null;
    }

    protected Map<String, M> getMessageList() {
        return null;
    }

    protected abstract boolean checkPusher(BasePusher basePusher);

    protected abstract boolean push() throws Exception;

    protected boolean pushOneMessage() {
        return false;
    }

    protected boolean pushOneMessageToList() {
        return false;
    }

    protected boolean pushManyMessageToMany() {
        return false;
    }

    protected boolean pushMessageToApp() {
        return false;
    }

    public Map<String, String> getAppUserData() {
        return null;
    }

    public Map<String, String> getAppPushData() {
        return null;
    }

    protected boolean parseMessage() {
        return false;
    }

}
