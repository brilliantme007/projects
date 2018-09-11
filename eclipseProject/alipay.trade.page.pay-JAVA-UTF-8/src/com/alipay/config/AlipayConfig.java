package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091900545826";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsw3b+Zg0RyUgOrdHDRzgRYu68ex6Wj7Std1FktDocd4xMFdqu+n7n6zZEuDtmFM26q/bQUaMTWKs9BWq4QFZq5DfZ5sbG67o8Akn81QyapCAGwdybr0mnbKyhBH25XY5RkvTrVyGLkzaycH9UC/HFOPUS/aCAkov8unc5EGkw9mMz4/lAjLVr7uphlENlAbUXCKdQ6N7aegud8CjcejZo39YMgoGso16SnpVsnKp5mX0YqcEZa5o58bT9zLhrNotbIHUrUo1cASmfamOZDcl5bYv+0/V0XZNnTeaQ620zKuZZnUY9Cl7uLqCmBn/Oqg7Fuq0+20o5BSTIcbTRfUOhAgMBAAECggEAY+NP9Z8Dh3kdVH9jfMi8vjC5X/i/osXL7U/Dm7oorzTfxU4NdoYWwfjZE7qBfXAh0wCCX8gTxafKuilbntR0IrF5KV+pJnI8511bcfespeynJ8EG0Gz05PyMgzyZhId3NesF+NwT5SNkyJ8Kv4l4bFOWPzitiCkzDxiwvimi6E9ZZ5KGhWOAccfrP1FUwFRXf/0RiNerVyWJNaIgpFdXr8GaJ9aEhNBrVAgBufetuYGC3Fd1x6HUsP6KlQhXakIJnno1W8ip2w1F+k0LIOWqIhwY7nj52hBWYvzlHedxXYk6H6t9qiG1uai8MNPN8a/u09TXYpX+sKIdM3OCduyIwQKBgQDc+axzNmrA0MfGIcIVULQraE6/7kRU9KEtq5kqJPS5pSx2x/8p0fwIjO1ec+PZY1PGNAb9cwpNKy3yq0/e4Wk1xDCqx7eQNQacMQiqz5xpN5rU5jnw2qPIGIVEmGU67mosvmD4JShJbyYf1ichKbv7oykWUB5ZAmzv7K13bST4dQKBgQDIJYs6942pVceF63mDYibyzeiCoFSuVZz6n/mAxH4+pFgLZuIdUwNTSi0N/VVlmpZ8uX1SSiYya3Z5CRjpTYdC8H73PFpILFzHRAqc/jBO76eQaDqe1X0KcNbK66grsfCkJKSAUf1FcP21QV/FFTPm2BC3ck83KT07ED4iBu3Y/QKBgQDMbvQ7FrIGtmDMDbk37RTyP6rzZ+Sx0Cw5S1MFKXF7OrN7JKYNM6Z+5ppH0uGN4ZXEfael3UM/h14X7qOS6Glkg6Aer2xErrnx0G9bT3093mKQ6nnaOPxjB0Rr5K1qqt1xjaO6ju3JmlnkBETpKmoucJF3giHBaTwNb6JAJaqZbQKBgQCFLsZ5VKXFmrkMWmcfkD7Or0obMklnQL0W4UEL+LTxRjb4T1wDm2vJDsDwYDVlSPr+wAeurHzCbFQHspALH3X0i0hRiK2i/TI3vwU49sR6omrkYxcu/m0Yd4HWwf3EJcI119RHN0fL36ZT6M6rvLIu2HQ3Pi+MSzrdhK9zwRygmQKBgFioYllkhlDKKJeP9fjl03f+vde5k4Js/xWyNP+kbUJww2Yr/aAhHp+ETBNLzzSX/b6I/ElplLqy0bbQEg47y0wv4g3o+9r9Wp+3KSk8b2jETMz3WDAhG8u95vuRW1r9n0QEi1b3Uho/npyl9u702G5YNgWJVNfTNvbxe5g8PK1q";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArMN2/mYNEclIDq3Rw0c4EWLuvHselo+0rXdRZLQ6HHeMTBXarvp+5+s2RLg7ZhTNuqv20FGjE1irPQVquEBWauQ32ebGxuu6PAJJ/NUMmqQgBsHcm69Jp2ysoQR9uV2OUZL061chi5M2snB/VAvxxTj1Ev2ggJKL/Lp3ORBpMPZjM+P5QIy1a+7qYZRDZQG1FwinUOje2noLnfAo3Ho2aN/WDIKBrKNekp6VbJyqeZl9GKnBGWuaOfG0/cy4azaLWyB1K1KNXAEpn2pjmQ3JeW2L/tP1dF2TZ03mkOttMyrmWZ1GPQpe7i6gpgZ/zqoOxbqtPttKOQUkyHG00X1DoQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

