package com.dukla.base.codec.gb32960.element;

import java.io.*;

/**
 * 发动机数据
 */
public class EngineData  implements GB32960DataElement {

    //发动机状态 0x01：启动状态；0x02：关闭状态，“0xFE”表示异常，“0xFF”表示无效。
    private int state;

    //曲轴转速 有效范围：0～60000(表示0rpm～60000rpm)，最小计量单元：1rpm，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int csSpeed;

    //燃料消耗率 有效值范围：0～60000（表示0L/100km～600L/100km），最小计量单元：0.01L/100km，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int fcRate;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.state);
        dos.writeShort(this.csSpeed);
        dos.writeShort(this.fcRate);
    }

    public static EngineData decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        return EngineData.decode(dis);
    }

    public static EngineData decode(DataInputStream dis) throws IOException{
        EngineData engineData=new EngineData();
        engineData.setState(dis.readUnsignedByte());
        engineData.setCsSpeed(dis.readUnsignedShort());
        engineData.setFcRate(dis.readUnsignedShort());
        return engineData;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCsSpeed() {
        return csSpeed;
    }

    public void setCsSpeed(int csSpeed) {
        this.csSpeed = csSpeed;
    }

    public int getFcRate() {
        return fcRate;
    }

    public void setFcRate(int fcRate) {
        this.fcRate = fcRate;
    }
}
