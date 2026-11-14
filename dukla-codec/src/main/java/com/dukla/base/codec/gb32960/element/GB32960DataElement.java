package com.dukla.base.codec.gb32960.element;

import java.io.DataOutputStream;
import java.io.IOException;

public interface GB32960DataElement {

     byte[] encode() throws IOException;

     void encode(DataOutputStream dos) throws IOException;

}
