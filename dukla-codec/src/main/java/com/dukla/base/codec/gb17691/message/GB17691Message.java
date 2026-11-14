package com.dukla.base.codec.gb17691.message;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 符合GB17691-2018和HJ1239.3-2021的报文结构
 */
public abstract class GB17691Message {

    private static final int START_SYMBOL = 0x2323;

    /**
     * 命令单元
     * 1 Byte
     */
    private CmdId cmdId;

    /**
     * 17 Byte
     */
    private String vin;

    /**
     * 终端软件版本号
     * 1 Byte
     */
    private int termSoftwareVer;

    /**
     * 加密类型
     * 1 Byte
     * 0x01：数据不加密；
     * 0x02：数据经过RSA算法加密；
     * 0x03:数据经过SM2位算法加密；
     * “0xFE”表示异常，
     * “0xFF”表示无效
     */
    private int encryptType;

    /**
     * 数据单元长度
     * 2 Byte
     * 有效值范围：0～65531。
     */
    private int dataUnitLength;

    /**
     * 校验码
     * 1 Byte
     * 采用BCC（异或校验）法，校验范围从命令单元的第一个字节开始，同后一字节异或，直到校验码前一字节为止，
     */
    private int bcc;

    public CmdId getCmdId() {
        return cmdId;
    }

    public void setCmdId(CmdId cmdId) {
        this.cmdId = cmdId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getTermSoftwareVer() {
        return termSoftwareVer;
    }

    public void setTermSoftwareVer(int termSoftwareVer) {
        this.termSoftwareVer = termSoftwareVer;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }

    public int getDataUnitLength() {
        return dataUnitLength;
    }

    public void setDataUnitLength(int dataUnitLength) {
        this.dataUnitLength = dataUnitLength;
    }

    public int getBcc() {
        return bcc;
    }

    /**
     * 编码
     */
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        //帧头
        dos.writeShort(START_SYMBOL);
        //命令单元
        dos.write(this.cmdId.getVal());
        byte[] vinBytes = this.vin.getBytes(StandardCharsets.US_ASCII);
        if(vinBytes.length!=17){
            throw new IOException("VIN must be 17 bytes");
        }
        //vin
        dos.write(this.vin.getBytes(StandardCharsets.US_ASCII));
        //终端软件版本号
        dos.write(this.termSoftwareVer);
        //加密类型
        dos.write(this.encryptType);
        //数据单元
        byte[] dataUnitArray=this.encodeDataUnit();//调用子类方法
        dos.writeShort(dataUnitArray.length);//长度
        dos.write(dataUnitArray);//数据单元
        //BCC
        this.bcc=this.genBcc(os.toByteArray());
        dos.write(this.bcc);
        return os.toByteArray();
    }

    /**
     * 解码
     */
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(is);
        //报文头
        int startSymbol = dis.readUnsignedShort();
        if(startSymbol != START_SYMBOL){
            throw new IOException("Not start with 0x2323");
        }
        //命令单元
        this.cmdId = CmdId.getByVal(dis.readUnsignedByte());
        //vin
        byte[] vinBytes=new byte[17];
        dis.readFully(vinBytes);
        this.vin = new String(vinBytes,StandardCharsets.US_ASCII);
        this.termSoftwareVer = dis.readUnsignedByte();
        //数据单元加密方式
        this.encryptType = dis.readUnsignedByte();
        //数据单元长度
        this.dataUnitLength = dis.readUnsignedShort();
        //数据单元
        this.decodeDataUnit(this.dataUnitLength,dis);//调用子类方法
        //bcc
        this.bcc=dis.readUnsignedByte();
    }

    /**
     * 编码数据单元
     * 子类实现
     */
    abstract byte[] encodeDataUnit() throws IOException;

    /**
     * 解码数据单元
     * 子类实现
     */
    abstract void decodeDataUnit(int dataUnitLength,DataInputStream dis) throws IOException;

    /**
     * 生成bbc码
     */
    private byte genBcc(byte[] bytes){
        byte bcc = 0;//从第2个字节开始向后异或
        for(int i = 2 ; i < bytes.length ; i++){
            bcc ^= bytes[i];
        }
        return bcc;
    }
}
