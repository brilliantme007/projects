package org.wanholi.erw.pub;
/**
 * A4:默认
 * A4H:A4横向
 * @author lifei
 * @version 1.0
 *  2017.11.10
 *
 */
public enum PageSize {
	A4(11906,16838),
	A4H(16838,11906),
	A3(0,0);
	
	 public Integer width;
	 public Integer height;
	private PageSize(Integer w,Integer h){
		this.width=w;
		this.height=h;
	}

	
}
