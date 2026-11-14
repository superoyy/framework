package com.dukla.base.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseDao {

    private static final Logger logger = LoggerFactory.getLogger(HBaseDao.class);
    private Connection connection;

    private Map<String, Table> tableMap=new HashMap<>();

    public HBaseDao(Connection connection){
        this.connection = connection;
    }

    public List<HBaseRecord> scan(String tableName,String columns,byte[] startRow,byte[] endRow){
        List<HBaseRecord> records=new ArrayList<>();
        Table table=this.tableMap.get(tableName);
        if(table==null){
            try {
                table = this.connection.getTable(TableName.valueOf(tableName));
                this.tableMap.put(tableName,table);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
                return records;
            }
        }
        Scan scan=new Scan();
        scan.setStartRow(startRow);
        scan.setStopRow(endRow);
        String[] fs = columns.split(",");
        for(String f:fs){
            String[] cs=f.split(":");
            scan.addColumn(Bytes.toBytes(cs[0]),Bytes.toBytes(cs[1]));
        }

        ResultScanner resultScanner = null;
        try {
            resultScanner = table.getScanner(scan);
            while (resultScanner.iterator().hasNext()){
                String rowKey = null ;
                Map<String,Map<String,String>> values=new HashMap<>();

                Result result = resultScanner.iterator().next();

                Cell[] cells=result.rawCells();
                for(Cell cell:cells){
                    if(rowKey==null){
                        rowKey = Bytes.toString(cell.getRowArray());
                    }
                    String family = Bytes.toString(cell.getFamilyArray());
                    String qualifier = Bytes.toString(cell.getQualifierArray());
                    String value = Bytes.toString(cell.getValueArray());
                    values.put(family,new HashMap<String,String>(){{
                        put(qualifier,value);
                    }});
                }
                HBaseRecord hBaseRecord=new HBaseRecord();
                hBaseRecord.setRowKey(rowKey);
                hBaseRecord.setValues(values);
                records.add(hBaseRecord);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }finally {
            if (resultScanner!=null){
                resultScanner.close();
            }
        }
        return records;
    }



}
