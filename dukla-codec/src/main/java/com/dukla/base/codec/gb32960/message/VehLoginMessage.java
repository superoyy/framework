package com.dukla.base.codec.gb32960.message;

import com.dukla.base.codec.gb32960.element.GatherTime;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 车辆登入
 */
public class VehLoginMessage extends GB32960Message{

    private GatherTime gatherTime;

    //登入流水号 车载终端每登入一次登入流水号自动加 1，从 1 开始循环累加，最大值 65531，循环周期为天 |
    private int sn;

    //SIM 卡 ICCID 号（ICCID 应为终端从 SIM 卡获取的值，不应该人为填写或修改）
    private String iccid;

    //可充电储能子系统数 可充电储能子系统数 n，有效值范围：0~250
    private int subSysNm;

    //可充电储能系统编码长度 可充电储能系统编码长度 m，有效值范围：0~50，“0”表示不上传该编码
    private int subSysNmLen;

    //可充电储能系统编码 可充电储能系统编码宜为终端从车辆获取的值
    private List<String> subSysSns;


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        this.gatherTime.encode(dos);
        dos.writeShort(this.sn);

        byte[] iccidArray=new byte[20];
        if(this.iccid!=null){
            byte[] array = this.iccid.getBytes(StandardCharsets.US_ASCII);
            System.arraycopy(array, 0, iccidArray, 0, Math.min(array.length, 20));
        }
        dos.write(iccidArray);

        if(this.subSysSns!=null){
            dos.writeByte(this.subSysSns.size());
        }else{
            dos.writeByte(0);
        }
        dos.writeByte(this.subSysNmLen);
        if(this.subSysSns!=null){
            for (String subSysSn:this.subSysSns){
                dos.write(subSysSn.getBytes(StandardCharsets.US_ASCII));
            }
        }
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(DataInputStream dis) throws IOException {
        this.setGatherTime(GatherTime.decode(dis));
        this.setSn(dis.readUnsignedShort());
        byte[] iccidArray=new byte[20];
        dis.read(iccidArray);
        this.setIccid(new String(iccidArray,StandardCharsets.US_ASCII));
        this.setSubSysNm(dis.readUnsignedByte());
        this.setSubSysNmLen(dis.readUnsignedByte());
        List<String> subSysSns=new ArrayList<>();
        if(this.subSysNm >= 0 && this.subSysNm <= 250 && this.subSysNmLen > 0 && this.subSysNmLen <= 50){
            for(int i=0;i<this.subSysNm;i++){
                byte[] subSysSnArray=new byte[this.subSysNmLen];
                dis.read(subSysSnArray);
                subSysSns.add(new String(subSysSnArray,StandardCharsets.US_ASCII));
            }
        }
        this.setSubSysSns(subSysSns);
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

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public int getSubSysNm() {
        return subSysNm;
    }

    public void setSubSysNm(int subSysNm) {
        this.subSysNm = subSysNm;
    }

    public int getSubSysNmLen() {
        return subSysNmLen;
    }

    public void setSubSysNmLen(int subSysNmLen) {
        this.subSysNmLen = subSysNmLen;
    }

    public List<String> getSubSysSns() {
        return subSysSns;
    }

    public void setSubSysSns(List<String> subSysSns) {
        this.subSysSns = subSysSns;
    }
}
