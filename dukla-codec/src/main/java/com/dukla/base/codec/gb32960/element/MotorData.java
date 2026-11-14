package com.dukla.base.codec.gb32960.element;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 驱动电机数据
 */
public class MotorData implements GB32960DataElement {

    //驱动电机总成信息列表
    private List<MotorItem> motorItems;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(motorItems != null ? motorItems.size() : 0);//1 (byte)
        if(motorItems != null){
            for (MotorItem motorItem : motorItems) {
                motorItem.encode(dos);
            }
        }
    }

    public static MotorData decode(byte[] bytes) throws IOException {
        return MotorData.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public static MotorData decode(DataInputStream dis) throws IOException {
        MotorData motorData=new MotorData();
        int motorCount=dis.readUnsignedByte();
        List<MotorItem> motorItems=new ArrayList<>();
        for(int i = 0; i < motorCount; i++){
            motorItems.add(MotorItem.decode(dis));
        }
        motorData.setMotorItems(motorItems);
        return motorData;
    }

    public List<MotorItem> getMotorItems() {
        return motorItems;
    }

    public void setMotorItems(List<MotorItem> motorItems) {
        this.motorItems = motorItems;
    }

    public static class MotorItem implements GB32960DataElement{

        //驱动电机顺序号，有效值范围1～253。
        private int no;
        //驱动电机状态 0x01：耗电；0x02：发电；0x03：关闭状态；0x04：准备状态“0xFE”表示异常，“0xFF”表示无效。
        private int status ;
        //驱动电机控制器温度 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
        private int controlTemp;
        //驱动电机转速 有效值范围：0～65531（数值偏移量20000表示-2000N*m～4553.1N*m），最小计量单元：0.1N*m，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        private int speed ;
        //驱动电机转矩 有效值范围：0～65531（数值偏移量20000表示-2000N*m～4553.1N*m），最小计量单元：0.1N*m，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        private int torque ;
        //驱动电机温度 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
        private int temp;
        //电机控制器输入电压 有效值范围：0～60000（表示0V～6000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        private int voltage ;
        //电机控制器直流母线电流 有效值范围： 0～20000（数值偏移量1000A，表示-1000A～+1000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        private int current;

        public byte[] encode() throws IOException {
            ByteArrayOutputStream os=new ByteArrayOutputStream();
            this.encode(new DataOutputStream(os));
            return os.toByteArray();
        }

        @Override
        public void encode(DataOutputStream dos) throws IOException {
            dos.writeByte(this.no);//1 (byte)
            dos.writeByte(this.status);//1 (byte)
            dos.writeByte(this.controlTemp);//1 (byte)
            dos.writeShort(this.speed);//2 (byte)
            dos.writeShort(this.torque);//2 (byte)
            dos.writeByte(this.temp);//1 (byte)
            dos.writeShort(this.voltage);//2 (byte)
            dos.writeShort(this.current);//2 (byte)
        }

        public static MotorItem decode(byte[] bytes) throws IOException {
            return MotorItem.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
        }

        public static MotorItem decode(DataInputStream dis) throws IOException {
            MotorItem motorItem=new MotorItem();
            motorItem.setNo(dis.readUnsignedByte());
            motorItem.setStatus(dis.readUnsignedByte());
            motorItem.setControlTemp(dis.readUnsignedByte());
            motorItem.setSpeed(dis.readUnsignedShort());
            motorItem.setTorque(dis.readUnsignedShort());
            motorItem.setTemp(dis.readUnsignedByte());
            motorItem.setVoltage(dis.readUnsignedShort());
            motorItem.setCurrent(dis.readUnsignedShort());
            return motorItem;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getControlTemp() {
            return controlTemp;
        }

        public void setControlTemp(int controlTemp) {
            this.controlTemp = controlTemp;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getTorque() {
            return torque;
        }

        public void setTorque(int torque) {
            this.torque = torque;
        }

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
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
    }
}
