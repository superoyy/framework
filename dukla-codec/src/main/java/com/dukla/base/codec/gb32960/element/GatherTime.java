package com.dukla.base.codec.gb32960.element;


import java.io.*;

public class GatherTime implements GB32960DataElement {

    private int year;//年 0～99

    private int month;//月 1～12

    private int day;//日 1～31

    private int hour;//小时 0～23

    private int minute;//分 0～59

    private int second;//秒 0～59

    public static GatherTime decode(byte[] bytes) throws IOException {
        return GatherTime.decode(new DataInputStream(new ByteArrayInputStream(bytes)));
    }

    public static GatherTime decode(DataInputStream dis) throws IOException {
        GatherTime gatherTime=new GatherTime();

        gatherTime.setYear(dis.readUnsignedByte());
        gatherTime.setMonth(dis.readUnsignedByte());
        gatherTime.setDay(dis.readUnsignedByte());
        gatherTime.setHour(dis.readUnsignedByte());
        gatherTime.setMinute(dis.readUnsignedByte());
        gatherTime.setSecond(dis.readUnsignedByte());

        return gatherTime;
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        this.encode(new DataOutputStream(os));
        return os.toByteArray();
    }

    @Override
    public void encode(DataOutputStream dos) throws IOException {
        dos.writeByte(this.year);
        dos.writeByte(this.month);
        dos.writeByte(this.day);
        dos.writeByte(this.hour);
        dos.writeByte(this.minute);
        dos.writeByte(this.second);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
