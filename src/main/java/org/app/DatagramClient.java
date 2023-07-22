package org.app;

import java.io.IOException;
import java.net.*;


public class DatagramClient {
    public static void main(String[] args)  {
        try {
            DatagramSocket ds = new DatagramSocket(2001);
            String msg = "HELLO WORLD!!!";
//this will require an array of bytes hence the conversion
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("localhost"), 4545);
            ds.send(dp);

            byte b[] = new byte[1024];
            dp= new DatagramPacket(b,1024);
            ds.receive(dp);

            msg=new String(dp.getData()).trim();
            System.out.println("FROM SERVER" +msg);
            ds.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class DatagramServers{
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(2000);

            byte b[] = new byte[1024];
            DatagramPacket dp = new DatagramPacket(b,1024);
            ds.receive(dp);

            String msg = new String(dp.getData()).trim();
            System.out.println("FROM CLIENT" + msg);

            StringBuilder sb = new StringBuilder(msg);
            sb.reverse();
            msg= sb.toString();

            dp = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("localhost"), 2001);
            ds.send(dp);

            ds.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
