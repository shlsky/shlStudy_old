package com.shl.thriftstudy.server;

import com.shl.thriftstudy.service.ShlShopBooking;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by jackson on 16/4/5.
 */
public class ServerMain {
    public static void main(String[] args) {
        try {

            //服务端端口
            TServerSocket tServerSocket = new TServerSocket(8888);

            //传输协议
            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

            //实例化处理器
            TProcessor processor = new ShlShopBooking.Processor(new ShlShopBookingImpl());

            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(tServerSocket);
            serverArgs.processor(processor);
            serverArgs.protocolFactory(proFactory);

            //实例化服务
            TServer server = new TThreadPoolServer(serverArgs);
            System.out.println("Start server on port 8888...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
