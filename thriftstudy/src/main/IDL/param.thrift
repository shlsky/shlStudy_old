namespace java com.shl.thriftstudy.param

include "types.thrift"

struct BookingModel {

    //购买产品
    1:required types.Long goodsId;

    //用户Id
    2:required types.Long userId;

    //购买数量
    3:optional types.Integer num;
}