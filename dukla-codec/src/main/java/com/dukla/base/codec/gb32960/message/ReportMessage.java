package com.dukla.base.codec.gb32960.message;


import com.dukla.base.codec.gb32960.element.*;
import java.io.*;

/**
 * 实时上报和补发上报
 */
public class ReportMessage extends GB32960Message {

    private GatherTime gatherTime;//采集时间

    private VehBodyData vehBodyData;

    private MotorData motorData;

    private FuelCellData fuelCellData;

    private EngineData engineData;

    private LocationData locationData;

    private ExtremeData extremeData;

    private AlarmData alarmData;

    private VoltageData voltageData;

    private TemperatureData temperatureData;

    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        this.gatherTime.encode(dos);
        if(this.vehBodyData != null){
            dos.writeByte(0x01);
            this.vehBodyData.encode(dos);
        }
        if(this.motorData != null){
            dos.writeByte(0x02);
            this.motorData.encode(dos);
        }
        if(this.fuelCellData != null){
            dos.writeByte(0x03);
            this.fuelCellData.encode(dos);
        }
        if(this.engineData != null){
            dos.writeByte(0x04);
            this.engineData.encode(dos);
        }
        if(this.locationData != null){
            dos.writeByte(0x05);
            this.locationData.encode(dos);
        }
        if(this.extremeData != null){
            dos.writeByte(0x06);
            this.extremeData.encode(dos);
        }
        if(this.alarmData != null){
            dos.writeByte(0x07);
            this.alarmData.encode(dos);
        }
        if(this.voltageData != null){
            dos.writeByte(0x08);
            this.voltageData.encode(dos);
        }
        if(this.temperatureData != null){
            dos.writeByte(0x09);
            this.temperatureData.encode(dos);
        }
        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(DataInputStream dis) throws IOException {
        this.setGatherTime(GatherTime.decode(dis));
        while (dis.available() > 1){//直到可读大小为 1(最后一个字节为bcc码)
            int dataType = dis.readUnsignedByte();
            if(dataType == 0x01){
                this.vehBodyData = VehBodyData.decode(dis);
            }else if(dataType == 0x02){
                this.motorData = MotorData.decode(dis);
            }else if(dataType == 0x03){
                this.fuelCellData = FuelCellData.decode(dis);
            }else if(dataType == 0x04){
                this.engineData = EngineData.decode(dis);
            }else if(dataType == 0x05){
                this.locationData = LocationData.decode(dis);
            }else if(dataType == 0x06){
                this.extremeData = ExtremeData.decode(dis);
            }else if(dataType == 0x07){
                this.alarmData = AlarmData.decode(dis);
            }else if(dataType == 0x08){
                this.voltageData = VoltageData.decode(dis);
            }else if(dataType == 0x09){
                this.temperatureData = TemperatureData.decode(dis);
            }
        }
    }

    public GatherTime getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(GatherTime gatherTime) {
        this.gatherTime = gatherTime;
    }

    public VehBodyData getVehBodyData() {
        return vehBodyData;
    }

    public void setVehBodyData(VehBodyData vehBodyData) {
        this.vehBodyData = vehBodyData;
    }

    public MotorData getMotorData() {
        return motorData;
    }

    public void setMotorData(MotorData motorData) {
        this.motorData = motorData;
    }

    public FuelCellData getFuelCellData() {
        return fuelCellData;
    }

    public void setFuelCellData(FuelCellData fuelCellData) {
        this.fuelCellData = fuelCellData;
    }

    public EngineData getEngineData() {
        return engineData;
    }

    public void setEngineData(EngineData engineData) {
        this.engineData = engineData;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public ExtremeData getExtremeData() {
        return extremeData;
    }

    public void setExtremeData(ExtremeData extremeData) {
        this.extremeData = extremeData;
    }

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }

    public VoltageData getVoltageData() {
        return voltageData;
    }

    public void setVoltageData(VoltageData voltageData) {
        this.voltageData = voltageData;
    }

    public TemperatureData getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(TemperatureData temperatureData) {
        this.temperatureData = temperatureData;
    }
}
