package com.nookio.utils.push.parse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nookio on 16/1/24.
 */
public abstract class BaseParse<F, T> implements Runnable {
    @Override
    public void run() {
        metaData = fetchMetaData();
        resultData = setResultData();
        saveResultData();
    }

    protected List<F> metaData = new ArrayList<>();
    protected List<T> resultData = new ArrayList<>();

    protected abstract List<F> fetchMetaData();
    protected abstract List<T> setResultData();
    protected abstract boolean saveResultData();
}
