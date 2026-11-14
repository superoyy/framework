package com.dukla.base.codec.gb17691.message;

import com.dukla.base.codec.gb17691.element.*;
import com.dukla.base.codec.util.HexUtils;
import com.dukla.base.codec.util.JsonUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实时上报和补发上报消息
 */
public class ReportMessage extends GB17691Message {

    /**
     * 数据发送时间 年月日时分秒 各占一个字节
     * 6 Bytes
     */
    private GatherTime reportTime;

    /**
     * 流水号 以天为单位，每包实时信息流水号唯一，从1开始累加
     * 2 Bytes
     */
    private int sn;

    /**
     * 数据单元集合
     */
    private List<ReportDataUnit> dataUnits;

    /**
     * 签名数据
     */
    private SignData signData;

    public GatherTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(GatherTime reportTime) {
        this.reportTime = reportTime;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public List<ReportDataUnit> getDataUnits() {
        return dataUnits;
    }

    public void setDataUnits(List<ReportDataUnit> dataUnits) {
        this.dataUnits = dataUnits;
    }

    public SignData getSignData() {
        return signData;
    }

    public void setSignData(SignData signData) {
        this.signData = signData;
    }

    public ReportMessage(CmdId cmdId){
        this.setCmdId(cmdId);
    }

    public ReportMessage(){
    }

    public ReportMessage(byte[] bytes) throws IOException {
        this.decode(bytes);
    }

    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.reportTime.encode());
        dos.writeShort(this.sn);
        for(ReportDataUnit dataUnit:this.dataUnits){
            dos.write(dataUnit.encode());
        }
        dos.write(this.signData.encode());
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(int dataUnitLength, DataInputStream dis) throws IOException {
        byte[] b = new byte[6];
        dis.readFully(b);
        this.reportTime = new GatherTime(b);
        this.sn = dis.readUnsignedShort();
        this.dataUnits = new ArrayList<>();
        /**
         * 协议的结构出现了OBD数据不定长+签名数据不定长的情况
         * 采用尝试-回退的方式进行读取
         */
        while (dis.available() > 1){
            try{
                //标记可回退的位置，最多读取 数据单元长度-1 的数据(1 byte是最后的bcc)
                dis.mark(dataUnitLength);
                ReportDataUnit dataUnit = new ReportDataUnit(dis);
                this.dataUnits.add(dataUnit);
            }catch (IOException ex){
                dis.reset();
                break;
            }
        }
        this.signData = new SignData(dis);
    }

