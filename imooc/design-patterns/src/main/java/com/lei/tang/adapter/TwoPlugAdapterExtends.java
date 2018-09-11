package com.lei.tang.adapter;

/**
 * @author tanglei
 * @date 18/9/11 下午2:42
 */
public class TwoPlugAdapterExtends extends GBTwoPlug implements ThreePlug {

    @Override
    public void powerWithThree() {
        this.powerWithTwo();
    }
}
