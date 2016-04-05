namespace java com.shl.thriftstudy.result

include "types.thrift"

struct InvokeRes{
    1:required types.Boolean invokeres;
    2:required types.String invokecode;
}

struct BookingResult{
    1:required types.Boolean bookingres;
    2:optional types.String msg;
    3:required InvokeRes invokeRes;
}

