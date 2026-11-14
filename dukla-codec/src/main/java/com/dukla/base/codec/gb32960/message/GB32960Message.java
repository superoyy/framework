package com.dukla.base.codec.gb32960.message;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class GB32960Message {

    private static final int START_SYMBOL = 0x2323;

    //命令标识
    private CmdId cmdId;

    //应答标志,0x01:应答成功 0x02:应答错误(设置未成功) 0x03:应答VIN重复 0xFE:请求命令,而非应答
    private RespFlag respFlag;

    //17位 ASCII
    private String vin;

    //加密类型 0x01：数据不加密；0x02：数据经过RSA算法加密；0x03:数据经过AES128位算法加密；“0xFE”表示异常，“0xFF”表示无效，其他预留。
    private int encryptType;

    private int dataUnitLength;

    private int bcc;

    /**
     * 编码
     * 对象->数组
     */
    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        //帧头
        dos.writeShort(START_SYMBOL);
        //命令单元-命令标识
        dos.writeByte(this.cmdId.getVal());
        //命令单元-应答标志
        dos.writeByte(this.respFlag.getVal());
        //vin
        byte[] vinArray=new byte[17];
        if(this.vin!=null){
            byte[] array = this.vin.getBytes(StandardCharsets.US_ASCII);
            System.arraycopy(array, 0, vinArray, 0, Math.min(array.length, 17));
        }
        dos.write(vinArray);
        //加密类型
        dos.writeByte(this.encryptType);
        //数据单元
        byte[] dataUnitArray = this.encodeDataUnit();//调用子类方法
        //数据单元长度
        dos.writeShort(dataUnitArray.length);
        dos.write(dataUnitArray);
        //bcc
        dos.writeByte(this.genBcc(os.toByteArray()));

        return os.toByteArray();
    }

    /**
     * 编码数据单元
     * 子类实现
     */
    abstract byte[] encodeDataUnit() throws IOException;

    /**
     * 解码
     * 数组->对象
     */
    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(is);
        dis.readUnsignedShort();//丢掉 2 字节起始符
        //命令单元
        this.setCmdId(CmdId.getByVal(dis.readUnsignedByte()));
        //应答标志
        this.setRespFlag(RespFlag.getByVal(dis.readUnsignedByte()));
        //vin
        byte[] vinBytes = new byte[17];
        dis.read(vinBytes);
        this.setVin(new String(vinBytes,StandardCharsets.US_ASCII));
        //数据单元加密方式
        this.setEncryptType(dis.readUnsignedByte());
        //数据单元长度
        this.dataUnitLength = dis.readUnsignedShort();
        //数据单元
        this.decodeDataUnit(dis);//调用子类方法
        //bcc
        this.bcc=dis.readUnsignedByte();
    }

    /**
     * 解码数据单元
     * 子类实现
     */
    abstract void decodeDataUnit(DataInputStream dis) throws IOException;

    //生成bbc码
    private byte genBcc(byte[] bytes){
        byte bcc = 0;//从第2个字节开始向后异或
        for(int i = 2 ; i < bytes.length ; i++){
            bcc ^= bytes[i];
        }
        return bcc;
    }

    public CmdId getCmdId() {
        return cmdId;
    }

    public void setCmdId(CmdId cmdId) {
        this.cmdId = cmdId;
    }

    public RespFlag getRespFlag() {
        return respFlag;
    }

    public void setRespFlag(RespFlag respFlag) {
        this.respFlag = respFlag;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

    public int getBcc() {
        return bcc;
    }

}
