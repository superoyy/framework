package com.dukla.base.codec.gb17691.element;

import java.io.*;

/**
 * 签名数据
 */
public class SignData {

    /**
     * 签名 R 值长度
     * 1 Byte
     */
    private int rLength;

    /**
     * 签名 R
     * rLength Byte
     */
    private byte[] r;

    /**
     * 签名 S 值长度
     * 1 Byte
     */
    private int sLength;

    /**
     * 签名 S
     * sLength Byte
     */
    private byte[] s;

    public int getrLength() {
        return rLength;
    }

    public void setrLength(int rLength) {
        this.rLength = rLength;
    }

    public byte[] getR() {
        return r;
    }

    public void setR(byte[] r) {
        this.r = r;
    }

    public int getsLength() {
        return sLength;
    }

    public void setsLength(int sLength) {
        this.sLength = sLength;
    }

    public byte[] getS() {
        return s;
    }

    public void setS(byte[] s) {
        this.s = s;
    }

    public SignData(){
    }

    public SignData(byte[] bytes) throws IOException {
        this.decode(bytes);
    }

    public SignData(DataInputStream dis) throws IOException {
        this.build(dis);
    }

    private void build(final DataInputStream dis) throws IOException {
        this.rLength = dis.readUnsignedByte();
        this.r=new byte[this.rLength];
        dis.readFully(this.r);

        this.sLength = dis.readUnsignedByte();
        this.s=new byte[this.sLength];
        dis.readFully(this.s);
    }

    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);

        dos.write(this.rLength);
        dos.write(this.r);
        dos.write(this.sLength);
        dos.write(this.s);

        return os.toByteArray();
    }

    public void decode(byte[] bytes) throws IOException {
        ByteArrayInputStream is=new ByteArrayInputStream(bytes);
        DataInputStream dis=new DataInputStream(is);
        this.build(dis);
    }

    public static void main(String[] argus){
        SignData signData=new SignData();

        //encode test
//        signData.setrLength(8);
//        signData.setR(new byte[]{0,1,2,3,4,5,6,7});
//        signData.setsLength(16);
//        signData.setS(new byte[]{0,1,2,3,4,5,6,7,8,9,0xa,0xb,0xc,0xd,0xe,0xf});
//        try {
//            byte[] bytes=signData.encode();
//            System.out.println("SignData Data Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            signData.decode(HexUtils.fromHexString("08000102030405060710000102030405060708090a0b0c0d0e0f"));
//            System.out.println("RLength="+signData.getrLength());
//            System.out.println("R="+HexUtils.toHexString(signData.getR()));
//            System.out.println("SLength="+signData.getsLength());
//            System.out.println("S="+HexUtils.toHexString(signData.getS()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
