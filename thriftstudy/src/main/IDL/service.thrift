namespace java com.shl.thriftstudy.service

include "types.thrift"
include "param.thrift"
include "result.thrift"
include "fb303.thrift"
include "exception.thrift"

service ShlShopBooking extends fb303.FacebookService{
    result.BookingResult  shlbooking(1:param.BookingModel bookingModel) throws (1:exception.SHLException e);
}