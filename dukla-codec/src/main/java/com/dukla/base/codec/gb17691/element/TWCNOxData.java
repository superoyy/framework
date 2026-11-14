package com.dukla.base.codec.gb17691.element;

import java.io.*;

/**
 * 有 NOx 传感器 三元催化器(TWC) 信息
 */
public class TWCNOxData implements GB17691DataElement {

    public static final int TYPE_CODE= 0x05;

    /**
     * 车速
     * 2 Byte
     * 精度：1/256 km/h per bit
     * 偏移量：0
     * 数据范围：0～250.996 km/h
     * “0xFF，0xFF”表示无效
     */
    private int speed;

    /**
     * 大气压力（直接测量或估计值）
     * 1 Byte
     * 精度：0.5 kPa/bit
     * 偏移量：0
     * 数据范围：0～125 kPa
     * “0xFF”表示无效
     */
    private int atmoPres;

    /**
     * 发动机净输出扭矩
     * 1 Byte
     * 精度：1%/bit
     * 偏移量：-125%
     * 数据范围：-125%～125%
     * “0xFF”表示无效
     */
    private int torque;

    /**
     * 摩擦扭矩
     * 1 Byte
     * 精度：1%/bit
     * 偏移量：-125%
     * 数据范围：-125%～125%
     * “0xFF”表示无效
     */
    private int frcTorque;

    /**
     * 发动机转速
     * 2 Byte
     * 精度：0.125 rpm/bit
     * 偏移量：0
     * 数据范围：0～8031.875 rpm
     * “0xFF，0xFF”表示无效
     */
    private int rpm;

    /**
     * 发动机燃料流量
     * 2 Byte
     * 精度：0.05 L/h per bit
     * 偏移量：0
     * 数据范围：0～3212.75 L/h
     * “0xFF，0xFF”表示无效
     */
    private int fuelFlow;

    /**
     * 三元催化器上游氧传感器输出值
     * 2 Byte
     * 精度：0.0000305/bit
     * 偏移量：0
     * 数据范围：0～1.999
     * “0xFF，0xFF”表示无效
     */
    private int twcUpOxySensor;

    /**
     * 三元催化器下游氧传感器输出值
     * 1 Byte
     * 精度：0.01 V/bit
     * 偏移量：0
     * 数据范围：0～2.550V
     * “0xFF”表示无效
     */
    private int twcDownOxySensor;

    /**
     * 三元催化器下游NOx传感器输出值
     * 2 Byte
     * 精度：0.05 ppm/bit
     * 偏移量：-200 ppm
     * 数据范围：-200 ppm～3012.75 ppm
     * “0xFF，0xFF”表示无效
     */
    private int twcDownNOxSensor;

    /**
     * 进气量
     * 2 Byte
     * 精度：0.05 kg/h per bit
     * 偏移量：0
     * 数据范围：0～3212.75 kg/h
     * “0xFF，0xFF”表示无效
     */
    private int airIn;

    /**
     * 发动机冷却液温度
     * 1 Byte
     * 精度：1deg C/bit
     * 偏移量：-40 deg C
     * 数据范围：-40 deg C～210 deg C
     * “0xFF”表示无效
     */
    private int coolantTemp;

    /**
     * 三元催化器温度传感器输出（上游、或下游、或模拟）
     * 2 Byte
     * 精度：0.03125 deg C/bit
     * 偏移量：-273 deg C
     * 数据范围：-273 deg C～1734.96875 deg C
     * “0xFF，0xFF”表示无效
     */
    private int twcTempSensor;

    /**
     * 定位数据
     */
    private LocationData locationData;

