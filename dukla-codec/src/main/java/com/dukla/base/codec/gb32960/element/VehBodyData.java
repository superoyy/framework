package com.dukla.base.codec.gb32960.element;

import com.dukla.base.codec.util.BitCodecUtils;

import java.io.*;

/**
 * 整车数据
 */
public class VehBodyData  implements GB32960DataElement {

    //车辆状态 0x01：车辆启动状态；0x02：熄火；0x03：其他状态；“0xFE”表示异常，“0xFF”表示无效。
    private int status;

    //充电状态 0x01：停车充电；0x02：行驶充电；0x03：未充电状态；0x04：充电完成；“0xFE”表示异常，“0xFF”表示无效。
    private int chargeStatus;

    //运行模式 0x01: 纯电；0x02：混动；0x03：燃油；0xFE表示异常；0xFF表示无效
    private int mode;

    //车速 有效值范围：0～2200（表示0 km/h～220 km/h），最小计量单元：0.1km/h，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int speed;

    //累计里程 有效值范围：0～9999999（表示0km～999999.9km），最小计量单元：0.1km。“0xFF, 0xFF, 0xFF,0xFE”表示异常，“0xFF,0xFF,0xFF,0xFF”表示无效。
    private long mileage ;

    //总电压 有效值范围：0～10000（表示0V～1000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int voltage;

    //总电流 有效值范围： 0～20000（偏移量1000A，表示-1000A～+1000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
    private int current;

    //剩余电量 有效值范围：0～100（表示0%～100%），最小计量单元：1%，“0xFE”表示异常，“0xFF”表示无效。
    private int soc;

    //DC-DC状态 0x01：工作；0x02：断开，“0xFE”表示异常，“0xFF”表示无效。
    private int dcStatus;

    //挡位驱动力状态(1:有驱动力 0:无驱动力)
    private int shiftPowerStatus;

    //挡位制动力状态(1:有制动力 0:无制动力)
    private int shiftBreakStatus;

    //挡位
    private int shift;

    //绝缘电阻 有效范围0~60000（表示0KΩ~60000KΩ），最小计量单元：1KΩ。
    private int resistance;

    //加速踏板行程值 有效值范围：0～100（表示0%～100%），最小计量单元：1%，“0xFE”表示异常，“0xFF”表示无效。
    private int aptv;

    //制动踏板状态 有效值范围：0～100（表示0%～100%），最小计量单元：1%，“0”表示制动关的状态；在无具体行程值情况下，用“0x65”即“101”表示制动有效状态，“0xFE”表示异常，“0xFF”表示无效。
    private int brake;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.status);//1 (byte)
        dos.writeByte(this.chargeStatus);//1 (byte)
        dos.writeByte(this.mode);//1 (byte)
        dos.writeShort(this.speed);//2 (byte)
        dos.writeInt((int) this.mileage);//4 (byte)
        dos.writeShort(this.voltage);//2 (byte)
        dos.writeShort(this.current);//2 (byte)
        dos.writeByte(this.soc);//1 (byte)
        dos.writeByte(this.dcStatus);//1 (byte)
        // 档位数据
        int shiftData = this.shift & 0x0f;
        if (this.shiftBreakStatus == 1) shiftData = BitCodecUtils.setPosTo1(shiftData,4);
        if (this.shiftPowerStatus == 1) shiftData = BitCodecUtils.setPosTo1(shiftData,5);
        dos.writeByte(shiftData);//1 (byte)

        dos.writeShort(this.resistance);//2 (byte)
        dos.writeByte(this.aptv);//1 (byte)
        dos.writeByte(this.brake);//1 (byte)
    }

    public static VehBodyData decode(byte[] bytes) throws IOException {
        return VehBodyData.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public static VehBodyData decode(DataInputStream dis) throws IOException {
        VehBodyData vehBodyData=new VehBodyData();

        vehBodyData.setStatus(dis.readUnsignedByte());
        vehBodyData.setChargeStatus(dis.readUnsignedByte());
        vehBodyData.setMode(dis.readUnsignedByte());
        vehBodyData.setSpeed(dis.readUnsignedShort());
        vehBodyData.setMileage(dis.readInt());
        vehBodyData.setVoltage(dis.readUnsignedShort());
        vehBodyData.setCurrent(dis.readUnsignedShort());
        vehBodyData.setSoc(dis.readUnsignedByte());
        vehBodyData.setDcStatus(dis.readUnsignedByte());

        // 档位数据 1 byte
        // bit0-bit3: 0000 空挡 0001 1档 ... 1101 倒挡 1110 D档 1111 P档
        // bit4 1 有制动力 0 无制动力
        // bit5 1 有驱动力 0 无驱动力
        int shiftData=dis.readUnsignedByte();
        vehBodyData.setShift(shiftData & 0x0f);
        vehBodyData.setShiftBreakStatus(BitCodecUtils.is1AtPos(shiftData,4) ? 1 : 0);
        vehBodyData.setShiftPowerStatus(BitCodecUtils.is1AtPos(shiftData,5) ? 1 : 0);

        vehBodyData.setResistance(dis.readUnsignedShort());
        vehBodyData.setAptv(dis.readUnsignedByte());
        vehBodyData.setBrake(dis.readUnsignedByte());

        return vehBodyData;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(int chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
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

    public int getSoc() {
        return soc;
    }

    public void setSoc(int soc) {
        this.soc = soc;
    }

    public int getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(int dcStatus) {
        this.dcStatus = dcStatus;
    }

    public int getShiftPowerStatus() {
        return shiftPowerStatus;
    }

    public void setShiftPowerStatus(int shiftPowerStatus) {
        this.shiftPowerStatus = shiftPowerStatus;
    }

    public int getShiftBreakStatus() {
        return shiftBreakStatus;
    }

    public void setShiftBreakStatus(int shiftBreakStatus) {
        this.shiftBreakStatus = shiftBreakStatus;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getAptv() {
        return aptv;
    }

    public void setAptv(int aptv) {
        this.aptv = aptv;
    }

    public int getBrake() {
        return brake;
    }

    public void setBrake(int brake) {
        this.brake = brake;
    }
}
