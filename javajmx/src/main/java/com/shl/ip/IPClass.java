package com.shl.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jackson on 16/11/10.
 */
public class IPClass {
    public static void main(String[] args) {
        try
        {
            System.out.println("本机的IP = " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}
