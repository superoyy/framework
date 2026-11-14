package com.dukla.base.util.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据描述
 */
public class DataScheme {

    public static void main(String[] argus){
        DataScheme dataScheme=new DataScheme(new ArrayList<Column>(){{
            this.add(Column.builder().index(0).name("中").key("a").dataType(DataType.STRING).build());
            this.add(Column.builder().index(1).name("中2").key("b").dataType(DataType.STRING).build());
        }});
        dataScheme.getColumns().forEach(column -> {
            column.getDataType();
            column.getIndex();
        });
    }

    private List<Column> columns;

    public List<Column> getColumns() {
        return columns;
    }

    public DataScheme(List<Column> columns){
        this.columns = columns;
    }

    private static class Column{

        private int index;//列序号

        private String name;//名称

        private String key;//键

        private DataType dataType;//数据类型

        private static Column instance = null;

        private Column(){

        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public String getKey() {
            return key;
        }

        public DataType getDataType() {
            return dataType;
        }

        public Column index(int index){
            instance.index = index;
            return instance;
        }

        public Column name(String name){
            instance.name = name;
            return instance;
        }

        public Column key(String key){
            instance.key = key;
            return instance;
        }

        public Column dataType(DataType dataType){
            instance.dataType = dataType;
            return instance;
        }

        public static Column builder(){
            instance = new Column();
            return instance;
        }

        public Column build(){
            Column column=new Column();
            column.dataType = instance.dataType;
            column.index = instance.index;
            column.name = instance.name;
            column.key = instance.key;
            return column;
        }
    }

}
