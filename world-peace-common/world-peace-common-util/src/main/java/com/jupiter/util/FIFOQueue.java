package com.jupiter.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jupiter.Lin
 * @desc The abstract fist in first out queue
 * @date 2023-12-10 02:04
 */
public abstract class FIFOQueue<D> implements Queue<D>, java.io.Serializable {
    private volatile boolean acceptDataFlag;
    protected List<D> cache;
    private static final int initializedSize = 16; // 默认大小

    public FIFOQueue() {
        this(initializedSize);
    }

    public FIFOQueue(int size) {
        this.cache = new ArrayList<>(size);
        this.acceptDataFlag = true;
    }

    /**
     * put data to the queue
     *
     * @param d
     */
    @Override
    public synchronized void put(D d) {
        if (this.acceptDataFlag)
            this.cache.add(d);
        notify(); // 通知在等待数据的线程，只需通知一个即可
    }

    /**
     * get data from the queue without timeout
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    public synchronized D get() throws InterruptedException {
        return this.get(0);
    }

    /**
     * get data from the queue with timeout defined.
     *
     * @param timeoutMillis
     * @return
     * @throws InterruptedException
     */
    public synchronized D get(final int timeoutMillis) throws InterruptedException {
        if (!this.acceptDataFlag)
            return null;
        if (this.cache.isEmpty()) {
            wait(timeoutMillis); // 若有数据进来，将被唤醒
            if (this.cache.size() == 0)
                return null;
        }
        D data = this.cache.remove(0);
        return data;
    }

    @Override
    public synchronized List<D> getAll() throws InterruptedException {
        return getAll(0);
    }

    public synchronized List<D> getAll(int timeoutMillis) throws InterruptedException {
        // if the cache is empty wait for notification or time out.
        // return null when timeout
        if (this.cache.isEmpty()) {
            wait(timeoutMillis);
            if (this.cache.isEmpty()) {
                return null;
            }
        }
        // swap
        final List<D> out = this.cache;
        this.cache = new ArrayList<D>(initializedSize);
        return out;
    }

    /**
     * @return 返回queue中剩余的data数量
     */
    @Override
    public synchronized int remains() {
        return this.cache.size();
    }

    @Override
    public synchronized void clear() {
        this.cache.clear();
    }

    @Override
    public synchronized void shutdown() {
        this.acceptDataFlag = false;
        this.clear();
        notifyAll();
    }
}
