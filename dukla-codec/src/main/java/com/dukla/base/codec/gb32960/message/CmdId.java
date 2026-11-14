package com.dukla.base.codec.gb32960.message;

public enum CmdId {

    VEHICLE_LOGIN(0x01),
    REALTIME(0x02),
    REISSUE(0x03),
    VEHICLE_LOGOUT(0x04),
    PLATFORM_LOGIN(0x05),
    PLATFORM_LOGOUT(0x06),
    HEARTBEAT(0x07),
    CHECK_TIME(0x08);

    private int val;

    CmdId(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static CmdId getByVal(int val){
        for(CmdId cmdId: CmdId.values()){
            if(cmdId.getVal() == val){
                return cmdId;
            }
        }
        return null;
    }

}
