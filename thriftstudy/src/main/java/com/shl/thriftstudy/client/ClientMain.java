package com.shl.thriftstudy.client;

import com.shl.thriftstudy.exception.SHLException;
import com.shl.thriftstudy.param.BookingModel;
import com.shl.thriftstudy.service.ShlShopBooking;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by jackson on 16/4/5.
 */
public class ClientMain {

    public static void main(String[] args) {
        TTransport transport = new TSocket("localhost", 8888);
        try {
            // 设置调用的服务地址为本地，端口为 7911

            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            ShlShopBooking.Client client = new ShlShopBooking.Client(protocol);
            transport.close();
            transport.open();
            // 调用服务的 helloVoid 方法
            client.shlbooking(new BookingModel());
            System.out.println("Success!!");
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } catch (SHLException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }
}
