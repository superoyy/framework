package com.dukla.base.codec.gb32960.element;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 电压数据
 */
public class VoltageData  implements GB32960DataElement {


    //可充电储能装置温度列表
    private List<VoltageItem> voltageItems;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.voltageItems!=null ? this.voltageItems.size() : 0);//1 (byte)
        if(this.voltageItems!=null){
            for(VoltageItem voltageItem:this.voltageItems){
                voltageItem.encode(dos);
            }
        }
    }

    public static VoltageData decode(DataInputStream dis) throws IOException {
        VoltageData voltageData=new VoltageData();

        List<VoltageItem> voltageItems=new ArrayList<>();
        int subCount = dis.readUnsignedByte(); //可充电储能子系统个数 有效值范围1-250
        if(subCount >= 1 && subCount <= 250){
            for(int i = 0;i < subCount; i++){
                voltageItems.add(VoltageItem.decode(dis));
            }
        }
        voltageData.setVoltageItems(voltageItems);

        return voltageData;
    }

    public static VoltageData decode(byte[] bytes) throws IOException {
        return VoltageData.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public List<VoltageItem> getVoltageItems() {
        return voltageItems;
    }

    public void setVoltageItems(List<VoltageItem> voltageItems) {
        this.voltageItems = voltageItems;
    }

    public static class VoltageItem implements GB32960DataElement{

        //可充电储能子系统编号，有效值范围：1～250。
        private int no ;
        //可充电储能装置电压 有效值范围：0～10000（表示0V～1000V），最小计量单元：0.1V，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
        private int voltage ;
        //可充电储能装置电流 有效值范围： 0～20000（数值偏移量1000A，表示-1000A～+1000A），最小计量单元：0.1A，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
        private int current;
        //单体电池总数 N个电池单体，有效值范围：1～65531，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
        private int batteryCount ;
        //本帧起始电池序号 当本帧单体个数超过200时，应拆分成多帧数据进行传输，有效值范围：1～65531。
        private int frameStartBatteryNo ;
        //帧单体总数 m;有效值范围：1～200。
        private List<Integer> batteryVols ;

        @Override
        public byte[] encode() throws IOException {
            ByteArrayOutputStream os=new ByteArrayOutputStream();
            this.encode(new DataOutputStream(os));
            return os.toByteArray();
        }

        @Override
        public void encode(DataOutputStream dos) throws IOException {
            dos.writeByte(this.no);//1 (byte)
            dos.writeShort(this.voltage);
            dos.writeShort(this.current);
            dos.writeShort(this.batteryCount);
            dos.writeShort(this.frameStartBatteryNo);
            dos.writeByte(this.batteryVols!=null ? this.batteryVols.size() : 0);
            if(this.batteryVols != null){
                for (Integer batteryVol : this.batteryVols) {
                    dos.writeShort(batteryVol);
                }
            }
        }

        public static VoltageItem decode(DataInputStream dis) throws IOException {
            VoltageItem voltageItem=new VoltageItem();

            voltageItem.setNo(dis.readUnsignedByte());
            voltageItem.setVoltage(dis.readUnsignedShort());
            voltageItem.setCurrent(dis.readUnsignedShort());
            voltageItem.setBatteryCount(dis.readUnsignedShort());
            voltageItem.setFrameStartBatteryNo(dis.readUnsignedShort());

            int frameBatteryCount = dis.readUnsignedByte();//本帧单体总数 m;有效值范围：1～200
            List<Integer> batteryVols=new ArrayList<>();
            if(frameBatteryCount >= 1 && frameBatteryCount <= 200){
                for(int i = 0; i < frameBatteryCount; i++){
                    batteryVols.add(dis.readUnsignedShort());
                }
            }
            voltageItem.setBatteryVols(batteryVols);

            return voltageItem;
        }

        public static VoltageItem decode(byte[] bytes) throws IOException {
            return VoltageItem.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
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

        public int getBatteryCount() {
            return batteryCount;
        }

        public void setBatteryCount(int batteryCount) {
            this.batteryCount = batteryCount;
        }

        public int getFrameStartBatteryNo() {
            return frameStartBatteryNo;
        }

        public void setFrameStartBatteryNo(int frameStartBatteryNo) {
            this.frameStartBatteryNo = frameStartBatteryNo;
        }

        public List<Integer> getBatteryVols() {
            return batteryVols;
        }

        public void setBatteryVols(List<Integer> batteryVols) {
            this.batteryVols = batteryVols;
        }
    }
}
