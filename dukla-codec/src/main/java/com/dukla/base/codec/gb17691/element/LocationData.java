package com.dukla.base.codec.gb17691.element;

import com.dukla.base.codec.util.BitCodecUtils;

import java.io.*;

/**
 * 位置数据
 */
public class LocationData {

    /**
     * 状态
     * 0:有效定位；1:无效定位
     */
    private int status;

    /**
     * 经度方向
     * 0:东经；1:西经
     */
    private int lngDir;

    /**
     * 纬度方向
     * 0:北纬；1:南纬
     */
    private int latDir;


    /**
     * 经度
     * 4 Byte
     * 精度：0.000001°/bit
     * 偏移量：0
     * 数据范围：0～180.000000°
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long lng;

    /**
     * 纬度
     * 4 Byte
     * 精度：0.000001°/bit
     * 偏移量：0
     * 数据范围：0～90.000000°
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long lat;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public LocationData(){
    }

    public LocationData(byte[] bytes) throws IOException {
        this.decode(bytes);
    }

    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);

        int locStatus = 0;
        if(this.status==0){
            locStatus = BitCodecUtils.setPosTo0(locStatus,0);
        }else{
            locStatus = BitCodecUtils.setPosTo1(locStatus,0);
        }
        if(this.latDir==0){
            locStatus = BitCodecUtils.setPosTo0(locStatus,1);
        }else{
            locStatus = BitCodecUtils.setPosTo1(locStatus,1);
        }
        if(this.lngDir==0){
            locStatus = BitCodecUtils.setPosTo0(locStatus,2);
        }else{
            locStatus = BitCodecUtils.setPosTo1(locStatus,2);
        }
        dos.write(locStatus);

        dos.writeInt((int) this.lng);
        dos.writeInt((int) this.lat);
        return os.toByteArray();
    }

    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);

        int locStatus = dis.readUnsignedByte();
        this.status = locStatus & 0x01;
        this.latDir = (locStatus >> 1) & 0x01;
        this.lngDir = (locStatus >> 2) & 0x01;

        this.lng = dis.readInt();
        this.lat = dis.readInt();
    }

    public static void main(String[] argus){
        LocationData locationData=new LocationData();

        //encode test
//        locationData.setStatus(0);
//        locationData.setLatDir(0);
//        locationData.setLngDir(0);
//        locationData.setLng(179000001L);
//        locationData.setLat(89000001);
//        try {
//            byte[] bytes=locationData.encode();
//            System.out.println("Location Data Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            locationData.decode(HexUtils.fromHexString("0007829b810243d582"));
//            System.out.println("Status="+locationData.getStatus());
//            System.out.println("LatDir="+locationData.getLatDir());
//            System.out.println("LngDir="+locationData.getLngDir());
//            System.out.println("Lng="+locationData.getLng());
//            System.out.println("Lat="+locationData.getLat());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
