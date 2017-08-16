package com.o2.models;

public class Customer {

    private int custnum;
    private long o2bfid;
    private String identity_id;

    public int getCustnum() {
        return custnum;
    }

    public void setCustnum(int custnum) {
        this.custnum = custnum;
    }

    public long getO2bfid() {
        return o2bfid;
    }

    public void setO2bfid(long o2bfid) {
        this.o2bfid = o2bfid;
    }

    public String getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(String identity_id) {
        this.identity_id = identity_id;
    }

    public String toString() {
        return custnum + "," + o2bfid + "," + identity_id;
    }

}
