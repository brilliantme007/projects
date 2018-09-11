package com.whl.netsongdata;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class MainTest {
	public String host;
	public int port;
	public static final String  dirver="com.mysql.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";
	public static final String username="root";
	public static final String password="123456";

	@Before
	public void start() {
		host = "127.0.0.1";
		port = 6379;
	}

	/**
	 * jedis 链接redis 测试
	 */
	@Test
	public void test() {

		Jedis jedis = new Jedis(host, port);
		jedis.set("admin", "新增");// 新增
		System.out.println(jedis.get("admin"));
		jedis.set("admin", "改变相同的值");// 新增
		System.out.println(jedis.get("admin"));
		jedis.append("admin", "在原有基础上加点东西");// 新增
		System.out.println(jedis.get("admin"));
		jedis.mset("admin", "同时添加多个键值对儿", "age", "123");
		System.out.println(jedis.get("admin") + "," + jedis.get("age"));
		jedis.incr("age");
		System.out.println("加1=" + jedis.get("age"));

	}

	@Test
	public void test1() {
		String[] split = "AXXX|split|B11|split|EXO".split("\\|split\\|");

		for (String string : split) {
			System.out.println(string);
		}

	}

	/*
	 * lucene
	 */

	protected String[] ids = { "1", "2" };

	protected String[] content = { "Amsterdam has lost of add  cancals", "i love  add this girl" };

	protected String[] city = { "Amsterdam", "Venice" };

	private Directory dir;

	@Test
	public void test3() throws IOException {
		String pathFile = "D://lucene";
		dir = FSDirectory.open(new File(pathFile));
		IndexWriter writer = getWriter();
		for (int i = 0; i < ids.length; i++) {
			Document doc = new Document();
			doc.add(new StringField("id", ids[i], Store.YES));
			doc.add(new TextField("content", content[i], Store.YES));
			doc.add(new StringField("city", city[i], Store.YES));
			writer.addDocument(doc);
		}
		System.out.println("init ok?");
		writer.close();
	}

	private IndexWriter getWriter() throws IOException {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
		return new IndexWriter(dir, iwc);
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void search() throws Exception {
		String filePath = "D://lucene";
		Directory dir = FSDirectory.open(new File(filePath));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		Term term = new Term("content", "add");
		TermQuery query = new TermQuery(term);
		TopDocs topdocs = searcher.search(query, 5);
		ScoreDoc[] scoreDocs = topdocs.scoreDocs;
		System.out.println("查询结果总数---" + topdocs.totalHits + "最大的评分--" + topdocs.getMaxScore());
		for (int i = 0; i < scoreDocs.length; i++) {
			int doc = scoreDocs[i].doc;
			Document document = searcher.doc(doc);
			System.out.println("content====" + document.get("content"));
			System.out.println("id--" + scoreDocs[i].doc + "---scors--" + scoreDocs[i].score + "---index--"
					+ scoreDocs[i].shardIndex);
		}
		reader.close();
	}

	/**
	 * jsoup 虾米音乐
	 */

	@Test
	public void TestPaChong() {
		SearchUtil searchUtil = new SearchUtil();
		/*
		 * try { searchUtil.entrance(4,"西界",1); List<Music> musicList =
		 * searchUtil.getmList(); for (Music music : musicList) {
		 * System.out.println(music); } } catch (CommonException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		List<Music> musicList = searchUtil.getMusicList("http://www.xiami.com/artist/dkNO355c1");
		for (Music music : musicList) {
			System.out.println(music);
		}
	}

	@Test
	public void TestTree() {
		List<Map<String, String>> mList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<>();
			map.put("id", (i + 1) + "");
			map.put("pid", i + "");
			map.put("name", "name:" + (i + 1));
			mList.add(map);
		}

		List<Map<String, String>> resultList = new ArrayList<>();
		if (mList != null && mList.size() > 0) {
			for (Map<String, String> map : mList) {
				if (map != null && map.get("pid").equals("0")) {// 顶级
					resultList.add(map);
					getTree(resultList, mList, map.get("id"));
				}
			}

		}
		System.out.println(resultList);
	}

	public void getTree(List<Map<String, String>> resultList, List<Map<String, String>> mList, String pid) {
		for (Map<String, String> map : mList) {
			if (map.get("pid").equals(pid)) {
				resultList.add(map);
				getTree(resultList, mList, map.get("id"));
			}
		}
	}

	/**
	 * 泛型测试
	 * 
	 */
	@Test
	public void pre() {
		B b=new B();
		A a=new A();
		a.setId("123");
		b.setT(a);
		b.setT1(1);
		b.setT2(new Date());
		System.out.println(b);
		System.out.println(b.getT());
		System.out.println(b.getT1());
		System.out.println(b.getT2());
	}
	/**
	 * 百万条数据jdbc
	 * @throws Exception
	 */
	@Test
	public void jdbc() throws Exception{
		Class.forName(dirver);
		Connection connection = DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
		String sql="insert into person (fid,tableid,name) values(?,?,?)";
		PreparedStatement sta = connection.prepareStatement(sql);
		long time = new Date().getTime();
		for(int i=0;i<1000000;i++){			
			sta.setString(1, i+"");
			sta.setString(2, i+"");
			sta.setString(3, "name"+i);
			sta.addBatch();
			if(i%100000==0){
				sta.executeBatch();
				connection.commit();
				System.out.println((new Date().getTime()-time)/1000);
			}
		}
		
		sta.executeBatch();
		connection.commit();
		System.out.println((new Date().getTime()-time)/1000);
		sta.close();
		connection.close();
	}
	@Test
	public void tt(){
		Long sum=0L;
		for(long i=0;i<Integer.MAX_VALUE;i++){
			sum+=i;
		}
		//用时11.几秒
	}
	//这两个例子说明,要优先使用基本类型的数据,避免不必要的装箱
	@Test
	public void tt1(){
		long sum=0L;
		for(long i=0;i<Integer.MAX_VALUE;i++){
			sum+=i;
		}
		//用时1.几秒
	}
	
	@Test
	public void ttt(){
		System.out.println("A|&amp;|B|&amp;|C|&amp;|D".equalsIgnoreCase("A|&amp;|B|&amp;|C|&amp;|D"));
	}
}

class A {
	private String id;
	private String pid;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "A [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}

}

class B<T>{
	private T t;
	private T t1;
	private T t2;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	@Override
	public String toString() {
		return "B [t=" + t + ", t1=" + t1 + ", t2=" + t2 + "]";
	}

	
}

