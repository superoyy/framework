package com.dukla.base.codec.gb17691.element;

import com.dukla.base.codec.util.BitCodecUtils;
import com.dukla.base.codec.util.HexUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * OBD 信息
 */
public class OBDData implements GB17691DataElement {
    /**
     * 信息类型编码
     */
    public static final int TYPE_CODE = 0x01;

    /**
     * OBD诊断协议
     * 1 Byte
     * 有效范围0～2
     * “0” 代表ISO15765
     * “1” 代表ISO27145
     * “2” 代表SAEJ1939
     * “0xFE” 表示无效
     */
    private int obdProtocol;

    /**
     * MIL状态
     * 1 Byte
     * 有效范围0～1
     * “0”代表未点亮
     * “1”代表点亮
     * “0xFE”表示无效
     */
    private int milStatus;

    /**
     * 诊断支持状态
     * 2 Byte
     * 每一位的定义如下：
     * 1 Catalyst monitoring Status 催化转化器监控
     * 2 Heated catalyst monitoring Status 加热催化转化器监控
     * 3 Evaporative system monitoring Status蒸发系统监控
     * 4 Secondary air system monitoring Status 二次空气系统监控
     * 5 A/C system refrigerant monitoring Status A/C系统致冷剂监控
     * 6 Exhaust Gas Sensor monitoring Status 排气传感器加热器监控
     * 7 Exhaust Gas Sensor heater monitoring Status 排气传感器加热器监控
     * 8 EGR/VVT system monitoring EGR系统和VVT 监控
     * 9 Cold start aid system monitoring Status 冷启动辅助系统监控
     * 10 Boost pressure control system monitoring Status 增压压力控制系统
     * 11 Diesel Particulate Filter (DPF) monitoring Status DPF监控
     * 12 NOx converting catalyst and/or NOx adsorber monitoring Status 选择性催化还原系统（SCR）或 NOx吸附器
     * 13 NMHC converting catalyst monitoring Status NMHC氧化 催化器监控
     * 14 Misfire monitoring support失火监控
     * 15 Fuel system monitoring support燃油系统监控
     * 16 Comprehensive component monitoring support 综合成分监控
     * 每一位的含义：0=不支持；1=支持
     */
    private final boolean[] diagSptStatus=new boolean[16];

    /**
     * 诊断就绪状态
     * 2 Byte
     * 每一位的定义如下：
     * 1 Catalyst monitoring Status 催化转化器监控
     * 2 Heated catalyst monitoring Status 加热催化转化器监控
     * 3 Evaporative system monitoring Status蒸发系统监控
     * 4 Secondary air system monitoring Status 二次空气系统监控
     * 5 A/C system refrigerant monitoring Status A/C系统致冷剂监控
     * 6 Exhaust Gas Sensor monitoring Status 排气传感器监控
     * 7 Exhaust Gas Sensor heater monitoring Status 排气传感器加热器监控
     * 8 EGR/VVT system monitoring EGR系统和VVT 监控
     * 9 Cold start aid system monitoring Status 冷启动辅助系统监控
     * 10 Boost pressure control system monitoring Status 增压压力控制系统
     * 11 Diesel Particulate Filter (DPF) monitoring Status DPF监控
     * 12 NOx converting catalyst and/or NOx adsorber monitoring Status 选择性催化还原系统（SCR）或 NOx吸附器
     * 13 NMHC converting catalyst monitoring Status NMHC氧化催化器监控
     * 14 Misfire monitoring support失火监控
     * 15 Fuel system monitoring support燃油系统监控
     * 16 Comprehensive component monitoring support 综合成分监控
     * 每一位的含义：0=测试完成或者不支持；1=测试未完成
     */
    private final boolean[] diagRdyStatus=new boolean[16];

    /**
     * 车辆识别代号
     * 17 Byte
     */
    private String vin;

    /**
     * 软件标定识别号
     * 18 Byte
     * 生产企业自定义，字母或数字组成，不足后面补字符“0”
     * “0xFF”表示无效
     */
    private String softVer;