    //测试编码
    public static void testEncode(){
        ReportMessage reportMessage=new ReportMessage(CmdId.REALTIME);

        reportMessage.setVin("12345678901234567");
        reportMessage.setEncryptType(1);
        reportMessage.setTermSoftwareVer(1);

        GatherTime reportTime=new GatherTime();
        reportTime.setYear(22);
        reportTime.setMonth(10);
        reportTime.setDay(11);
        reportTime.setHour(17);
        reportTime.setMinute(30);
        reportTime.setSecond(0);

        //数据单元
        reportMessage.setReportTime(reportTime);
        reportMessage.setSn(100);

        List<ReportDataUnit> dataUnits=new ArrayList<>();
        //1.obd数据
        OBDData obdData=new OBDData();
        obdData.setObdProtocol(1);
        obdData.setMilStatus(1);
        obdData.setDiagSptStatus(new boolean[]{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false});
        obdData.setDiagRdyStatus(new boolean[]{true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false});
        obdData.setVin("vin1234567890abcd");
        obdData.setSoftVer("soft1234567890abcd");
        obdData.setCvn("cvn1234567890abcde");
        obdData.setIupr("iupr1234567890abcdiupr1234567890abcd");
        obdData.setDtcs(new String[]{"2323ff00","7e7eff11"});
        dataUnits.add(new ReportDataUnit(obdData,reportTime));
        //2.dpf数据
        DPFData dpfData=new DPFData();
        dpfData.setSpeed(120);
        dpfData.setAtmoPres(60);
        dpfData.setTorque(50);
        dpfData.setFrcTorque(20);
        dpfData.setRpm(2000);
        dpfData.setFuelFlow(1000);
        dpfData.setScrUpNOx(3000);
        dpfData.setScrDownNOx(1000);
        dpfData.setReactRemain(30);
        dpfData.setAirIn(2000);
        dpfData.setScrInTemp(80);
        dpfData.setScrOutTemp(150);
        dpfData.setDpfPresDiff(2000);
        dpfData.setFuelTankLevel(70);
        LocationData locationData=new LocationData();
        locationData.setStatus(0);
        locationData.setLngDir(0);
        locationData.setLatDir(0);
        locationData.setLat(38000002);
        locationData.setLng(126000001);
        dpfData.setLocationData(locationData);
        dpfData.setMileage(700000L);
        dataUnits.add(new ReportDataUnit(dpfData,reportTime));
        //3、twc data
        TWCData twcData=new TWCData();
        twcData.setSpeed(120);
        twcData.setAtmoPres(60);
        twcData.setTorque(50);
        twcData.setFrcTorque(20);
        twcData.setRpm(2000);
        twcData.setFuelFlow(1000);
        twcData.setTwcUpOxySensor(1000);
        twcData.setTwcDownOxySensor(200);
        twcData.setAirIn(2000);
        twcData.setCoolantTemp(160);
        twcData.setTwcTempSensor(1234);
        locationData=new LocationData();
        locationData.setStatus(1);
        locationData.setLngDir(0);
        locationData.setLatDir(0);
        locationData.setLat(38000003);
        locationData.setLng(126000002);
        twcData.setLocationData(locationData);
        twcData.setMileage(800000L);
        dataUnits.add(new ReportDataUnit(twcData,reportTime));
        //4、twcNox data
        TWCNOxData twcnOxData=new TWCNOxData();
        twcnOxData.setSpeed(120);
        twcnOxData.setAtmoPres(60);
        twcnOxData.setTorque(50);
        twcnOxData.setFrcTorque(20);
        twcnOxData.setRpm(2000);
        twcnOxData.setFuelFlow(1000);
        twcnOxData.setTwcUpOxySensor(1000);
        twcnOxData.setTwcDownOxySensor(200);
        twcnOxData.setTwcDownNOxSensor(1800);
        twcnOxData.setAirIn(2000);
        twcnOxData.setCoolantTemp(160);
        twcnOxData.setTwcTempSensor(1234);
        locationData=new LocationData();
        locationData.setStatus(0);
        locationData.setLngDir(1);
        locationData.setLatDir(0);
        locationData.setLat(38000003);
        locationData.setLng(126000002);
        twcnOxData.setLocationData(locationData);
        twcnOxData.setMileage(800000L);
        dataUnits.add(new ReportDataUnit(twcnOxData,reportTime));
        //5、phev data
        PHEVData phevData=new PHEVData();
        phevData.setMotorRpm(2000);
        phevData.setMotorLoadPer(30);
        phevData.setBatVolt(220);
        phevData.setBatCurr(5);
        phevData.setSoc(70);
        dataUnits.add(new ReportDataUnit(phevData,reportTime));
        //6、extend data
        ExtendData extendData=new ExtendData();
        extendData.setTorqueMode(9);
        extendData.setThrottle(30);
        extendData.setTotalOilConsume(2000);
        extendData.setUreaBoxTemp(20);
        extendData.setUreaInject(100);
        extendData.setTotalUreaConsume(1000);
        extendData.setDpfOutTemp(120);
        dataUnits.add(new ReportDataUnit(extendData,reportTime));

        //数据类型集合
        reportMessage.setDataUnits(dataUnits);

        SignData signData=new SignData();
        signData.setrLength(8);
        signData.setR(new byte[]{0,1,2,3,4,5,6,7});
        signData.setsLength(16);
        signData.setS(new byte[]{0,1,2,3,4,5,6,7,8,9,0xa,0xb,0xc,0xd,0xe,0xf});
        reportMessage.setSignData(signData);

        try {
            byte[] bytes=reportMessage.encode();
            System.out.println("ReportMessage Hex="+ HexUtils.toHexString(bytes));
            System.out.println("ReportMessage json=\n"+ JsonUtil.objToJson(reportMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试解码
    public static void testDecode(){
        ReportMessage reportMessage=new ReportMessage();
        try {
            //reportMessage.decode(HexUtils.fromHexString("232302313233343536373839303132333435363701000130160a0b111e00006401160a0b111e0001010001000f76696e3132333435363738393061626364736f6674313233343536373839306162636463766e313233343536373839306162636465697570723132333435363738393061626364697570723132333435363738393061626364022323ff007e7eff1102160a0b111e0000783c321407d003e80bb803e81e07d00050009607d000460007829b810243d582000aae6003160a0b111e0000783c321407d003e803e8c807d0a004d20107829b820243d583000c350005160a0b111e0000783c321407d003e803e8c8070807d0a004d20407829b820243d583000c350004160a0b111e0007d01e00dc00054680160a0b111e00091e000007d01400000064000003e8007808000102030405060710000102030405060708090a0b0c0d0e0f35"));
            reportMessage.decode(HexUtils.fromHexString("232302334c4e364c35453937485236303230303000010130160a0b111e00000001160a0b111e0001010001000f334c4e364c354539374852363032303030736f6674313233343536373839306162636463766e313233343536373839306162636465697570723132333435363738393061626364697570723132333435363738393061626364022323ff007e7eff1102160a0b111e0000783c321407d003e80bb803e81e07d00050009607d000460007829b810243d582000aae6003160a0b111e0000783c321407d003e803e8c807d0a004d20107829b820243d583000c350005160a0b111e0000783c321407d003e803e8c8070807d0a004d20407829b820243d583000c350004160a0b111e0007d01e00dc00054680160a0b111e00091e000007d01400000064000003e8007808000102030405060710000102030405060708090a0b0c0d0e0f14"));

            System.out.println("CmdId="+reportMessage.getCmdId().getVal());
            System.out.println("Vin="+reportMessage.getVin());
            System.out.println("TermSoftwareVer="+reportMessage.getTermSoftwareVer());
            System.out.println("EncryptType="+reportMessage.getEncryptType());
            System.out.println("DataUnitLength="+reportMessage.getDataUnitLength());

            System.out.println();
            System.out.println("ReportTime.Year="+reportMessage.getReportTime().getYear());
            System.out.println("ReportTime.Month="+reportMessage.getReportTime().getMonth());
            System.out.println("ReportTime.Day="+reportMessage.getReportTime().getDay());
            System.out.println("ReportTime.Hour="+reportMessage.getReportTime().getHour());
            System.out.println("ReportTime.Minute="+reportMessage.getReportTime().getMinute());
            System.out.println("ReportTime.Second="+reportMessage.getReportTime().getSecond());
            System.out.println("Sn="+reportMessage.getSn());

            for(ReportDataUnit dataUnit: reportMessage.getDataUnits()){
                System.out.println();
                System.out.println("DataUnit.TypeId="+dataUnit.getTypeId().getVal());
                System.out.println("DataUnit.GatherTime.Year="+dataUnit.getGatherTime().getYear());
                System.out.println("DataUnit.GatherTime.Month="+dataUnit.getGatherTime().getMonth());
                System.out.println("DataUnit.GatherTime.Day="+dataUnit.getGatherTime().getDay());
                System.out.println("DataUnit.GatherTime.Hour="+dataUnit.getGatherTime().getHour());
                System.out.println("DataUnit.GatherTime.Minute="+dataUnit.getGatherTime().getMinute());
                System.out.println("DataUnit.GatherTime.Second="+dataUnit.getGatherTime().getSecond());
                GB17691DataElement dataElement=dataUnit.getElement();
                if(dataElement instanceof OBDData){
                    OBDData obdData=(OBDData) dataElement;
                    System.out.println("ObdProtocol="+obdData.getObdProtocol());
                    System.out.println("MilStatus="+obdData.getMilStatus());
                    System.out.print("DiagSptStatus=");
                    for(boolean val:obdData.getDiagSptStatus()){
                        System.out.print(val);
                        System.out.print(",");
                    }
                    System.out.println();
                    System.out.print("DiagRdyStatus=");
                    for(boolean val:obdData.getDiagRdyStatus()){
                        System.out.print(val);
                        System.out.print(",");
                    }
                    System.out.println();
                    System.out.println("vin="+obdData.getVin());
                    System.out.println("SoftVer="+obdData.getSoftVer());
                    System.out.println("cvn="+obdData.getCvn());
                    System.out.println("iupr="+obdData.getIupr());
                    System.out.println("DtcCount="+obdData.getDtcCount());
                    System.out.print("Dtcs=");
                    for(String val:obdData.getDtcs()){
                        System.out.print(val);
                        System.out.print(",");
                    }
                    System.out.println();
                }else if(dataElement instanceof DPFData){
                    DPFData dpfData=(DPFData) dataElement;
                    System.out.println("Speed="+dpfData.getSpeed());
                    System.out.println("AtmoPres="+dpfData.getAtmoPres());
                    System.out.println("Torque="+dpfData.getTorque());
                    System.out.println("FrcTorque="+dpfData.getFrcTorque());
                    System.out.println("Rpm="+dpfData.getRpm());
                    System.out.println("FuelFlow="+dpfData.getFuelFlow());
                    System.out.println("ScrUpNOx="+dpfData.getScrUpNOx());
                    System.out.println("ScrDownNOx="+dpfData.getScrDownNOx());
                    System.out.println("ReactRemain="+dpfData.getReactRemain());
                    System.out.println("AirIn="+dpfData.getAirIn());
                    System.out.println("ScrInTemp="+dpfData.getScrInTemp());
                    System.out.println("ScrOutTemp="+dpfData.getScrOutTemp());
                    System.out.println("DpfPresDiff="+dpfData.getDpfPresDiff());
                    System.out.println("FuelTankLevel="+dpfData.getFuelTankLevel());

                    System.out.println("Location.Status="+dpfData.getLocationData().getStatus());
                    System.out.println("Location.LatDir="+dpfData.getLocationData().getLatDir());
                    System.out.println("Location.LngDir="+dpfData.getLocationData().getLngDir());
                    System.out.println("Location.Lng="+dpfData.getLocationData().getLng());
                    System.out.println("Location.Lat="+dpfData.getLocationData().getLat());

                    System.out.println("Mileage="+dpfData.getMileage());
                }else if(dataElement instanceof TWCData){
                    TWCData twcData=(TWCData) dataElement;
                    System.out.println("Speed="+twcData.getSpeed());
                    System.out.println("AtmoPres="+twcData.getAtmoPres());
                    System.out.println("Torque="+twcData.getTorque());
                    System.out.println("FrcTorque="+twcData.getFrcTorque());
                    System.out.println("Rpm="+twcData.getRpm());
                    System.out.println("FuelFlow="+twcData.getFuelFlow());

                    System.out.println("TwcUpOxySensor="+twcData.getTwcUpOxySensor());
                    System.out.println("TwcDownOxySensor="+twcData.getTwcDownOxySensor());
                    System.out.println("AirIn="+twcData.getAirIn());
                    System.out.println("CoolantTemp="+twcData.getCoolantTemp());
                    System.out.println("TwcTempSensor="+twcData.getTwcTempSensor());

                    System.out.println("Location.Status="+twcData.getLocationData().getStatus());
                    System.out.println("Location.LatDir="+twcData.getLocationData().getLatDir());
                    System.out.println("Location.LngDir="+twcData.getLocationData().getLngDir());
                    System.out.println("Location.Lng="+twcData.getLocationData().getLng());
                    System.out.println("Location.Lat="+twcData.getLocationData().getLat());
                    System.out.println("Mileage="+twcData.getMileage());
                }else if(dataElement instanceof TWCNOxData){
                    TWCNOxData twcnOxData=(TWCNOxData) dataElement;
                    System.out.println("Speed="+twcnOxData.getSpeed());
                    System.out.println("AtmoPres="+twcnOxData.getAtmoPres());
                    System.out.println("Torque="+twcnOxData.getTorque());
                    System.out.println("FrcTorque="+twcnOxData.getFrcTorque());
                    System.out.println("Rpm="+twcnOxData.getRpm());
                    System.out.println("FuelFlow="+twcnOxData.getFuelFlow());
                    System.out.println("TwcUpOxySensor="+twcnOxData.getTwcUpOxySensor());
                    System.out.println("TwcDownOxySensor="+twcnOxData.getTwcDownOxySensor());
                    System.out.println("TwcDownNOxSensor="+twcnOxData.getTwcDownNOxSensor());
                    System.out.println("AirIn="+twcnOxData.getAirIn());
                    System.out.println("CoolantTemp="+twcnOxData.getCoolantTemp());
                    System.out.println("TwcTempSensor="+twcnOxData.getTwcTempSensor());
                    System.out.println("Location.Status="+twcnOxData.getLocationData().getStatus());
                    System.out.println("Location.LatDir="+twcnOxData.getLocationData().getLatDir());
                    System.out.println("Location.LngDir="+twcnOxData.getLocationData().getLngDir());
                    System.out.println("Location.Lng="+twcnOxData.getLocationData().getLng());
                    System.out.println("Location.Lat="+twcnOxData.getLocationData().getLat());
                    System.out.println("Mileage="+twcnOxData.getMileage());
                }else if(dataElement instanceof PHEVData){
                    PHEVData phevData=(PHEVData) dataElement;
                    System.out.println("MotorRpm="+phevData.getMotorRpm());
                    System.out.println("MotorLoadPer="+phevData.getMotorLoadPer());
                    System.out.println("BatVolt="+phevData.getBatVolt());
                    System.out.println("BatCurr="+phevData.getBatCurr());
                    System.out.println("Soc="+phevData.getSoc());
                }else if(dataElement instanceof ExtendData){
                    ExtendData extendData=(ExtendData) dataElement;
                    System.out.println("TorqueMode="+extendData.getTorqueMode());
                    System.out.println("Throttle="+extendData.getThrottle());
                    System.out.println("TotalOilConsume="+extendData.getTotalOilConsume());
                    System.out.println("UreaBoxTemp="+extendData.getUreaBoxTemp());
                    System.out.println("UreaInject="+extendData.getUreaInject());
                    System.out.println("TotalUreaConsume="+extendData.getTotalUreaConsume());
                    System.out.println("DpfOutTemp="+extendData.getDpfOutTemp());
                }
            }
            System.out.println();
            System.out.println("Sign.RLength="+reportMessage.getSignData().getrLength());
            System.out.println("Sign.R="+HexUtils.toHexString(reportMessage.getSignData().getR()));
            System.out.println("Sign.SLength="+reportMessage.getSignData().getsLength());
            System.out.println("Sign.S="+HexUtils.toHexString(reportMessage.getSignData().getS()));
            System.out.println();
            System.out.println("Bcc="+reportMessage.getBcc());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argus){
     // testEncode();
  testDecode();
    }
}
