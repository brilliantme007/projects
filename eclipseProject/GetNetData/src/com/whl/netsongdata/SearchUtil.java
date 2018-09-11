package com.whl.netsongdata;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SearchUtil {

	private  String keySearchUrl;

	/**
	 * 存放音乐或者是专辑,或是艺人的列表
	 */
	private List<Music> mList;
	/**
	 * 获取音乐或者是专辑,或是艺人的列表
	 * @return
	 */
	public List<Music> getmList() {
		return mList;
	}
	/**
	 * 默认的最外层的类
	 */
	private  String clazz="track_list";

	/**
	 * 搜索关键字地址(全部)
	 */
	public static final String  KEY_SEARCH_URL_ALL="http://www.xiami.com/search";
	/**
	 * 搜索关键字地址(歌曲)
	 */
	public static final String KEY_SEARCH_URL_MUSIC="http://www.xiami.com/search/song";
	/**
	 * 搜索关键字地址(歌词)
	 */
	public static final String KEY_SEARCH_URL_SONG_LYRIC="http://www.xiami.com/search/song-lyric";
	/**
	 * 搜索关键字地址(艺人)
	 */
	public static final String KEY_SEARCH_URL_ARTIST="http://www.xiami.com/search/artist";


	/**
	 * 歌曲id接口
	 */
	public static final String ID_SEARCH_URL="http://www.xiami.com/song/playlist/id/";

	/**
	 * 搜索入口
	 * @param index(1:全部搜索,2:根据歌曲名字搜索,3.根据歌词搜索,4:搜索艺人)
	 * @param key(搜索关键字)
	 * @param page(页数,全部搜索的时候只有一页)
	 * @throws CommonException
	 */
	public  void entrance(int index,String key,int page) throws CommonException{
		switch(index){
		default://全部搜索
			keySearchUrl=KEY_SEARCH_URL_ALL+"/page/"+page+"?key="+key;
			break;
		case 1://全部搜索
			keySearchUrl=KEY_SEARCH_URL_ALL+"/page/"+page+"?key="+key;
			break;
		case 2://根据歌曲名字搜索
			keySearchUrl=KEY_SEARCH_URL_MUSIC+"/page/"+page+"?key="+key;
			break;
		case 3://根据歌词搜索
			keySearchUrl=KEY_SEARCH_URL_SONG_LYRIC+"/page/"+page+"?key="+key;
			clazz="all_LRC";
			break;
		case 4://搜索艺人
			keySearchUrl=KEY_SEARCH_URL_ARTIST+"/page/"+page+"?key="+key;
			break;
		}
		if(index==4){
			mList=getArtistList(keySearchUrl);
		}else{
			mList = getMusicList(keySearchUrl);			

		}
	}


	/**
	 * 获取艺人相关列表
	 * @param keySearchUrl
	 * @return
	 */
	private List<Music> getArtistList(String keySearchUrl) {
		List<Music> musicList=new ArrayList<Music>();
		String html=null;
		try {
			html=getHtml(keySearchUrl);
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByClass("artistBlock_list");
		if(elements!=null&&elements.size()>0){
			Elements all = elements.get(0).getElementsByClass("artist_item100_block");
			if(all!=null&&all.size()>0){
				for (Element element : all) {
					Music music=new Music();
					music.setBigAlumUrl(element.getElementsByTag("img").attr("src"));
					music.setPath(element.getElementsByClass("artist100").attr("href"));
					music.setAirtistName(element.getElementsByClass("title").attr("title"));
					musicList.add(music);
				}

			}

		}

		return musicList;
	}
	
	/**
	 * 获取网页html
	 * @param urlString
	 * @return
	 * @throws CommonException
	 */
	public  String getHtml(String urlString) throws CommonException{
		StringBuffer sb=new StringBuffer();
		try {
			URL url=new URL(urlString);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(30000);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				InputStream is=conn.getInputStream();
				int len=0;
				byte[] buf= new byte[1024];
				while((len=is.read(buf))!=-1){
					sb.append(new String(buf,0,len,"UTF-8"));
				}
				is.close();
			}else{
				throw new CommonException("访问网络失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("访问网络失败!");
		}
		return sb.toString();
	}

	/**
	 * 抓取歌曲id
	 * @param input
	 */
	public List<String> getIds(String url){
		List<String> allIds=new ArrayList<String>();
		String html=null;
		try {
			html= getHtml(url);
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document document=null;
		if(!StringUtil.isEmpty(html)){
			document=Jsoup.parse(html);
			Elements elements = document.getElementsByClass(clazz);
			if(elements!=null&&elements.size()>0){

				Elements all = elements.get(0).getElementsByClass("chkbox");
				if(all!=null&&all.size()>0){
					for (Element element : all) {
						String id=element.select("input").attr("value");
						if(!StringUtil.isEmpty(id)){
							allIds.add(id);
						}
					}
				}
			}
		}
		return allIds;
	}
	/**
	 * 通过ids获取music数据列表
	 * @param ids
	 * @return
	 */
	public List<Music> getMusicList(String url){
		List<String> ids = getIds(url);
		List<Music> musicList=new ArrayList<Music>();
		if(ids!=null&&ids.size()>0){
			for (String id : ids) {
				String postUrl=ID_SEARCH_URL+id;
				String html =null;
				try {
					html = getHtml(postUrl);
				} catch (CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Document doc=Jsoup.parse(html);
				Elements elements = doc.select("trackList");

				if(elements!=null&&elements.size()>0){
					for (Element element : elements) {
						Music music=new Music();
						music.setMusicId(id);
						music.setMusicName(element.select("songName").text());
						music.setAirtistName(element.select("artist").text());
						music.setSmallAlumUrl(element.select("pic").text());
						music.setBigAlumUrl(element.select("album_pic").text());
						music.setLrcUrl(element.select("lyric").text());
						music.setAlbumName(element.select("album_name").text());
						music.setPath(StringUtil.decodeMusicUrl(element.select("location").text()));

						musicList.add(music);
					}
				}
			}
		}

		return musicList;

	}
	/**
	 * 解析歌名
	 * 
	 * @return
	 */
	private String getSubString(String name) {
		int start = name.indexOf("[", 3) + 1;
		int end = name.indexOf("]");
		return name.substring(start, end);
	}


}
