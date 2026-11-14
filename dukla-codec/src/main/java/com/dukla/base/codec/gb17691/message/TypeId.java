package com.dukla.base.codec.gb17691.message;

/**
 * 信息类型标识
 */
public enum TypeId {

    OBD(0x01),
    DPF(0x02),
    TWC(0x03),
    PHEV(0x04),
    TWCNOX(0x05),
    EXTEND(0x80);

    private int val;

    TypeId(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static TypeId getByVal(int val){
        for(TypeId typeId: TypeId.values()){
            if(typeId.getVal() == val){
                return typeId;
            }
        }
        return null;
    }

}
