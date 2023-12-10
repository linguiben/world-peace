package com.jupiter.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desc Distributor中包含一个监听器列表，功能是将数据发布到监听器中。
 * @author Jupiter.Lin
 * @date 2023-12-10 03:19
 */
public class DataDistributor <D extends Data, L extends DataListener<D>>{
    protected List<L> listenerList; // 需要一个线程安全的List
    ExecutorService executorService;

    public DataDistributor() {
        this(false);
    }

    public DataDistributor(Boolean nonBlocking) {
        if (nonBlocking)
            executorService = Executors.newFixedThreadPool(2);
        this.listenerList = Collections.synchronizedList(new ArrayList<L>());
    }

    public void addListener(final L listener){
        this.listenerList.add(listener);
    }

    public boolean removeListener(final L listener){
        return this.listenerList.remove(listener);
    }

    public void clearListenerList(){
        this.listenerList.clear();
    }

    protected List<L> getListenerList(){
        return this.listenerList;
    }

    /**
     * distribute data to all listener.
     * @param data
     */
    protected void distributeData(D data) {
        for (L l : this.listenerList) {
            // 此处可修改为线程池(异步非阻塞)，由于Example代码中已经使用FIFIQueue，所以这里没有用线程池。
            if (executorService == null)
                l.receiveData(data);
            else
                executorService.execute(() -> l.receiveData(data));
        }
    }

}
