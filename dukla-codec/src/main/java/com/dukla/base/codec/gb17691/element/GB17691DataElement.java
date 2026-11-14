package com.dukla.base.codec.gb17691.element;

import java.io.IOException;

public interface GB17691DataElement {

     byte[] encode() throws IOException;

     void decode(byte[] bytes) throws IOException;

}
