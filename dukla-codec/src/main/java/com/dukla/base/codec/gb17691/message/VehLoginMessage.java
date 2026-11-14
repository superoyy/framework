package com.dukla.base.codec.gb17691.message;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 车辆登入
 */
public class VehLoginMessage extends GB17691Message {

    /**
     * 数据采集时间 年月日时分秒 各占一个字节
     * 6 Bytes
     */
    private GatherTime gatherTime;

    /**
     * 登入流水号 车载终端每登入一次登入流水号自动加 1，从 1 开始循环累加，最大值 65531，循环周期为天
     * 2 Bytes
     */
    private int sn;

    /**
     * SIM 卡 ICCID 号（ICCID 应为终端从 SIM 卡获取的值，不应该人为填写或修改）
     */
    private String iccid;

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

    public VehLoginMessage(){
        this.setCmdId(CmdId.VEHICLE_LOGIN);
    }

    public VehLoginMessage(byte[] bytes) throws IOException{
        this.decode(bytes);
    }


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.gatherTime.encode());
        dos.writeShort(this.sn);
        dos.write(this.iccid.getBytes(StandardCharsets.US_ASCII));
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(int dataUnitLength,final DataInputStream dis)  throws IOException {
        byte[] b = new byte[6];
        dis.readFully(b);
        this.gatherTime = new GatherTime(b);
        this.sn = dis.readUnsignedShort();
        b = new byte[dataUnitLength-8];
        dis.readFully(b);
        this.iccid=new String(b,StandardCharsets.US_ASCII);
    }

    public static void main(String[] argus){
        VehLoginMessage vehLoginMessage=new VehLoginMessage();

        //encode test
//        vehLoginMessage.setVin("12345678901234567");
//        vehLoginMessage.setEncryptType(0);
//        vehLoginMessage.setTermSoftwareVer(1);
//
//        GatherTime gatherTime=new GatherTime();
//        gatherTime.setYear(22);
//        gatherTime.setMonth(10);
//        gatherTime.setDay(11);
//        gatherTime.setHour(17);
//        gatherTime.setMinute(30);
//        gatherTime.setSecond(0);
//
//        vehLoginMessage.setGatherTime(gatherTime);
//        vehLoginMessage.setIccid("123");
//        vehLoginMessage.setSn(1);
//        try {
//            byte[] bytes=vehLoginMessage.encode();
//            System.out.println("VehLoginMessage Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            vehLoginMessage.decode(HexUtils.fromHexString("23230131323334353637383930313233343536370100000b160a0b111e00000131323313"));
//            System.out.println("CmdId="+vehLoginMessage.getCmdId().getVal());
//            System.out.println("Vin="+vehLoginMessage.getVin());
//            System.out.println("TermSoftwareVer="+vehLoginMessage.getTermSoftwareVer());
//            System.out.println("EncryptType="+vehLoginMessage.getEncryptType());
//            System.out.println("DataUnitLength="+vehLoginMessage.getDataUnitLength());
//            System.out.println("GatherTime.Year="+vehLoginMessage.getGatherTime().getYear());
//            System.out.println("GatherTime.Month="+vehLoginMessage.getGatherTime().getMonth());
//            System.out.println("GatherTime.Day="+vehLoginMessage.getGatherTime().getDay());
//            System.out.println("GatherTime.Hour="+vehLoginMessage.getGatherTime().getHour());
//            System.out.println("GatherTime.Minute="+vehLoginMessage.getGatherTime().getMinute());
//            System.out.println("GatherTime.Second="+vehLoginMessage.getGatherTime().getSecond());
//            System.out.println("Sn="+vehLoginMessage.getSn());
//            System.out.println("Iccid="+vehLoginMessage.getIccid());
//            System.out.println("Bcc="+vehLoginMessage.getBcc());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
