package com.krishantha;

import com.sun.tools.jdi.SocketListeningConnector;

import java.io.*;
import java.net.Socket;

public class SocketReader implements Runnable {


    private String ipAddress;
    private int port;
    private   String readValue;

    public String getReadValue() {
        return readValue;
    }

    public SocketReader(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port=port;
    }

    @Override
    public void run() {

        Socket socket= null;
        try {
            socket=new Socket(ipAddress,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream=null;
        try {
             inputStream=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

        String line;

        try {
            while ((readValue = bufferedReader.readLine()) != null) {

            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
