package com.dukla.base.util.excel;

import java.util.List;

public class DataValues {

    public List<Value> values;

    public DataValues(List<Value> values){
        this.values = values;
    }

    public List<Value> getValues(){
        return values;
    }

    private static class Value<T>{

        private int columnIndex;//列序号

        private T value;//值

        public Value(int columnIndex, T value){
            this.columnIndex = columnIndex;
            this.value = value;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}
