package com.dukla.base.codec.gb17691.message;

/**
 * 命令标识
 */
public enum CmdId {

    VEHICLE_LOGIN(0x01),
    REALTIME(0x02),
    REISSUE(0x03),
    VEHICLE_LOGOUT(0x04),
    TERMINAL_TIME_CHECK(0x05),
    TERMINAL_REMOVE_ALARM(0x06),
    PLATFORM_LOGIN(0x07),
    PLATFORM_LOGOUT(0x08),
    SECRET_KEY_EXCHANGE(0x09);

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
