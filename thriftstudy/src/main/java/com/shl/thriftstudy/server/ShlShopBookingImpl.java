package com.shl.thriftstudy.server;

import com.facebook.fb303.fb_status;
import com.shl.thriftstudy.exception.SHLException;
import com.shl.thriftstudy.param.BookingModel;
import com.shl.thriftstudy.result.BookingResult;
import com.shl.thriftstudy.result.InvokeRes;
import com.shl.thriftstudy.service.ShlShopBooking;
import org.apache.thrift.TException;

import java.util.Map;

/**
 * Created by jackson on 16/4/5.
 */
public class ShlShopBookingImpl extends FB303 implements ShlShopBooking.Iface{


    @Override
    public BookingResult shlbooking(BookingModel bookingModel) throws SHLException, TException {
        System.out.println("invoke !!!");
        BookingResult bookingResult = new BookingResult();
        try{
            bookingResult.setBookingres(true);
            bookingResult.setInvokeRes(new InvokeRes());
            bookingResult.getInvokeRes().setInvokeres(true);
            bookingResult.getInvokeRes().setInvokecode("success");
            bookingResult.setMsg("购买成功");

        }catch (Exception e){
            throw new SHLException("exception");
        }



        return bookingResult;
    }

}
