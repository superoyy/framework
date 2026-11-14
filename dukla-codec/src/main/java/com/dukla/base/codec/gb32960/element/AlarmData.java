package com.dukla.base.codec.gb32960.element;


import com.dukla.base.codec.util.BitCodecUtils;

import java.io.*;

/**
 * 告警数据
 */
public class AlarmData  implements GB32960DataElement {

    //最高报警等级 有效值范围：0～3，“0”表示无故障；“1”表示1级故障，“2”表示2级故障，“3”表示3级故障，具体等级对应的故障内容由厂商自行定义；“0xFE”表示异常，“0xFF”表示无效。
    private int maxLevel;
    //通用告警标志
    private UniAlarmFlag uas;
    //可充电储能装置故障总数 可充电储能装置故障，有效值范围：0～252，“0xFE”表示异常，“0xFF”表示无效。
    private CustomFault ressList;
    //驱动电机故障
    private CustomFault motorList;
    //发动机故障
    private CustomFault engineList;
    //其他故障
    private CustomFault otherList;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.maxLevel);//1 (byte)
        this.uas.encode(dos);
        this.ressList.encode(dos);
        this.motorList.encode(dos);
        this.engineList.encode(dos);
        this.otherList.encode(dos);
    }

    public static AlarmData decode(final DataInputStream dis) throws IOException {
        AlarmData alarmData=new AlarmData();
        alarmData.setMaxLevel(dis.readUnsignedByte());
        alarmData.setUas(UniAlarmFlag.decode(dis));
        alarmData.setRessList(CustomFault.decode(dis));
        alarmData.setMotorList(CustomFault.decode(dis));
        alarmData.setEngineList(CustomFault.decode(dis));
        alarmData.setOtherList(CustomFault.decode(dis));
        return alarmData;
    }

    public static GB32960DataElement decode(final byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        return AlarmData.decode(dis);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public UniAlarmFlag getUas() {
        return uas;
    }

    public void setUas(UniAlarmFlag uas) {
        this.uas = uas;
    }

    public CustomFault getRessList() {
        return ressList;
    }

    public void setRessList(CustomFault ressList) {
        this.ressList = ressList;
    }

    public CustomFault getMotorList() {
        return motorList;
    }

    public void setMotorList(CustomFault motorList) {
        this.motorList = motorList;
    }

    public CustomFault getEngineList() {
        return engineList;
    }

    public void setEngineList(CustomFault engineList) {
        this.engineList = engineList;
    }

    public CustomFault getOtherList() {
        return otherList;
    }

    public void setOtherList(CustomFault otherList) {
        this.otherList = otherList;
    }

    /**
     * 通用告警标志类 4字节以位标识
     */
    public static class UniAlarmFlag implements GB32960DataElement{

        private boolean ressChargeOver = false;
        private boolean motorTemp = false;
        private boolean highVolMuteStatus = false;
        private boolean motorControlTemp = false;
        private boolean dcdcStatus = false;
        private boolean brake = false;
        private boolean dcdcTemp = false;
        private boolean insulation = false;
        private boolean batteryBadConsistency = false;
        private boolean ressNotMatch = false;
        private boolean socJump = false;
        private boolean socOver = false;
        private boolean batteryLow = false;
        private boolean batteryOver = false;
        private boolean socLow = false;
        private boolean ressVolLow = false;
        private boolean ressVolOver = false;
        private boolean batteryTempOver = false;
        private boolean tempDiff = false;

        @Override
        public byte[] encode() throws IOException {
            byte[] bytes=new byte[4];
            int val = 0;

            val = this.tempDiff ? BitCodecUtils.setPosTo1(val,0) : BitCodecUtils.setPosTo0(val,0);
            val = this.batteryTempOver ? BitCodecUtils.setPosTo1(val,1) : BitCodecUtils.setPosTo0(val,1);
            val = this.ressVolOver ? BitCodecUtils.setPosTo1(val,2) : BitCodecUtils.setPosTo0(val,2);
            val = this.ressVolLow ? BitCodecUtils.setPosTo1(val,3) : BitCodecUtils.setPosTo0(val,3);
            val = this.socLow ? BitCodecUtils.setPosTo1(val,4) : BitCodecUtils.setPosTo0(val,4);
            val = this.batteryOver ? BitCodecUtils.setPosTo1(val,5) : BitCodecUtils.setPosTo0(val,5);
            val = this.batteryLow ? BitCodecUtils.setPosTo1(val,6) : BitCodecUtils.setPosTo0(val,6);
            val = this.socOver ? BitCodecUtils.setPosTo1(val,7) : BitCodecUtils.setPosTo0(val,7);
            val = this.socJump ? BitCodecUtils.setPosTo1(val,8) : BitCodecUtils.setPosTo0(val,8);
            val = this.ressNotMatch ? BitCodecUtils.setPosTo1(val,9) : BitCodecUtils.setPosTo0(val,9);
            val = this.batteryBadConsistency ? BitCodecUtils.setPosTo1(val,10) : BitCodecUtils.setPosTo0(val,10);
            val = this.insulation ? BitCodecUtils.setPosTo1(val,11) : BitCodecUtils.setPosTo0(val,11);
            val = this.dcdcTemp ? BitCodecUtils.setPosTo1(val,12) : BitCodecUtils.setPosTo0(val,12);
            val = this.brake ? BitCodecUtils.setPosTo1(val,13) : BitCodecUtils.setPosTo0(val,13);
            val = this.dcdcStatus ? BitCodecUtils.setPosTo1(val,14) : BitCodecUtils.setPosTo0(val,14);
            val = this.motorControlTemp ? BitCodecUtils.setPosTo1(val,15) : BitCodecUtils.setPosTo0(val,15);
            val = this.highVolMuteStatus ? BitCodecUtils.setPosTo1(val,16) : BitCodecUtils.setPosTo0(val,16);
            val = this.motorTemp ? BitCodecUtils.setPosTo1(val,17) : BitCodecUtils.setPosTo0(val,17);
            val = this.ressChargeOver ? BitCodecUtils.setPosTo1(val,18) : BitCodecUtils.setPosTo0(val,18);

            bytes[0] = (byte) (val & 0xff);
            bytes[1] = (byte) (val >> 8 & 0xff);
            bytes[2] = (byte) (val >> 16 & 0xff);
            bytes[3] = (byte) (val >> 24 & 0xff);
            return bytes;
        }

        @Override
        public void encode(final DataOutputStream dos) throws IOException {
            dos.write(this.encode());
        }

        public static UniAlarmFlag decode(final DataInputStream dis) throws IOException {
            UniAlarmFlag uniAlarmFlag=new UniAlarmFlag();
            int val = dis.readInt();
            uniAlarmFlag.setTempDiff(BitCodecUtils.is1AtPos(val,0));
            uniAlarmFlag.setBatteryTempOver(BitCodecUtils.is1AtPos(val,1));
            uniAlarmFlag.setRessVolOver(BitCodecUtils.is1AtPos(val,2));
            uniAlarmFlag.setRessVolLow(BitCodecUtils.is1AtPos(val,3));
            uniAlarmFlag.setSocLow(BitCodecUtils.is1AtPos(val,4));
            uniAlarmFlag.setBatteryOver(BitCodecUtils.is1AtPos(val,5));
            uniAlarmFlag.setBatteryLow(BitCodecUtils.is1AtPos(val,6));
            uniAlarmFlag.setSocOver(BitCodecUtils.is1AtPos(val,7));
            uniAlarmFlag.setSocJump(BitCodecUtils.is1AtPos(val,8));
            uniAlarmFlag.setRessNotMatch(BitCodecUtils.is1AtPos(val,9));
            uniAlarmFlag.setBatteryBadConsistency(BitCodecUtils.is1AtPos(val,10));
            uniAlarmFlag.setInsulation(BitCodecUtils.is1AtPos(val,11));
            uniAlarmFlag.setDcdcTemp(BitCodecUtils.is1AtPos(val,12));
            uniAlarmFlag.setBrake(BitCodecUtils.is1AtPos(val,13));
            uniAlarmFlag.setDcdcStatus(BitCodecUtils.is1AtPos(val,14));
            uniAlarmFlag.setMotorControlTemp(BitCodecUtils.is1AtPos(val,15));
            uniAlarmFlag.setHighVolMuteStatus(BitCodecUtils.is1AtPos(val,16));
            uniAlarmFlag.setMotorTemp(BitCodecUtils.is1AtPos(val,17));
            uniAlarmFlag.setRessChargeOver(BitCodecUtils.is1AtPos(val,18));
            return uniAlarmFlag;
        }

        public UniAlarmFlag decode(final byte[] bytes) throws IOException {
            ByteArrayInputStream is=new ByteArrayInputStream(bytes);
            DataInputStream dis=new DataInputStream(is);
            return UniAlarmFlag.decode(dis);
        }

        public boolean isRessChargeOver() {
            return ressChargeOver;
        }

        public void setRessChargeOver(boolean ressChargeOver) {
            this.ressChargeOver = ressChargeOver;
        }

        public boolean isMotorTemp() {
            return motorTemp;
        }

        public void setMotorTemp(boolean motorTemp) {
            this.motorTemp = motorTemp;
        }

        public boolean isHighVolMuteStatus() {
            return highVolMuteStatus;
        }

        public void setHighVolMuteStatus(boolean highVolMuteStatus) {
            this.highVolMuteStatus = highVolMuteStatus;
        }

        public boolean isMotorControlTemp() {
            return motorControlTemp;
        }

        public void setMotorControlTemp(boolean motorControlTemp) {
            this.motorControlTemp = motorControlTemp;
        }

        public boolean isDcdcStatus() {
            return dcdcStatus;
        }

        public void setDcdcStatus(boolean dcdcStatus) {
            this.dcdcStatus = dcdcStatus;
        }

        public boolean isBrake() {
            return brake;
        }

        public void setBrake(boolean brake) {
            this.brake = brake;
        }

        public boolean isDcdcTemp() {
            return dcdcTemp;
        }

        public void setDcdcTemp(boolean dcdcTemp) {
            this.dcdcTemp = dcdcTemp;
        }

        public boolean isInsulation() {
            return insulation;
        }

        public void setInsulation(boolean insulation) {
            this.insulation = insulation;
        }

        public boolean isBatteryBadConsistency() {
            return batteryBadConsistency;
        }

        public void setBatteryBadConsistency(boolean batteryBadConsistency) {
            this.batteryBadConsistency = batteryBadConsistency;
        }

        public boolean isRessNotMatch() {
            return ressNotMatch;
        }

        public void setRessNotMatch(boolean ressNotMatch) {
            this.ressNotMatch = ressNotMatch;
        }

        public boolean isSocJump() {
            return socJump;
        }

        public void setSocJump(boolean socJump) {
            this.socJump = socJump;
        }

        public boolean isSocOver() {
            return socOver;
        }

        public void setSocOver(boolean socOver) {
            this.socOver = socOver;
        }

        public boolean isBatteryLow() {
            return batteryLow;
        }

        public void setBatteryLow(boolean batteryLow) {
            this.batteryLow = batteryLow;
        }

        public boolean isBatteryOver() {
            return batteryOver;
        }

        public void setBatteryOver(boolean batteryOver) {
            this.batteryOver = batteryOver;
        }

        public boolean isSocLow() {
            return socLow;
        }

        public void setSocLow(boolean socLow) {
            this.socLow = socLow;
        }

        public boolean isRessVolLow() {
            return ressVolLow;
        }

        public void setRessVolLow(boolean ressVolLow) {
            this.ressVolLow = ressVolLow;
        }

        public boolean isRessVolOver() {
            return ressVolOver;
        }

        public void setRessVolOver(boolean ressVolOver) {
            this.ressVolOver = ressVolOver;
        }

        public boolean isBatteryTempOver() {
            return batteryTempOver;
        }

        public void setBatteryTempOver(boolean batteryTempOver) {
            this.batteryTempOver = batteryTempOver;
        }

        public boolean isTempDiff() {
            return tempDiff;
        }

        public void setTempDiff(boolean tempDiff) {
            this.tempDiff = tempDiff;
        }
    }

    /**
     * 故障列表类
     */
    public static class CustomFault implements GB32960DataElement{

        private int[] dtcs;

        public int[] getDtcs() {
            return dtcs;
        }

        public void setDtcs(int[] dtcs) {
            this.dtcs = dtcs;
        }

        @Override
        public byte[] encode() throws IOException {
            byte[] bytes=new byte[this.dtcs.length * 4 + 1];
            bytes[0] = (byte) (this.dtcs.length & 0xff);
            for(int i=0;i<this.dtcs.length;i++){
                bytes[i*4+1]=(byte) (this.dtcs[i] & 0xff);
                bytes[i*4+2]=(byte) (this.dtcs[i] >> 8 & 0xff);
                bytes[i*4+3]=(byte) (this.dtcs[i] >> 16 & 0xff);
                bytes[i*4+4]=(byte) (this.dtcs[i] >> 24 & 0xff);
            }
            return bytes;
        }

        @Override
        public void encode(final DataOutputStream dos) throws IOException {
            dos.write(this.encode());
        }

        public static CustomFault decode(final DataInputStream dis) throws IOException {
            CustomFault customFault=new CustomFault();
            int num = dis.readUnsignedByte();
            if(num <= 252){
                int[] dtcs = new int[num];
                for(int i=0;i<num;i++){
                    dtcs[i]=dis.readInt();
                }
                customFault.setDtcs(dtcs);
            }
            return customFault;
        }

        public static CustomFault decode(final byte[] bytes) throws IOException {
            ByteArrayInputStream is=new ByteArrayInputStream(bytes);
            DataInputStream dis=new DataInputStream(is);
            return CustomFault.decode(dis);
        }
    }
}
