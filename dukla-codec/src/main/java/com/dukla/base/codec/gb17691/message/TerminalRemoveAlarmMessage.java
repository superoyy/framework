package com.dukla.base.codec.gb17691.message;

import com.dukla.base.codec.gb17691.element.LocationData;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 终端拆除告警
 */
public class TerminalRemoveAlarmMessage extends GB17691Message {

    /**
     * 采集时间 年月日时分秒 各占一个字节
     * 6 Bytes
     */
    private GatherTime gatherTime;

    /**
     * 流水号 车载终端每登入一次登入流水号自动加 1，从 1 开始循环累加，最大值 65531，循环周期为天
     * 2 Bytes
     */
    private int sn;

    /**
     * 位置数据
     */
    private LocationData locationData;

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

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public TerminalRemoveAlarmMessage(){
        this.setCmdId(CmdId.TERMINAL_REMOVE_ALARM);
    }

    public TerminalRemoveAlarmMessage(byte[] bytes) throws IOException{
        this.decode(bytes);
    }


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.gatherTime.encode());
        dos.writeShort(this.sn);
        dos.write(this.locationData.encode());
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(int dataUnitLength,final DataInputStream dis)  throws IOException {
        byte[] b = new byte[6];
        dis.readFully(b);
        this.gatherTime = new GatherTime(b);
        this.sn = dis.readUnsignedShort();
        b = new byte[9];
        dis.readFully(b);
        this.locationData = new LocationData(b);
    }

    public static void main(String[] argus){
        TerminalRemoveAlarmMessage terminalRemoveAlarmMessage=new TerminalRemoveAlarmMessage();

        //encode test
//        terminalRemoveAlarmMessage.setVin("12345678901234567");
//        terminalRemoveAlarmMessage.setEncryptType(0);
//        terminalRemoveAlarmMessage.setTermSoftwareVer(1);
//
//        GatherTime gatherTime=new GatherTime();
//        gatherTime.setYear(22);
//        gatherTime.setMonth(10);
//        gatherTime.setDay(11);
//        gatherTime.setHour(17);
//        gatherTime.setMinute(30);
//        gatherTime.setSecond(0);
//        terminalRemoveAlarmMessage.setGatherTime(gatherTime);
//
//        terminalRemoveAlarmMessage.setSn(1);
//
//        LocationData locationData=new LocationData();
//        locationData.setStatus(0);
//        locationData.setLngDir(0);
//        locationData.setLatDir(0);
//        locationData.setLat(38000003);
//        locationData.setLng(126000002);
//        terminalRemoveAlarmMessage.setLocationData(locationData);
//        try {
//            byte[] bytes=terminalRemoveAlarmMessage.encode();
//            System.out.println("TerminalRemoveAlarmMessage Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            terminalRemoveAlarmMessage.decode(HexUtils.fromHexString("232306313233343536373839303132333435363701000011160a0b111e0000010007829b820243d583b5"));
//            System.out.println("CmdId="+terminalRemoveAlarmMessage.getCmdId().getVal());
//            System.out.println("Vin="+terminalRemoveAlarmMessage.getVin());
//            System.out.println("TermSoftwareVer="+terminalRemoveAlarmMessage.getTermSoftwareVer());
//            System.out.println("EncryptType="+terminalRemoveAlarmMessage.getEncryptType());
//            System.out.println("DataUnitLength="+terminalRemoveAlarmMessage.getDataUnitLength());
//            System.out.println("GatherTime.Year="+terminalRemoveAlarmMessage.getGatherTime().getYear());
//            System.out.println("GatherTime.Month="+terminalRemoveAlarmMessage.getGatherTime().getMonth());
//            System.out.println("GatherTime.Day="+terminalRemoveAlarmMessage.getGatherTime().getDay());
//            System.out.println("GatherTime.Hour="+terminalRemoveAlarmMessage.getGatherTime().getHour());
//            System.out.println("GatherTime.Minute="+terminalRemoveAlarmMessage.getGatherTime().getMinute());
//            System.out.println("GatherTime.Second="+terminalRemoveAlarmMessage.getGatherTime().getSecond());
//            System.out.println("Sn="+terminalRemoveAlarmMessage.getSn());
//            System.out.println("Location.Status="+terminalRemoveAlarmMessage.getLocationData().getStatus());
//            System.out.println("Location.LatDir="+terminalRemoveAlarmMessage.getLocationData().getLatDir());
//            System.out.println("Location.LngDir="+terminalRemoveAlarmMessage.getLocationData().getLngDir());
//            System.out.println("Location.Lng="+terminalRemoveAlarmMessage.getLocationData().getLng());
//            System.out.println("Location.Lat="+terminalRemoveAlarmMessage.getLocationData().getLat());
//            System.out.println("Bcc="+terminalRemoveAlarmMessage.getBcc());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