    /**
     * 标定验证码
     * 18 Byte
     * 生产企业自定义，字母或数字组成，不足后面补字符“0”
     * “0xFF”表示无效
     */
    private String cvn;

    /**
     * 在用监测频率
     * 36 Byte
     * “0xFF”表示无效
     */
    private String iupr;

    /**
     * 故障码总数
     * 1 Byte
     * “0xFF”表示无效
     */
    private int dtcCount;

    /**
     * 故障码集合
     * 1个DTC 4字节
     */
    private String[] dtcs;

    public int getObdProtocol() {
        return obdProtocol;
    }

    public void setObdProtocol(int obdProtocol) {
        this.obdProtocol = obdProtocol;
    }

    public int getMilStatus() {
        return milStatus;
    }

    public void setMilStatus(int milStatus) {
        this.milStatus = milStatus;
    }

    public boolean[] getDiagSptStatus() {
        return diagSptStatus;
    }

    public void setDiagSptStatus(boolean[] diagSptStatus) {
        System.arraycopy(diagSptStatus,0,this.diagSptStatus,0,16);
    }

    public boolean[] getDiagRdyStatus() {
        return diagRdyStatus;
    }

    public void setDiagRdyStatus(boolean[] diagRdyStatus) {
        System.arraycopy(diagRdyStatus,0,this.diagRdyStatus,0,16);
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer;
    }

    public String getCvn() {
        return cvn;
    }

    public void setCvn(String cvn) {
        this.cvn = cvn;
    }

    public String getIupr() {
        return iupr;
    }

    public void setIupr(String iupr) {
        this.iupr = iupr;
    }

    public int getDtcCount() {
        return dtcs.length;
    }

    public String[] getDtcs() {
        return dtcs;
    }

    public void setDtcs(String[] dtcs) {
        this.dtcs = dtcs;
    }

    public OBDData(){

    }

    public OBDData(DataInputStream dis) throws IOException{
        this.build(dis);
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);

        dos.writeByte(this.getObdProtocol());
        dos.writeByte(this.getMilStatus());

        int sptStatus = 0;
        for(int i = 0; i < this.diagSptStatus.length; i++){
            if(this.diagSptStatus[i]){
                sptStatus= BitCodecUtils.setPosTo1(sptStatus,i);
            }
        }
        dos.writeShort(sptStatus);//诊断支持状态

        int rdyStatus = 0;
        for(int i = 0; i < this.diagRdyStatus.length; i++){
            if(this.diagRdyStatus[i]){
                rdyStatus=BitCodecUtils.setPosTo1(rdyStatus,i);
            }
        }
        dos.writeShort(rdyStatus);//诊断就绪状态

        byte[] vinBytes = this.vin.getBytes(StandardCharsets.US_ASCII);
        if(vinBytes.length >= 17){
            dos.write(vinBytes,0,17);
        }else{
            dos.write(vinBytes);
            for(int i=0;i < (17-vinBytes.length);i++){
                dos.write(0x30);//补0
            }
        }

        byte[] softVerBytes = this.softVer.getBytes(StandardCharsets.US_ASCII);
        if(softVerBytes.length >= 18){
            dos.write(softVerBytes,0,18);
        }else{
            dos.write(softVerBytes);
            for(int i=0;i < (18-softVerBytes.length);i++){
                dos.write(0x30);//补0
            }
        }

        byte[] cvnBytes = this.cvn.getBytes(StandardCharsets.US_ASCII);
        if(cvnBytes.length >= 18){
            dos.write(cvnBytes,0,18);
        }else{
            dos.write(cvnBytes);
            for(int i=0;i < (18-cvnBytes.length);i++){
                dos.write(0x30);//补0
            }
        }

        byte[] iuprBytes = this.iupr.getBytes(StandardCharsets.US_ASCII);
        if(iuprBytes.length >= 36){
            dos.write(iuprBytes,0,36);
        }else{
            dos.write(iuprBytes);
            for(int i=0;i < (36-iuprBytes.length);i++){
                dos.write(0x30);//补0
            }
        }

