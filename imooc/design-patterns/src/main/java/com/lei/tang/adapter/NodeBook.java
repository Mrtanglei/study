package com.lei.tang.adapter;

/**
 * @author tanglei
 * @date 18/9/11 下午2:30
 */
public class NodeBook {

    private ThreePlug threePlug;

    public NodeBook(ThreePlug threePlug) {
        this.threePlug = threePlug;
    }

    public void power() {
        threePlug.powerWithThree();
    }

    public static void main(String[] args) {
        ThreePlug threePlug = new TwoPlugAdapter(new GBTwoPlug());
        NodeBook nodeBook = new NodeBook(threePlug);
        nodeBook.power();
        System.out.println("==============");
        threePlug = new TwoPlugAdapterExtends();
        nodeBook = new NodeBook(threePlug);
        nodeBook.power();
    }
}
