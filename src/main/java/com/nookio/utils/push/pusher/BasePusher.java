package com.nookio.utils.push.pusher;


import com.nookio.utils.push.model.BaseMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nookio on 16/1/4.
 */
public abstract class BasePusher<M extends BaseMessage> {

    public void finalTodo(){
        successCodes.clear();
        tasks.clear();
    }

    List<M> tasks = new ArrayList<>();

    List<String> successCodes = new ArrayList<>();

    public abstract boolean pushOne(M m) throws Exception;

    public abstract boolean push() throws Exception;

    public boolean checkTask() {
        if (tasks.size() == 0) return false;
        else return true;
    }

    public boolean addTask(M m) {
        tasks.add(m);
        return true;
    }

//    public <M> boolean addTaskList(String fromCode, String fromType, M m, String title, Collection<String> pushIds){
//        pushIds.stream().filter(key -> null != key).forEach(pushId -> tasks.add(new BaseMessage(fromCode, fromType, pushId, m.toString(), title, null, null)));
//        return true;
//    }
//
//    public <M> boolean addAppTask(String fromCode, String fromType, M m, String groupId, String title){
//        tasks.add(new BaseMessage(fromCode, fromType, null, m.toString(), groupId, title, null));
//        return true;
//    }


    public abstract String getMessage(M m);

    public List<String> getSuccessCodes() {
        return successCodes;
    }

    public Map<String,String> getAppUserData(String date){
        return null;
    }

    public Map<String,String> getAppPushData(String date) {
        return null;
    }

}
