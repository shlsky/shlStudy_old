package com.shl.zk.demo;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jackson on 16/3/28.
 */
public class Zkconnection implements Watcher{

    private static final int SESSION_TIMEOUT = Integer.MAX_VALUE;

    private static final String CONNECT_URL_PORT = "127.0.0.1:2182";

    private static final String ZK_PATH = "/study";

    private ZooKeeper zooKeeper = null;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive notification:"+event.getState()+"\n");

        //如果收到了连接建立的通知,则不阻塞进程
        if (Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }

    }

    public void createZkInstance(String address,int sessionTimeout){

        try{
            this.closeZkInstance(this.zooKeeper);
            zooKeeper = new ZooKeeper(address,sessionTimeout,this);

            //没有收到连接成功的通知,则阻塞.
            connectedSemaphore.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeZkInstance(ZooKeeper zk){
        //关闭旧的链接.
        if (null != this.zooKeeper){
            try {
                this.zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean createPath(String path,String data,CreateMode createMode) throws Exception{
        this.zooKeeper.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                createMode);
        return true;
    }

    public String readData(String path) throws Exception{
        return new String(this.zooKeeper.getData(path,false,null));
    }

    public boolean writeData(String path,String data) throws Exception{
        this.zooKeeper.setData(path,data.getBytes(),-1);
        return true;
    }

    public boolean deleteNode(String path) throws Exception{
        this.zooKeeper.delete(path,-1);
        return true;
    }
    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }
    public static void main( String[] args ) {
        Zkconnection zkconnection = new Zkconnection();
        try {

            zkconnection.createZkInstance(CONNECT_URL_PORT,SESSION_TIMEOUT);
            if ( zkconnection.createPath( ZK_PATH, "我是节点初始内容",CreateMode.EPHEMERAL) ) {
                System.out.println();
                System.out.println( "数据内容: " + zkconnection.readData( ZK_PATH ) + "\n" );
                zkconnection.writeData( ZK_PATH, "更新后的数据" );
                System.out.println( "数据内容: " + zkconnection.readData( ZK_PATH ) + "\n" );
//                zkconnection.deleteNode( ZK_PATH );
            }
            Thread.sleep(20000);
            zkconnection.closeZkInstance(zkconnection.getZooKeeper());

        }catch (Exception e){
            zkconnection.closeZkInstance(zkconnection.getZooKeeper());
            e.printStackTrace();
        }

    }
}
