package org.wanholi.erw.pub;
/**
 * LEFT:左对齐
 * CENTER:居中
 * RIGHT:右对齐
 * TOP:居上，用于表格
 * BOTTOM:居下，用于表格
 * DISTRIBUTE:分散对齐
 * @author lifei
 * @version 1.0
 * 2017.11.10
 */
public enum Alignment {
	LEFT("left"),
        /**
         * 居中
         */
	CENTER("center"),
	RIGHT("right"),
	TOP("top"),
	BOTTOM("bottom"),
        DISTRIBUTE("distribute");
	public String val;
	private Alignment(String val){
		this.val=val;
	}
}
