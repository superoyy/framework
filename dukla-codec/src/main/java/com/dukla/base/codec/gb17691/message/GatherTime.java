package com.dukla.base.codec.gb17691.message;


import java.io.*;

public class GatherTime {

    private int year;//年 0～99

    private int month;//月 1～12

    private int day;//日 1～31

    private int hour;//小时 0～23

    private int minute;//分 0～59

    private int second;//秒 0～59

    public GatherTime(byte[] bytes) throws IOException {
        this.decode(bytes);
    }

    public GatherTime() {

    }

    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(is);
        this.year = dis.readUnsignedByte();
        this.month = dis.readUnsignedByte();
        this.day = dis.readUnsignedByte();
        this.hour = dis.readUnsignedByte();
        this.minute = dis.readUnsignedByte();
        this.second = dis.readUnsignedByte();
    }

    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.year);
        dos.write(this.month);
        dos.write(this.day);
        dos.write(this.hour);
        dos.write(this.minute);
        dos.write(this.second);
        return os.toByteArray();
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
