package com.dukla.base.codec.gb32960.message;

import com.dukla.base.codec.gb32960.element.GatherTime;

import java.io.*;

/**
 * 平台登出
 */
public class PlatformLogoutMessage extends GB32960Message {
    //数据采集时间 年月日时分秒 各占一个字节
    private GatherTime gatherTime;

    //登入流水号 车载终端每登入一次登入流水号自动加 1，从 1 开始循环累加，最大值 65531，循环周期为天
    private int sn;


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        this.gatherTime.encode(dos);
        dos.writeShort(this.sn);
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(DataInputStream dis) throws IOException {
        this.setGatherTime(GatherTime.decode(dis));
        this.setSn(dis.readUnsignedShort());
    }

    public GatherTime getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(GatherTime gatherTime) {
        this.gatherTime = gatherTime;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

}