        dos.writeByte(this.getDtcCount());
        for(String dtc : this.getDtcs()){
            dos.write(HexUtils.fromHexString(dtc),0,4);
        }
        return os.toByteArray();
    }

    @Override
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException{
        this.obdProtocol = dis.readUnsignedByte();
        this.milStatus = dis.readUnsignedByte();

        int sptStatus = dis.readUnsignedShort();
        for(int i = 0;i<this.diagSptStatus.length;i++){
            this.diagSptStatus[i] = BitCodecUtils.is1AtPos(sptStatus,i);
        }
        int rdyStatus = dis.readUnsignedShort();
        for(int i = 0;i<this.diagRdyStatus.length;i++){
            this.diagRdyStatus[i] = BitCodecUtils.is1AtPos(rdyStatus,i);
        }

        byte[] vinBytes=new byte[17];
        dis.readFully(vinBytes);
        this.vin=new String(vinBytes,StandardCharsets.US_ASCII);

        byte[] softVerBytes=new byte[18];
        dis.readFully(softVerBytes);
        this.softVer=new String(softVerBytes,StandardCharsets.US_ASCII);

        byte[] cvnVerBytes=new byte[18];
        dis.readFully(cvnVerBytes);
        this.cvn=new String(cvnVerBytes,StandardCharsets.US_ASCII);

        byte[] iuprBytes=new byte[36];
        dis.readFully(iuprBytes);
        this.iupr=new String(iuprBytes,StandardCharsets.US_ASCII);

        this.dtcCount = dis.readUnsignedByte();
        if(this.dtcCount > 0 && this.dtcCount < 254){
            this.dtcs=new String[this.dtcCount];
            for(int i=0;i<this.dtcCount;i++){
                byte[] dtc=new byte[4];
                dis.readFully(dtc);
                this.dtcs[i]=HexUtils.toHexString(dtc);
            }
        }
    }

    public static void main(String[] argus){
        OBDData obdData=new OBDData();

        //encode test
//        obdData.setObdProtocol(1);
//        obdData.setMilStatus(1);
//        obdData.setDiagSptStatus(new boolean[]{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false});
//        obdData.setDiagRdyStatus(new boolean[]{true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false});
//        obdData.setVin("vin1234567890abcd");
//        obdData.setSoftVer("soft1234567890abcd");
//        obdData.setCvn("cvn1234567890abcde");
//        obdData.setIupr("iupr1234567890abcdiupr1234567890abcd");
//        obdData.setDtcs(new String[]{"2323ff00","7e7eff11"});
//        try {
//            byte[] bytes=obdData.encode();
//            System.out.println("OBD DATA Bytes="+HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        String dataHex="01010001000f76696e3132333435363738393061626364736f6674313233343536373839306162636463766e313233343536373839306162636465697570723132333435363738393061626364697570723132333435363738393061626364022323ff007e7eff11";
//        try {
//            obdData.decode(HexUtils.fromHexString(dataHex));
//            System.out.println("ObdProtocol="+obdData.getObdProtocol());
//            System.out.println("MilStatus="+obdData.getMilStatus());
//            System.out.print("DiagSptStatus=");
//            for(boolean val:obdData.getDiagSptStatus()){
//                System.out.print(val);
//                System.out.print(",");
//            }
//            System.out.println();
//            System.out.print("DiagRdyStatus=");
//            for(boolean val:obdData.getDiagRdyStatus()){
//                System.out.print(val);
//                System.out.print(",");
//            }
//            System.out.println();
//            System.out.println("vin="+obdData.getVin());
//            System.out.println("SoftVer="+obdData.getSoftVer());
//            System.out.println("cvn="+obdData.getCvn());
//            System.out.println("iupr="+obdData.getIupr());
//            System.out.println("DtcCount="+obdData.getDtcCount());
//            System.out.print("Dtcs=");
//            for(String val:obdData.getDtcs()){
//                System.out.print(val);
//                System.out.print(",");
//            }
//            System.out.println();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
