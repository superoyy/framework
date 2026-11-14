package com.dukla.base.codec.gb32960.element;

import java.io.*;

/**
 * 极值数据
 */
public class ExtremeData  implements GB32960DataElement {

    //最高电压电池子系统号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效
    private int maxVoltageSubSysNo ;
    //最高电压电池单体代号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int maxVoltageSingNo;
    //电池单体电压最高值 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int maxVoltage;
    //最低电压电池子系代号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int minVoltageSubSysNo;
    //最低电压电池单体代号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int minVoltageSingNo ;
    //电池单体最低电压值 有效值范围：0～15000（表示0V～15V），最小计量单元：0.001V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int minVoltage;
    //最高温度子系统号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int maxNtcSubSysNo;
    //最高温度探针序号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int maxNtcNo;
    //最高温度值 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
    private int maxNtc;
    //最低温度子系统号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int minNtcSubSysNo;
    //最低温度探针序号 有效值范围：1～250，“0xFE”表示异常，“0xFF”表示无效。
    private int minNtcNo;
    //最低温度值 有效值范围：0～250（数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
    private int minNtc;


    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.maxVoltageSubSysNo);//1 (byte)
        dos.writeByte(this.maxVoltageSingNo);//1 (byte)
        dos.writeShort(this.maxVoltage);//2 (byte)
        dos.writeByte(this.minVoltageSubSysNo);//1 (byte)
        dos.writeByte(this.minVoltageSingNo);//1 (byte)
        dos.writeShort(this.minVoltage);//2 (byte)
        dos.writeByte(this.maxNtcSubSysNo);//1 (byte)
        dos.writeByte(this.maxNtcNo);//1 (byte)
        dos.writeByte(this.maxNtc);//1 (byte)
        dos.writeByte(this.minNtcSubSysNo);//1 (byte)
        dos.writeByte(this.minNtcNo);//1 (byte)
        dos.writeByte(this.minNtc);//1 (byte)
    }

    public static ExtremeData decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        return ExtremeData.decode(dis);
    }

    public static ExtremeData decode(DataInputStream dis) throws IOException {
        ExtremeData extremeData=new ExtremeData();

        extremeData.setMaxVoltageSubSysNo(dis.readUnsignedByte());
        extremeData.setMaxVoltageSingNo(dis.readUnsignedByte());
        extremeData.setMaxVoltage(dis.readUnsignedByte());
        extremeData.setMinVoltageSubSysNo(dis.readUnsignedByte());
        extremeData.setMinVoltageSingNo(dis.readUnsignedByte());
        extremeData.setMinVoltage(dis.readUnsignedShort());
        extremeData.setMaxNtcSubSysNo(dis.readUnsignedByte());
        extremeData.setMaxNtcNo(dis.readUnsignedByte());
        extremeData.setMaxNtc(dis.readUnsignedByte());
        extremeData.setMinNtcSubSysNo(dis.readUnsignedByte());
        extremeData.setMinNtcNo(dis.readUnsignedByte());
        extremeData.setMinNtc(dis.readUnsignedByte());

        return extremeData;
    }

    public int getMaxVoltageSubSysNo() {
        return maxVoltageSubSysNo;
    }

    public void setMaxVoltageSubSysNo(int maxVoltageSubSysNo) {
        this.maxVoltageSubSysNo = maxVoltageSubSysNo;
    }

    public int getMaxVoltageSingNo() {
        return maxVoltageSingNo;
    }

    public void setMaxVoltageSingNo(int maxVoltageSingNo) {
        this.maxVoltageSingNo = maxVoltageSingNo;
    }

    public int getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(int maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public int getMinVoltageSubSysNo() {
        return minVoltageSubSysNo;
    }

    public void setMinVoltageSubSysNo(int minVoltageSubSysNo) {
        this.minVoltageSubSysNo = minVoltageSubSysNo;
    }

    public int getMinVoltageSingNo() {
        return minVoltageSingNo;
    }

    public void setMinVoltageSingNo(int minVoltageSingNo) {
        this.minVoltageSingNo = minVoltageSingNo;
    }

    public int getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(int minVoltage) {
        this.minVoltage = minVoltage;
    }

    public int getMaxNtcSubSysNo() {
        return maxNtcSubSysNo;
    }

    public void setMaxNtcSubSysNo(int maxNtcSubSysNo) {
        this.maxNtcSubSysNo = maxNtcSubSysNo;
    }

    public int getMaxNtcNo() {
        return maxNtcNo;
    }

    public void setMaxNtcNo(int maxNtcNo) {
        this.maxNtcNo = maxNtcNo;
    }

    public int getMaxNtc() {
        return maxNtc;
    }

    public void setMaxNtc(int maxNtc) {
        this.maxNtc = maxNtc;
    }

    public int getMinNtcSubSysNo() {
        return minNtcSubSysNo;
    }

    public void setMinNtcSubSysNo(int minNtcSubSysNo) {
        this.minNtcSubSysNo = minNtcSubSysNo;
    }

    public int getMinNtcNo() {
        return minNtcNo;
    }

    public void setMinNtcNo(int minNtcNo) {
        this.minNtcNo = minNtcNo;
    }

    public int getMinNtc() {
        return minNtc;
    }

    public void setMinNtc(int minNtc) {
        this.minNtc = minNtc;
    }
}
