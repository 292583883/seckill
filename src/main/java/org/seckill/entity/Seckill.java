package org.seckill.entity;

import java.util.Date;

/**
 * @author Administrator
 * @date 2016/5/22.
 */
public class Seckill {
    private long secKillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" + "seckillId=" + secKillId + ",name=" + name + ",number=" + number + ",startTime=" + startTime + ",endTime=" + endTime + ",createTime=" + createTime + ")";
    }
}
