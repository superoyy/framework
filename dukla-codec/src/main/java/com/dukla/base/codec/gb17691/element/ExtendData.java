package com.dukla.base.codec.gb17691.element;

import java.io.*;

/**
 * 补充信息 信息
 */
public class ExtendData implements GB17691DataElement {

    public static final int TYPE_CODE = 0x80;

    /**
     * 发动机扭矩模式
     * 1 Byte
     * 0：超速失效
     * 1：转速控制
     * 2：扭矩控制
     * 3：转速/扭矩控制
     * 9：正常
     */
    private int torqueMode;

    /**
     * 油门踏板
     * 1 Byte
     * 精度：0.4%/bit
     * 偏移量：0
     * 数据范围：0～100%
     * “0xFF”表示无效
     */
    private int throttle;

    /**
     * 累计油耗（总油耗）
     * 4 Byte
     * 精度：0.5 L/bit
     * 偏移量：0
     * 数据范围：0~2 105 540 607.5 L
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long totalOilConsume;

    /**
     * 尿素箱温度
     * 1 Byte
     * 精度：1 deg C/bit
     * 偏移量：-40 deg C
     * 数据范围：-40 deg C～210 deg C
     * “0xFF”表示无效
     */
    private int ureaBoxTemp;

    /**
     * 实际尿素喷射量
     * 4 Byte
     * 精度：0.01 ml/h /bit
     * 偏移量：0
     * 数据范围：0
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long ureaInject;

    /**
     * 累计尿素消耗（总尿素消耗）
     * 4 Byte
     * 精度：1 g/bit
     * 偏移量：0
     * 数据范围：0
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long totalUreaConsume;

    /**
     * DPF排气温度
     * 2 Byte
     * 精度：0.03125 deg C/bit
     * 偏移量：-273 deg C
     * 数据范围：-273 deg C~1734.96875 deg C
     * “0xFF，0xFF”表示无效
     */
    private int dpfOutTemp;

    public int getTorqueMode() {
        return torqueMode;
    }

    public void setTorqueMode(int torqueMode) {
        this.torqueMode = torqueMode;
    }

    public int getThrottle() {
        return throttle;
    }

    public void setThrottle(int throttle) {
        this.throttle = throttle;
    }

    public long getTotalOilConsume() {
        return totalOilConsume;
    }

    public void setTotalOilConsume(long totalOilConsume) {
        this.totalOilConsume = totalOilConsume;
    }

    public int getUreaBoxTemp() {
        return ureaBoxTemp;
    }

    public void setUreaBoxTemp(int ureaBoxTemp) {
        this.ureaBoxTemp = ureaBoxTemp;
    }

    public long getUreaInject() {
        return ureaInject;
    }

    public void setUreaInject(long ureaInject) {
        this.ureaInject = ureaInject;
    }

    public long getTotalUreaConsume() {
        return totalUreaConsume;
    }

    public void setTotalUreaConsume(long totalUreaConsume) {
        this.totalUreaConsume = totalUreaConsume;
    }

    public int getDpfOutTemp() {
        return dpfOutTemp;
    }

    public void setDpfOutTemp(int dpfOutTemp) {
        this.dpfOutTemp = dpfOutTemp;
    }

    public ExtendData(){

    }

    public ExtendData(DataInputStream dis) throws IOException {
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException {
        this.torqueMode = dis.readUnsignedByte();
        this.throttle = dis.readUnsignedByte();
        this.totalOilConsume = dis.readInt();
        this.ureaBoxTemp = dis.readUnsignedByte();
        this.ureaInject = dis.readInt();
        this.totalUreaConsume = dis.readInt();
        this.dpfOutTemp = dis.readUnsignedShort();
    }


    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.torqueMode);
        dos.write(this.throttle);
        dos.writeInt((int)this.totalOilConsume);
        dos.write(this.ureaBoxTemp);
        dos.writeInt((int)this.ureaInject);
        dos.writeInt((int)this.totalUreaConsume);
        dos.writeShort(this.dpfOutTemp);
        return os.toByteArray();
    }

    @Override
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        this.build(dis);
    }

    public static void main(String[] argus){
        ExtendData extendData=new ExtendData();

        //encode test
//        extendData.setTorqueMode(9);
//        extendData.setThrottle(30);
//        extendData.setTotalOilConsume(2000);
//        extendData.setUreaBoxTemp(20);
//        extendData.setUreaInject(100);
//        extendData.setTotalUreaConsume(1000);
//        extendData.setDpfOutTemp(120);
//        try {
//            byte[] bytes=extendData.encode();
//            System.out.println("ExtendData Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            extendData.decode(HexUtils.fromHexString("091e000007d01400000064000003e80078"));
//            System.out.println("TorqueMode="+extendData.getTorqueMode());
//            System.out.println("Throttle="+extendData.getThrottle());
//            System.out.println("TotalOilConsume="+extendData.getTotalOilConsume());
//            System.out.println("UreaBoxTemp="+extendData.getUreaBoxTemp());
//            System.out.println("UreaInject="+extendData.getUreaInject());
//            System.out.println("TotalUreaConsume="+extendData.getTotalUreaConsume());
//            System.out.println("DpfOutTemp="+extendData.getDpfOutTemp());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
