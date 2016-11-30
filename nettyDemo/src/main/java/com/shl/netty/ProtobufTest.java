package com.shl.netty;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by jackson on 16/11/30.
 */
public class ProtobufTest {

    private static byte[] encode(SubscribeRespProto.SubscribeResp resp){
        return resp.toByteArray();
    }

    private static  SubscribeRespProto.SubscribeResp decode(byte[] body){
        try {
            return SubscribeRespProto.SubscribeResp.parseFrom(body);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static SubscribeRespProto.SubscribeResp createSubscribeReq(){
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();

        builder.setRespCode(1);
        builder.setSubReqID(2);
        builder.setDesc("shl netty study!");

        return builder.build();
    }

    public static void main(String[] args) {
        SubscribeRespProto.SubscribeResp resp = createSubscribeReq();

        System.out.println("Before encode:" + resp.toString());

        SubscribeRespProto.SubscribeResp resp2 = decode(encode(resp));

        System.out.println("After decode:" + resp2.toString());
    }
}
