package com.dukla.base.codec.gb17691.message;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 车辆登出
 */
public class VehLogoutMessage extends GB17691Message {

    /**
     * 登出时间 年月日时分秒 各占一个字节
     * 6 Bytes
     */
    private GatherTime gatherTime;

    /**
     * 登出流水号 车载终端每登入一次登入流水号自动加 1，从 1 开始循环累加，最大值 65531，循环周期为天
     * 2 Bytes
     */
    private int sn;


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

    public VehLogoutMessage(){
        this.setCmdId(CmdId.VEHICLE_LOGOUT);
    }

    public VehLogoutMessage(byte[] bytes) throws IOException{
        this.decode(bytes);
    }


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.gatherTime.encode());
        dos.writeShort(this.sn);
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(int dataUnitLength,final DataInputStream dis)  throws IOException {
        byte[] b = new byte[6];
        dis.readFully(b);
        this.gatherTime = new GatherTime(b);
        this.sn = dis.readUnsignedShort();
    }

    public static void main(String[] argus){
        VehLogoutMessage vehLogoutMessage=new VehLogoutMessage();

        //encode test
//        vehLogoutMessage.setVin("12345678901234567");
//        vehLogoutMessage.setEncryptType(0);
//        vehLogoutMessage.setTermSoftwareVer(1);
//
//        GatherTime gatherTime=new GatherTime();
//        gatherTime.setYear(22);
//        gatherTime.setMonth(10);
//        gatherTime.setDay(11);
//        gatherTime.setHour(17);
//        gatherTime.setMinute(30);
//        gatherTime.setSecond(0);
//
//        vehLogoutMessage.setGatherTime(gatherTime);
//        vehLogoutMessage.setSn(1);
//        try {
//            byte[] bytes=vehLogoutMessage.encode();
//            System.out.println("VehLogoutMessage Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            vehLogoutMessage.decode(HexUtils.fromHexString("232304313233343536373839303132333435363701000008160a0b111e00000125"));
//            System.out.println("CmdId="+vehLogoutMessage.getCmdId().getVal());
//            System.out.println("Vin="+vehLogoutMessage.getVin());
//            System.out.println("TermSoftwareVer="+vehLogoutMessage.getTermSoftwareVer());
//            System.out.println("EncryptType="+vehLogoutMessage.getEncryptType());
//            System.out.println("DataUnitLength="+vehLogoutMessage.getDataUnitLength());
//            System.out.println("GatherTime.Year="+vehLogoutMessage.getGatherTime().getYear());
//            System.out.println("GatherTime.Month="+vehLogoutMessage.getGatherTime().getMonth());
//            System.out.println("GatherTime.Day="+vehLogoutMessage.getGatherTime().getDay());
//            System.out.println("GatherTime.Hour="+vehLogoutMessage.getGatherTime().getHour());
//            System.out.println("GatherTime.Minute="+vehLogoutMessage.getGatherTime().getMinute());
//            System.out.println("GatherTime.Second="+vehLogoutMessage.getGatherTime().getSecond());
//            System.out.println("Sn="+vehLogoutMessage.getSn());
//            System.out.println("Bcc="+vehLogoutMessage.getBcc());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
