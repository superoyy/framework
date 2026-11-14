package com.dukla.base.hbase;

import java.util.Map;

public class HBaseRecord {
    private String rowKey;

    /**
     * key1: family hex string
     * key2: qualifier hex string
     * value: value hex string
     */
    private Map<String,Map<String,String>> values;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Map<String, Map<String, String>> getValues() {
        return values;
    }

    public void setValues(Map<String, Map<String, String>> values) {
        this.values = values;
    }
}
