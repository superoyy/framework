package com.dukla.base.codec.gb32960.element;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 燃料电池数据
 */
public class FuelCellData  implements GB32960DataElement {

    //燃料电池电压 有效值范围：0～20000（表示0V～2000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
    private int voltage;

    //燃料电池电流 有效值范围： 0～20000（表示0A～+2000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int current;

    //燃料消耗率 有效值范围：0～60000（表示0kg/100km～600kg/100km），最小计量单元：0.01kg/100km，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int conRate;

    //探针温度值 有效值范围：0～240（数值偏移量40℃，表示-40℃～+200℃），最小计量单元：1℃。
    private List<Integer> ntcs;

    //氢系统中最高温度 有效值范围：0～2400（偏移量40℃，表示-40℃～200℃），最小计量单元：0.1℃，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int maxNtc;
    //氢气最高温度传感器代号 有效值范围：1～252，“0xFE”表示异常，“0xFF”表示无效。
    private int maxNtcNo;
    //氢气最高浓度 有效值范围：0～60000（表示0ppm～50000ppm），最小计量单元：1ppm，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int hcOfh;

    //氢气最高浓度传感器代号 有效值范围：1～252，“0xFE”表示异常，“0xFF”表示无效。
    private int hcOfhNo;

    //氢气最高压力 有效值范围：0～1000（表示0MPa～100MPa），最小计量单元：0.1MPa.
    private int maxVoltageOfh ;

    //氢气最高压力传感器代号 有效值范围：1～252，“0xFE”表示异常，“0xFF”表示无效。
    private int maxVoltageOfhNo;

    //高压 DC/DC 状态   0x01：工作；0x02：断开；“0xFE”表示异常，“0xFF”表示无效。
    private int dc;


    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeShort(this.voltage);
        dos.writeShort(this.current);
        dos.writeShort(this.conRate);
        dos.writeShort((this.ntcs!=null && this.ntcs.size() <= 65531) ? this.ntcs.size() : 0);//probeCount  燃料电池温度探针总数，有效值范围：0～65531，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        if(this.ntcs!=null && this.ntcs.size() <= 65531){
            for (Integer ntc : this.ntcs) {
                dos.writeByte(ntc);
            }
        }
        dos.writeShort(this.maxNtc);
        dos.writeByte(this.maxNtcNo);
        dos.writeShort(this.hcOfh);
        dos.writeByte(this.hcOfhNo);
        dos.writeShort(this.maxVoltageOfh);
        dos.writeByte(this.maxVoltageOfhNo);
        dos.writeByte(this.dc);
    }

    public static FuelCellData decode(DataInputStream dis) throws IOException {
        FuelCellData fuelCellData=new FuelCellData();

        fuelCellData.setVoltage(dis.readUnsignedShort());
        fuelCellData.setCurrent(dis.readUnsignedShort());
        fuelCellData.setConRate(dis.readUnsignedShort());
        int probeCount = dis.readUnsignedShort();
        if(probeCount <= 65531){
            List<Integer> ntcs = new ArrayList<>();
            for(int i = 0; i < probeCount; i++){
                ntcs.add(dis.readUnsignedByte());
            }
            fuelCellData.setNtcs(ntcs);
        }
        fuelCellData.setMaxNtc(dis.readUnsignedShort());
        fuelCellData.setMaxNtcNo(dis.readUnsignedByte());
        fuelCellData.setHcOfh(dis.readUnsignedShort());
        fuelCellData.setHcOfhNo(dis.readUnsignedByte());
        fuelCellData.setMaxVoltageOfh(dis.readUnsignedShort());
        fuelCellData.setMaxVoltageOfhNo(dis.readUnsignedByte());
        fuelCellData.setDc(dis.readUnsignedByte());

        return fuelCellData;
    }

    public static FuelCellData decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        return FuelCellData.decode(dis);
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getConRate() {
        return conRate;
    }

    public void setConRate(int conRate) {
        this.conRate = conRate;
    }

    public List<Integer> getNtcs() {
        return ntcs;
    }

    public void setNtcs(List<Integer> ntcs) {
        this.ntcs = ntcs;
    }

    public int getMaxNtc() {
        return maxNtc;
    }

    public void setMaxNtc(int maxNtc) {
        this.maxNtc = maxNtc;
    }

    public int getMaxNtcNo() {
        return maxNtcNo;
    }

    public void setMaxNtcNo(int maxNtcNo) {
        this.maxNtcNo = maxNtcNo;
    }

    public int getHcOfh() {
        return hcOfh;
    }

    public void setHcOfh(int hcOfh) {
        this.hcOfh = hcOfh;
    }

    public int getHcOfhNo() {
        return hcOfhNo;
    }

    public void setHcOfhNo(int hcOfhNo) {
        this.hcOfhNo = hcOfhNo;
    }

    public int getMaxVoltageOfh() {
        return maxVoltageOfh;
    }

    public void setMaxVoltageOfh(int maxVoltageOfh) {
        this.maxVoltageOfh = maxVoltageOfh;
    }

    public int getMaxVoltageOfhNo() {
        return maxVoltageOfhNo;
    }

    public void setMaxVoltageOfhNo(int maxVoltageOfhNo) {
        this.maxVoltageOfhNo = maxVoltageOfhNo;
    }

    public int getDc() {
        return dc;
    }

    public void setDc(int dc) {
        this.dc = dc;
    }
}
