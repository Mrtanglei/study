package org.seckill.dto;

import org.seckill.entity.SucessSeckill;
import org.seckill.enums.SeckillState;

/**
 * 封装秒杀执行后的结果
 */
public class SeckillExecution {

    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //状态表示
    private String stateInfo;

    //秒杀成功对象
    private SucessSeckill sucessSeckill;

    public SeckillExecution() {
    }

    public SeckillExecution(long seckillId, SeckillState seckillState) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
    }

    public SeckillExecution(long seckillId, SeckillState seckillState, SucessSeckill sucessSeckill) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
        this.sucessSeckill = sucessSeckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SucessSeckill getSucessSeckill() {
        return sucessSeckill;
    }

    public void setSucessSeckill(SucessSeckill sucessSeckill) {
        this.sucessSeckill = sucessSeckill;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" + "seckillId=" + seckillId + ", state=" + state + ", stateInfo='" + stateInfo + '\'' + ", sucessSeckill=" + sucessSeckill + '}';
    }
}
