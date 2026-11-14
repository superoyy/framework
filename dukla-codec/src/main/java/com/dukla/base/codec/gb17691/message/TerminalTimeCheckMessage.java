package com.dukla.base.codec.gb17691.message;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 终端校时
 */
public class TerminalTimeCheckMessage extends GB17691Message {

    public TerminalTimeCheckMessage(){
        this.setCmdId(CmdId.TERMINAL_TIME_CHECK);
    }

    public TerminalTimeCheckMessage(byte[] bytes) throws IOException{
        this.decode(bytes);
    }


    @Override
    byte[] encodeDataUnit() throws IOException {
        return new byte[0];
    }

    @Override
    void decodeDataUnit(int dataUnitLength,final DataInputStream dis)  throws IOException {
    }

    public static void main(String[] argus){
        TerminalTimeCheckMessage terminalTimeCheckMessage=new TerminalTimeCheckMessage();

        //encode test
//        terminalTimeCheckMessage.setVin("12345678901234567");
//        terminalTimeCheckMessage.setEncryptType(0);
//        terminalTimeCheckMessage.setTermSoftwareVer(1);
//
//        try {
//            byte[] bytes=terminalTimeCheckMessage.encode();
//            System.out.println("TerminalTimeCheckMessage Hex="+ HexUtils.toHexString(bytes));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //decode test
//        try {
//            terminalTimeCheckMessage.decode(HexUtils.fromHexString("23230531323334353637383930313233343536370100000035"));
//            System.out.println("CmdId="+terminalTimeCheckMessage.getCmdId().getVal());
//            System.out.println("Vin="+terminalTimeCheckMessage.getVin());
//            System.out.println("TermSoftwareVer="+terminalTimeCheckMessage.getTermSoftwareVer());
//            System.out.println("EncryptType="+terminalTimeCheckMessage.getEncryptType());
//            System.out.println("DataUnitLength="+terminalTimeCheckMessage.getDataUnitLength());
//            System.out.println("Bcc="+terminalTimeCheckMessage.getBcc());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
