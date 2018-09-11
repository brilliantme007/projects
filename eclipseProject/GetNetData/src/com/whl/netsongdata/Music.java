package com.whl.netsongdata;

public class Music {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 歌名 */
	private String musicName;
	/** 歌手名 */
	private String airtistName;
	/** 歌曲路径 */
	private String path;
	/** 专辑名 */
	private String albumName;
	/** 小图URL */
	private String smallAlumUrl;
	/** 大图URL */
	private String bigAlumUrl;
	/** 歌曲id */
	private String musicId;
	/** 歌词地址 */
	private String lrcUrl;
	
	public String getMusicName() {
		return musicName;
	}
	
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	
	public String getAirtistName() {
		return airtistName;
	}
	
	public void setAirtistName(String airtistName) {
		this.airtistName = airtistName;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getSmallAlumUrl() {
		return smallAlumUrl;
	}
	
	public void setSmallAlumUrl(String smallAlumUrl) {
		this.smallAlumUrl = smallAlumUrl;
	}
	
	public String getBigAlumUrl() {
		return bigAlumUrl;
	}
	
	public void setBigAlumUrl(String bigAlumUrl) {
		this.bigAlumUrl = bigAlumUrl;
	}
	
	public String getMusicId() {
		return musicId;
	}
	
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	
	public String getLrcUrl() {
		return lrcUrl;
	}
	
	public void setLrcUrl(String lrcUrl) {
		this.lrcUrl = lrcUrl;
	}

	@Override
	public String toString() {
		return "Music [musicName=" + musicName + ", airtistName="
				+ airtistName + ", path=" + path + ", albumName=" + albumName
				+ ", smallAlumUrl=" + smallAlumUrl + ", bigAlumUrl="
				+ bigAlumUrl + ", musicId=" + musicId + ", lrcUrl=" + lrcUrl
				+ "]";
	}
	
}

