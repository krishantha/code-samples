package com.krishantha.socketreader.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketReader {


    private String ipAddress;
    private int port;
    private String readValue;


    public SocketReader(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }


    public String readValue() {

        Socket socket = null;
        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        // BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer line = new StringBuffer();
        try {

            char character;
char tmp;
            while ((tmp=(char) inputStream.read() )!= '<') {
               // System.out.println("1 - "+tmp);
            }
            while ((character =(char) inputStream.read())!= '>') {
                line.append(character);
            }
                return line.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
