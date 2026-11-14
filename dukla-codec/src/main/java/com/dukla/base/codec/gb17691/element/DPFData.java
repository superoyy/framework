package com.dukla.base.codec.gb17691.element;

import java.io.*;

/**
 * DPF / SCR 信息
 */
public class DPFData implements GB17691DataElement {

    public static final int TYPE_CODE= 0x02;

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
     * SCR上游NOx传感器输出值
     * 2 Byte
     * 精度：0.05 ppm/bit
     * 偏移量：-200 ppm
     * 数据范围：-200 ppm～3012.75 ppm
     * “0xFF，0xFF”表示无效
     */
    private int scrUpNOx;

    /**
     * SCR下游NOx传感器输出值
     * 2 Byte
     * 精度：0.05 ppm/bit
     * 偏移量：-200 ppm
     * 数据范围：-200 ppm～3012.75 ppm
     * “0xFF，0xFF”表示无效
     */
    private int scrDownNOx;

    /**
     * 反应剂余量
     * 1 Byte
     * 精度：0.4%/bit
     * 偏移量：0
     * 数据范围：0～100 %
     * “0xFF”表示无效
     */
    private int reactRemain;

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
     * SCR入口温度
     * 2 Byte
     * 精度：0.03125 deg C/bit
     * 偏移量：-273 deg C
     * 数据范围：-273 deg C～1734.96875deg C
     * “0xFF，0xFF”表示无效
     */
    private int scrInTemp;

    /**
     * SCR出口温度
     * 2 Byte
     * 精度：0.03125 deg C/bit
     * 偏移量：-273 deg C
     * 数据范围：-273 deg C～1734.96875deg C
     * “0xFF，0xFF”表示无效
     */
    private int scrOutTemp;

    /**
     * DPF压差
     * 2 Byte
     * 精度：0.1 kPa/bit
     * 偏移量：0
     * 数据范围：0～6425.5 kPa
     * “0xFF，0xFF”表示无效
     */
    private int dpfPresDiff;

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
     * 油箱液位
     * 1 Byte
     * 精度：0.4%/bit
     * 偏移量：0
     * 数据范围：0～100 %
     * “0xFF”表示无效
     */
    private int fuelTankLevel;

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

    public int getScrUpNOx() {
        return scrUpNOx;
    }

    public void setScrUpNOx(int scrUpNOx) {
        this.scrUpNOx = scrUpNOx;
    }

    public int getScrDownNOx() {
        return scrDownNOx;
    }

    public void setScrDownNOx(int scrDownNOx) {
        this.scrDownNOx = scrDownNOx;
    }

    public int getReactRemain() {
        return reactRemain;
    }

    public void setReactRemain(int reactRemain) {
        this.reactRemain = reactRemain;
    }

    public int getAirIn() {
        return airIn;
    }

    public void setAirIn(int airIn) {
        this.airIn = airIn;
    }

    public int getScrInTemp() {
        return scrInTemp;
    }

    public void setScrInTemp(int scrInTemp) {
        this.scrInTemp = scrInTemp;
    }

    public int getScrOutTemp() {
        return scrOutTemp;
    }

    public void setScrOutTemp(int scrOutTemp) {
        this.scrOutTemp = scrOutTemp;
    }

    public int getDpfPresDiff() {
        return dpfPresDiff;
    }

    public void setDpfPresDiff(int dpfPresDiff) {
        this.dpfPresDiff = dpfPresDiff;
    }

    public int getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(int coolantTemp) {
        this.coolantTemp = coolantTemp;
    }

    public int getFuelTankLevel() {
        return fuelTankLevel;
    }

