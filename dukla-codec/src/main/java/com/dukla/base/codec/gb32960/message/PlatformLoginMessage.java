package com.dukla.base.codec.gb32960.message;

import com.dukla.base.codec.gb32960.element.GatherTime;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 平台登入
 */
public class PlatformLoginMessage extends GB32960Message {

    //平台登入时间
    private GatherTime gatherTime;

    //登入流水号
    private int sn;

    private String username;//12 bytes

    private String password;//20 bytes

    //加密规则 0x01：数据不加密；0x02：数据经过RSA算法加密；0x03:数据经过AES128位算法加密；“0xFE”表示异常，“0xFF”表示无效，其他预留。
    private int encryptRule;


    @Override
    byte[] encodeDataUnit() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);

        this.gatherTime.encode(dos);

        dos.writeShort(this.sn);

        byte[] usernameArray = new byte[12];
        if(this.username!=null){
            byte[] array = this.username.getBytes(StandardCharsets.US_ASCII);
            System.arraycopy(array, 0, usernameArray, 0, Math.min(array.length, 12));
        }
        dos.write(usernameArray);

        byte[] passwordArray=new byte[20];
        if(this.password!=null){
            byte[] array = this.password.getBytes(StandardCharsets.US_ASCII);
            System.arraycopy(array, 0, passwordArray, 0, Math.min(array.length, 20));
        }
        dos.write(passwordArray);

        dos.writeByte(this.encryptRule);

        return os.toByteArray();
    }

    @Override
    void decodeDataUnit(DataInputStream dis) throws IOException {
        this.setGatherTime(GatherTime.decode(dis));

        this.setSn(dis.readUnsignedShort());

        byte[] usernameArray=new byte[12];
        dis.read(usernameArray);
        this.setUsername(new String(usernameArray,StandardCharsets.US_ASCII));

        byte[] passwordArray=new byte[20];
        dis.read(passwordArray);
        this.setPassword(new String(passwordArray,StandardCharsets.US_ASCII));

        this.setEncryptRule(dis.readUnsignedByte());
    }

    public GatherTime getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(GatherTime gatherTime) {
        this.gatherTime = gatherTime;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEncryptRule() {
        return encryptRule;
    }

    public void setEncryptRule(int encryptRule) {
        this.encryptRule = encryptRule;
    }
}
