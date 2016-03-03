package com.nookio.utils.push.pushAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nookio on 16/1/4.
 */
public abstract class PushOnlineWorker<M> extends BasePush<M> {

    protected List<String> successCodes = new ArrayList<>();

    public void start() {
        try {
            process();
        } catch (Exception e) {
            onError(e);
        }
    }

    protected void process() throws Exception {
        fetchMessage();
        parseMessage();
        push();
        successToDo();
        failToDo();
        finalTodo();
    }

    protected abstract void finalTodo();

    protected abstract void successToDo();

    protected abstract void failToDo();

    protected abstract void onError(Exception e);

    protected abstract void fetchMessage();

}
