package com.jupiter.io.bio.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Browser {

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.browser();
    }
        Logger logger = LoggerFactory.getLogger(Browser.class);

    public void browser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("url: "); // localhost:6666/test
        String url = scanner.nextLine();

        String[] strings = url.split(":|(?=/)", 3);
        String ip = strings[0];
        int port = Integer.parseInt(strings[1]);
        String uri = strings[2];
        logger.info("connecting to:{}, port:{}, access:{}",ip,port,uri);

        try(Socket socket = new Socket(ip, port);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            Reader reader = new InputStreamReader(inputStream)
        ) {
            logger.info("Connected to {}:{}!",ip,port);
            outputStream.write((uri + "\n").getBytes());
            outputStream.flush();
            //outputStream.close();
            char[] chars = new char[0X10];
            StringBuffer sb = new StringBuffer();
            while(true){
                int len = reader.read(chars);
                if(len == -1){
                    break;
                }
                sb.append(chars,0,len);
            }
            logger.info("Response: {}",sb.toString());

            //socket.close();
        } catch (IOException e) {
            logger.error("Cannot connect to {}:{}",ip,port,e);
        }

    }


}
