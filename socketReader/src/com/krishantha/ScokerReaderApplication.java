package com.krishantha;

public class ScokerReaderApplication {

    public static void main(String[] args) {


        SocketReader socketReader1= new SocketReader("192.168.8.101",3001);


        SocketReader socketReader2= new SocketReader("192.168.8.102",3002);

        Thread thread1=new Thread(socketReader1);
        thread1.start();

        Thread thread2=new Thread(socketReader2);
        thread2.start();

        for(;;){

            System.out.println("1 >>>>"+ socketReader1.getReadValue());
            System.out.println( "2 >>>>"+socketReader2.getReadValue());
        }

    }
}
