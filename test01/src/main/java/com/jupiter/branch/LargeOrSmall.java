package com.jupiter.branch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LargeOrSmall {

    public static String Small = "Small";
    public static String Large = "Large";

    private String result;

    public String dice(int input){
        switch (input){
            case 1:
            case 2:
            case 3:
                this.result = LargeOrSmall.Small;
                break;
            case 4:
            case 5:
            case 6:
                this.result = LargeOrSmall.Large;
                break;
            default:
                log.error("error! dice:{}, return:{}",input,this.result);
                throw new UnsupportedOperationException();
        }
        log.info("dice:{}, return:{}",input,this.result);
        return this.result;
    }

}
