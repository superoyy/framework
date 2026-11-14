package com.dukla.base.codec.gb32960.element;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 温度数据
 */
public class TemperatureData  implements GB32960DataElement {


    //可充电储能装置温度列表
    private List<TemperatureItem> temperatureItems;

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        if(this.temperatureItems != null){
            dos.writeByte(temperatureItems.size());
            for (TemperatureItem temperatureItem : this.temperatureItems) {
                temperatureItem.encode(dos);
            }
        }else{
            dos.writeByte(0);
        }
    }


    public static TemperatureData decode(byte[] bytes) throws IOException {
        return TemperatureData.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public static TemperatureData decode(DataInputStream dis) throws IOException {
        TemperatureData temperatureData=new TemperatureData();
        int tempItemCount = dis.readUnsignedByte();
        List<TemperatureItem> items=new ArrayList<>();
        if(tempItemCount>=1 && tempItemCount<=250){
            for(int i=0;i<tempItemCount;i++){
                items.add(TemperatureItem.decode(dis));
            }
        }
        temperatureData.setTemperatureItems(items);
        return temperatureData;
    }

    public List<TemperatureItem> getTemperatureItems() {
        return temperatureItems;
    }

    public void setTemperatureItems(List<TemperatureItem> temperatureItems) {
        this.temperatureItems = temperatureItems;
    }

    public static class TemperatureItem implements GB32960DataElement{

        //可充电储能子系统编号，有效值范围：1～250。
        private int no ;
        //温度 有效值范围：0～250 （数值偏移量40℃，表示-40℃～+210℃），最小计量单元：1℃，“0xFE”表示异常，“0xFF”表示无效。
        private List<Integer> temps;

        @Override
        public byte[] encode() throws IOException {
            ByteArrayOutputStream os=new ByteArrayOutputStream();
            this.encode(new DataOutputStream(os));
            return os.toByteArray();
        }

        @Override
        public void encode(DataOutputStream dos) throws IOException {
            dos.writeByte(this.no);
            if(this.temps != null){
                dos.writeShort(this.temps.size());
                for (Integer temp : this.temps) {
                    dos.writeByte(temp);
                }
            }else{
                dos.writeShort(0);
            }
        }

        public static TemperatureItem decode(byte[] bytes) throws IOException {
            return TemperatureItem.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
        }

        public static TemperatureItem decode(DataInputStream dis) throws IOException {
            TemperatureItem temperatureItem=new TemperatureItem();
            temperatureItem.setNo(dis.readUnsignedByte());
            int probCount=dis.readUnsignedShort();//可充电储能温度探针个数 N个温度探针，有效值范围：1～65531，“0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效。
            List<Integer> temps=new ArrayList<>();
            if(probCount >= 1 && probCount <= 65531){
                for(int i=0;i<probCount;i++){
                    temps.add(dis.readUnsignedByte());
                }
            }
            temperatureItem.setTemps(temps);
            return temperatureItem;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public List<Integer> getTemps() {
            return temps;
        }

        public void setTemps(List<Integer> temps) {
            this.temps = temps;
        }
    }
}
