/**
 * @className TextData
 * @desc TODO
 * @author Jupiter.Lin
 * @date 2023-12-10 13:55
 */
package com.jupiter.util;

/**
 *@desc TODO
 *@author Jupiter.Lin
 *@date 2023-12-10 13:55
 */
public class TextData implements Data{

    private String rawData;
    public TextData(String rawData) {
        this.rawData = rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getRawData() {
        return rawData;
    }
}
