package com.dukla.base.codec.gb17691.element;

import com.dukla.base.codec.util.HexUtils;

import java.io.*;

/**
 * 混合动力车 信息
 */
public class PHEVData implements GB17691DataElement {

    public static final int TYPE_CODE= 0x04;

    /**
     * 电机转速
     * 2 Byte
     * 精度：1 rpm/bit
     * 偏移量：0
     * 数据范围:0～65535
     * “0xFF，0xFF”表示无效
     */
    private int motorRpm;

    /**
     * 电机负荷百分比
     * 1 Byte
     * 精度：1%/bit
     * 偏移量：0%
     * 数据范围:0～100
     * “0xFF”表示无效
     */
    private int motorLoadPer;

    /**
     * 电池电压
     * 2 Byte
     * 精度：0.1 V/bit
     * 偏移量：0 V
     * 数据范围:0～6553.4
     * “0xFF，0xFF”表示无效
     */
    private int batVolt;

    /**
     * 电池电流
     * 2 Byte
     * 精度：0.1 A/bit
     * 偏移量：-1000 A
     * 数据范围:-1000 A～5553.4 A
     * “0xFF，0xFF”表示无效
     */
    private int batCurr;

    /**
     * 电池电量百分比（SOC）
     * 1 Byte
     * 精度：1%/bit
     * 偏移量：-0%
     * 数据范围:0～100
     * “0xFF”表示无效
     */
    private int soc;

    public int getMotorRpm() {
        return motorRpm;
    }

    public void setMotorRpm(int motorRpm) {
        this.motorRpm = motorRpm;
    }

    public int getMotorLoadPer() {
        return motorLoadPer;
    }

    public void setMotorLoadPer(int motorLoadPer) {
        this.motorLoadPer = motorLoadPer;
    }

    public int getBatVolt() {
        return batVolt;
    }

    public void setBatVolt(int batVolt) {
        this.batVolt = batVolt;
    }

    public int getBatCurr() {
        return batCurr;
    }

    public void setBatCurr(int batCurr) {
        this.batCurr = batCurr;
    }

    public int getSoc() {
        return soc;
    }

    public void setSoc(int soc) {
        this.soc = soc;
    }

    public PHEVData(){

    }

    public PHEVData(DataInputStream dis) throws IOException {
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException {
        this.motorRpm = dis.readUnsignedShort();
        this.motorLoadPer = dis.readUnsignedByte();
        this.batVolt = dis.readUnsignedShort();
        this.batCurr = dis.readUnsignedShort();
        this.soc = dis.readUnsignedByte();
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.writeShort(this.motorRpm);
        dos.write(this.motorLoadPer);
        dos.writeShort(this.batVolt);
        dos.writeShort(this.batCurr);
        dos.write(this.soc);
        return os.toByteArray();
    }

    @Override
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        this.build(dis);
    }

    public static void main(String[] argus){
        PHEVData phevData=new PHEVData();

        //encode test
        phevData.setMotorRpm(2000);
        phevData.setMotorLoadPer(30);
        phevData.setBatVolt(220);
        phevData.setBatCurr(5);
        phevData.setSoc(70);
        try {
            byte[] bytes=phevData.encode();
            System.out.println("PHEVData Hex="+ HexUtils.toHexString(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //decode test
//        try {
//            phevData.decode(HexUtils.fromHexString("07d01e00dc000546"));
//            System.out.println("MotorRpm="+phevData.getMotorRpm());
//            System.out.println("MotorLoadPer="+phevData.getMotorLoadPer());
//            System.out.println("BatVolt="+phevData.getBatVolt());
//            System.out.println("BatCurr="+phevData.getBatCurr());
//            System.out.println("Soc="+phevData.getSoc());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
