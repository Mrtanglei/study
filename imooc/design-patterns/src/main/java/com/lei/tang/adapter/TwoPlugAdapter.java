package com.lei.tang.adapter;

/**
 * 组合适配器
 *
 * @author tanglei
 * @date 18/9/11 下午2:32
 */
public class TwoPlugAdapter implements ThreePlug {

    private GBTwoPlug gbTwoPlug;

    public TwoPlugAdapter(GBTwoPlug gbTwoPlug) {
        this.gbTwoPlug = gbTwoPlug;
    }

    @Override
    public void powerWithThree() {
        gbTwoPlug.powerWithTwo();
    }
}
