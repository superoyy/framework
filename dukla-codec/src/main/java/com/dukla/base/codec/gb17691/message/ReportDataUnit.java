package com.dukla.base.codec.gb17691.message;


import com.dukla.base.codec.gb17691.element.*;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 实时/补发上报数据单元
 */
public class ReportDataUnit {

    /**
     * 信息类型标志
     * 1 Bytes
     */
    private TypeId typeId;

    /**
     * 采集时间 年月日时分秒 各占一个字节
     * 6 Bytes
     */
    private GatherTime gatherTime;

    /**
     * 信息体
     */
    private GB17691DataElement element;

    public TypeId getTypeId() {
        return typeId;
    }

    public void setTypeId(TypeId typeId) {
        this.typeId = typeId;
    }

    public GatherTime getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(GatherTime gatherTime) {
        this.gatherTime = gatherTime;
    }

    public GB17691DataElement getElement() {
        return element;
    }

    public void setElement(GB17691DataElement element) {
        this.element = element;
    }

    public ReportDataUnit(DataInputStream dis) throws IOException {
        this.decode(dis);
    }

    public ReportDataUnit(GB17691DataElement dataElement,GatherTime gatherTime) {
        if(dataElement instanceof OBDData){
            this.setTypeId(TypeId.OBD);
        }else if(dataElement instanceof DPFData){
            this.setTypeId(TypeId.DPF);
        }else if(dataElement instanceof TWCData){
            this.setTypeId(TypeId.TWC);
        }else if(dataElement instanceof TWCNOxData){
            this.setTypeId(TypeId.TWCNOX);
        }else if(dataElement instanceof PHEVData){
            this.setTypeId(TypeId.PHEV);
        }else if(dataElement instanceof ExtendData){
            this.setTypeId(TypeId.EXTEND);
        }
        this.setGatherTime(gatherTime);
        this.setElement(dataElement);
    }

    public ReportDataUnit() {
    }

    public void decode(DataInputStream dis) throws IOException {
        this.typeId = TypeId.getByVal(dis.readUnsignedByte());
        if(this.typeId==null){
            throw new IOException("Not support typeId");
        }
        byte[] b=new byte[6];
        dis.readFully(b);
        this.gatherTime = new GatherTime(b);

        switch (this.typeId){
            case OBD:
                this.element = new OBDData(dis);
                break;
            case DPF:
                this.element = new DPFData(dis);
                break;
            case TWC:
                this.element = new TWCData(dis);
                break;
            case TWCNOX:
                this.element = new TWCNOxData(dis);
                break;
            case PHEV:
                this.element = new PHEVData(dis);
                break;
            case EXTEND:
                this.element = new ExtendData(dis);
                break;
            default:
                throw new IOException("Not support typeId="+this.typeId.getVal());
        }
    }

    public byte[] encode() throws IOException {
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(os);
        dos.write(this.typeId.getVal());
        dos.write(this.gatherTime.encode());
        dos.write(this.element.encode());
        return os.toByteArray();
    }
}
