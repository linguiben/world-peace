/**
 * @className TextDataQueue
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-10 13:43
 */
package com.jupiter.util;

import lombok.extern.slf4j.Slf4j;

/**
 *@desc 此类实现监听器接口，可以接收数据，并存放到自己的queue中，以备使用。
 *@author Jupiter.Lin
 *@date 2023-12-10 13:43
 */
@Slf4j
public class TextDataConsumer implements DataListener<TextData>{

    Queue<TextData> queue = new FIFOQueue<>() {};

    public void start(){
        Thread worker = new Thread(() -> {
            while (true) {
                try {
                    TextData data = this.queue.get();
                    if(data == null){
                        continue;
                    }
                    log.info("Receive: {}", data.getRawData());
                } catch (InterruptedException e) {
                    log.error("Receive data Error. {}", e);
                    throw new RuntimeException(e);
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    @Override
    public void receiveData(TextData data) {
        this.queue.put(data);
    }

    public void stop(){
        this.queue.shutdown();
    }
}
