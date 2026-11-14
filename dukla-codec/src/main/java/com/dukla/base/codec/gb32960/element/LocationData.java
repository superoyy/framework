package com.dukla.base.codec.gb32960.element;

import com.dukla.base.codec.util.BitCodecUtils;

import java.io.*;

/**
 * 车辆位置数据
 */
public class LocationData  implements GB32960DataElement {

    // 定位状态 0 有效定位 1:无效定位
    private int state;

    //经度方向 0 东经，1 西经
    private int lngDir;

    //维度方向 0 北纬，1 南纬
    private int latDir;

    //经度 以度为单位的纬度值乘以 10 的六次方，精度精确到百万分之一度
    private long lng;

    //纬度 以度为单位的经度值乘以 10 的六次方，精度精确到百万分之一度
    private long lat;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        int prop = 0;//bit0:定位状态 bit1:维度方向 bit2:经度方向
        if(this.state == 1) prop = BitCodecUtils.setPosTo1(prop,0);
        if(this.latDir == 1) prop = BitCodecUtils.setPosTo1(prop,1);
        if(this.lngDir == 1) prop = BitCodecUtils.setPosTo1(prop,2);
        dos.writeByte(prop);
        dos.writeInt((int)this.lng);
        dos.writeInt((int)this.lat);
    }

    public static LocationData decode(byte[] bytes) throws IOException {
        return LocationData.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public static LocationData decode(DataInputStream dis) throws IOException {
        LocationData locationData=new LocationData();
        int prop = dis.readUnsignedByte();
        if(BitCodecUtils.is1AtPos(prop,0)) locationData.setState(1); else locationData.setState(0);
        if(BitCodecUtils.is1AtPos(prop,1)) locationData.setLatDir(1); else locationData.setLatDir(0);
        if(BitCodecUtils.is1AtPos(prop,2)) locationData.setLngDir(1); else locationData.setLngDir(0);
        locationData.setLng(dis.readInt());
        locationData.setLat(dis.readInt());
        return locationData;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLngDir() {
        return lngDir;
    }

    public void setLngDir(int lngDir) {
        this.lngDir = lngDir;
    }

    public int getLatDir() {
        return latDir;
    }

    public void setLatDir(int latDir) {
        this.latDir = latDir;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }
}
