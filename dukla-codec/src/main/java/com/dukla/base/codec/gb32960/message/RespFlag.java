package com.dukla.base.codec.gb32960.message;

public enum RespFlag {

    SUCCESS(0x01),
    FAILED(0x02),
    VIN_DUPLICATE(0x03),
    COMMAND(0xFE);

    private int val;

    RespFlag(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static RespFlag getByVal(int val){
        for(RespFlag cmdId: RespFlag.values()){
            if(cmdId.getVal() == val){
                return cmdId;
            }
        }
        return null;
    }

}
