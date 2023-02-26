package com.jupiter.schedule;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @Description:
* @author: Jupiter.Lin
* @version: V1.0 
* @date: 2021年5月27日 上午11:16:48
*/



@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    //每30秒执行一次
    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
    }

    //在固定时间执行
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
}