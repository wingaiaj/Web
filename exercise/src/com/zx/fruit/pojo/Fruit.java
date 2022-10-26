package com.zx.fruit.pojo;

/**
 * @ClassName Fruit
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 20:44
 * @Version 1.0
 */
public class Fruit {
    int fid;
    String fname;
    int fprice;
    int fcount;
    String remark;

    public Fruit(int fid, String fname, int fprice, int fcount, String remark) {
        this.fid = fid;
        this.fname = fname;
        this.fprice = fprice;
        this.fcount = fcount;
        this.remark = remark;
    }

    public Fruit(String fname, int fprice, int fcount, String remark) {
        this.fname = fname;
        this.fprice = fprice;
        this.fcount = fcount;
        this.remark = remark;
    }

    public Fruit() {
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getFprice() {
        return fprice;
    }

    public void setFprice(int fprice) {
        this.fprice = fprice;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