    /**
     * 累计里程
     * 4 Byte
     * 精度：0.1km/bit
     * 偏移量：0
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long mileage;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAtmoPres() {
        return atmoPres;
    }

    public void setAtmoPres(int atmoPres) {
        this.atmoPres = atmoPres;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public int getFrcTorque() {
        return frcTorque;
    }

    public void setFrcTorque(int frcTorque) {
        this.frcTorque = frcTorque;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getFuelFlow() {
        return fuelFlow;
    }

    public void setFuelFlow(int fuelFlow) {
        this.fuelFlow = fuelFlow;
    }

    public int getTwcUpOxySensor() {
        return twcUpOxySensor;
    }

    public void setTwcUpOxySensor(int twcUpOxySensor) {
        this.twcUpOxySensor = twcUpOxySensor;
    }

    public int getTwcDownOxySensor() {
        return twcDownOxySensor;
    }

    public void setTwcDownOxySensor(int twcDownOxySensor) {
        this.twcDownOxySensor = twcDownOxySensor;
    }

    public int getTwcDownNOxSensor() {
        return twcDownNOxSensor;
    }

    public void setTwcDownNOxSensor(int twcDownNOxSensor) {
        this.twcDownNOxSensor = twcDownNOxSensor;
    }

    public int getAirIn() {
        return airIn;
    }

    public void setAirIn(int airIn) {
        this.airIn = airIn;
    }

    public int getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(int coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public int getTwcTempSensor() {
        return twcTempSensor;
    }

    public void setTwcTempSensor(int twcTempSensor) {
        this.twcTempSensor = twcTempSensor;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public TWCNOxData(){

    }

    public TWCNOxData(DataInputStream dis) throws IOException {
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException {
        this.speed = dis.readUnsignedShort();
        this.atmoPres = dis.readUnsignedByte();
        this.torque = dis.readUnsignedByte();
        this.frcTorque = dis.readUnsignedByte();
        this.rpm = dis.readUnsignedShort();
        this.fuelFlow = dis.readUnsignedShort();
        this.twcUpOxySensor = dis.readUnsignedShort();
        this.twcDownOxySensor = dis.readUnsignedByte();
        this.twcDownNOxSensor = dis.readUnsignedShort();
        this.airIn = dis.readUnsignedShort();
        this.coolantTemp = dis.readUnsignedByte();
        this.twcTempSensor = dis.readUnsignedShort();

        byte[] b=new byte[9];
        dis.readFully(b);
        this.locationData = new LocationData(b);

        this.mileage = dis.readInt();
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.writeShort(this.speed);
        dos.write(this.atmoPres);
        dos.write(this.torque);
        dos.write(this.frcTorque);
        dos.writeShort(this.rpm);
        dos.writeShort(this.fuelFlow);
        dos.writeShort(this.twcUpOxySensor);
        dos.write(this.twcDownOxySensor);
        dos.writeShort(this.twcDownNOxSensor);
        dos.writeShort(this.airIn);
        dos.write(this.coolantTemp);
        dos.writeShort(this.twcTempSensor);
        dos.write(this.locationData.encode());
        dos.writeInt((int)this.mileage);
        return os.toByteArray();
    }

    @Override
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        this.build(dis);
    }

    public static void main(String[] argus){
        TWCNOxData twcnOxData=new TWCNOxData();

        //encode test
//        twcnOxData.setSpeed(120);
//        twcnOxData.setAtmoPres(60);
//        twcnOxData.setTorque(50);
//        twcnOxData.setFrcTorque(20);
//        twcnOxData.setRpm(2000);
//        twcnOxData.setFuelFlow(1000);
//        twcnOxData.setTwcUpOxySensor(1000);
//        twcnOxData.setTwcDownOxySensor(200);
//        twcnOxData.setTwcDownNOxSensor(1800);
//        twcnOxData.setAirIn(2000);
//        twcnOxData.setCoolantTemp(160);
//        twcnOxData.setTwcTempSensor(1234);
//        LocationData locationData=new LocationData();
//        locationData.setStatus(0);
//        locationData.setLngDir(0);
//        locationData.setLatDir(0);
//        locationData.setLat(38000003);
//        locationData.setLng(126000002);
//        twcnOxData.setLocationData(locationData);
//        twcnOxData.setMileage(800000L);
//        try {
//            byte[] bytes=twcnOxData.encode();
//            System.out.println("TWCNOxData Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            twcnOxData.decode(HexUtils.fromHexString("00783c321407d003e803e8c8070807d0a004d20007829b820243d583000c3500"));
//            System.out.println("Speed="+twcnOxData.getSpeed());
//            System.out.println("AtmoPres="+twcnOxData.getAtmoPres());
//            System.out.println("Torque="+twcnOxData.getTorque());
//            System.out.println("FrcTorque="+twcnOxData.getFrcTorque());
//            System.out.println("Rpm="+twcnOxData.getRpm());
//            System.out.println("FuelFlow="+twcnOxData.getFuelFlow());
//            System.out.println("TwcUpOxySensor="+twcnOxData.getTwcUpOxySensor());
//            System.out.println("TwcDownOxySensor="+twcnOxData.getTwcDownOxySensor());
//            System.out.println("TwcDownNOxSensor="+twcnOxData.getTwcDownNOxSensor());
//            System.out.println("AirIn="+twcnOxData.getAirIn());
//            System.out.println("CoolantTemp="+twcnOxData.getCoolantTemp());
//            System.out.println("TwcTempSensor="+twcnOxData.getTwcTempSensor());
//            System.out.println("Location.Status="+twcnOxData.getLocationData().getStatus());
//            System.out.println("Location.LatDir="+twcnOxData.getLocationData().getLatDir());
//            System.out.println("Location.LngDir="+twcnOxData.getLocationData().getLngDir());
//            System.out.println("Location.Lng="+twcnOxData.getLocationData().getLng());
//            System.out.println("Location.Lat="+twcnOxData.getLocationData().getLat());
//            System.out.println("Mileage="+twcnOxData.getMileage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