    public void setFuelTankLevel(int fuelTankLevel) {
        this.fuelTankLevel = fuelTankLevel;
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

    public DPFData(){

    }

    public DPFData(DataInputStream dis) throws IOException{
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException{
        this.speed = dis.readUnsignedShort();
        this.atmoPres = dis.readUnsignedByte();
        this.torque = dis.readUnsignedByte();
        this.frcTorque = dis.readUnsignedByte();
        this.rpm = dis.readUnsignedShort();
        this.fuelFlow = dis.readUnsignedShort();
        this.scrUpNOx = dis.readUnsignedShort();
        this.scrDownNOx = dis.readUnsignedShort();
        this.reactRemain = dis.readUnsignedByte();
        this.airIn = dis.readUnsignedShort();
        this.scrInTemp = dis.readUnsignedShort();
        this.scrOutTemp = dis.readUnsignedShort();
        this.dpfPresDiff = dis.readUnsignedShort();
        this.coolantTemp = dis.readUnsignedByte();
        this.fuelTankLevel = dis.readUnsignedByte();

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
        dos.writeShort(this.scrUpNOx);
        dos.writeShort(this.scrDownNOx);
        dos.write(this.reactRemain);
        dos.writeShort(this.airIn);
        dos.writeShort(this.scrInTemp);
        dos.writeShort(this.scrOutTemp);
        dos.writeShort(this.dpfPresDiff);
        dos.write(this.coolantTemp);
        dos.write(this.fuelTankLevel);
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
        DPFData dpfData=new DPFData();

        //encode test
//        dpfData.setSpeed(120);
//        dpfData.setAtmoPres(60);
//        dpfData.setTorque(50);
//        dpfData.setFrcTorque(20);
//        dpfData.setRpm(2000);
//        dpfData.setFuelFlow(1000);
//        dpfData.setScrUpNOx(3000);
//        dpfData.setScrDownNOx(1000);
//        dpfData.setReactRemain(30);
//        dpfData.setAirIn(2000);
//        dpfData.setScrInTemp(80);
//        dpfData.setScrOutTemp(150);
//        dpfData.setDpfPresDiff(2000);
//        dpfData.setFuelTankLevel(70);
//        LocationData locationData=new LocationData();
//        locationData.setStatus(0);
//        locationData.setLngDir(0);
//        locationData.setLatDir(0);
//        locationData.setLat(38000002);
//        locationData.setLng(126000001);
//        dpfData.setLocationData(locationData);
//        dpfData.setMileage(700000L);
//
//        try {
//            byte[] bytes=dpfData.encode();
//            System.out.println("DPFData Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            dpfData.decode(HexUtils.fromHexString("00783c321407d003e80bb803e81e07d00050009607d000460007829b810243d582000aae60"));
//            System.out.println("Speed="+dpfData.getSpeed());
//            System.out.println("AtmoPres="+dpfData.getAtmoPres());
//            System.out.println("Torque="+dpfData.getTorque());
//            System.out.println("FrcTorque="+dpfData.getFrcTorque());
//            System.out.println("Rpm="+dpfData.getRpm());
//            System.out.println("FuelFlow="+dpfData.getFuelFlow());
//            System.out.println("ScrUpNOx="+dpfData.getScrUpNOx());
//            System.out.println("ScrDownNOx="+dpfData.getScrDownNOx());
//            System.out.println("ReactRemain="+dpfData.getReactRemain());
//            System.out.println("AirIn="+dpfData.getAirIn());
//            System.out.println("ScrInTemp="+dpfData.getScrInTemp());
//            System.out.println("ScrOutTemp="+dpfData.getScrOutTemp());
//            System.out.println("DpfPresDiff="+dpfData.getDpfPresDiff());
//            System.out.println("FuelTankLevel="+dpfData.getFuelTankLevel());
//
//            System.out.println("Location.Status="+dpfData.getLocationData().getStatus());
//            System.out.println("Location.LatDir="+dpfData.getLocationData().getLatDir());
//            System.out.println("Location.LngDir="+dpfData.getLocationData().getLngDir());
//            System.out.println("Location.Lng="+dpfData.getLocationData().getLng());
//            System.out.println("Location.Lat="+dpfData.getLocationData().getLat());
//
//            System.out.println("Mileage="+dpfData.getMileage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
