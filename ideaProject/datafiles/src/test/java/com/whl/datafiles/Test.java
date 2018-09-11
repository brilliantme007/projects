package com.whl.datafiles;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.whl.datafiles.config.MyConfig;
import com.whl.datafiles.customize.annotation.WElement;
import com.whl.datafiles.customize.annotation.WTable;
import com.whl.datafiles.customize.daoImpl.FileInfoDaoImpl;
import com.whl.datafiles.customize.entity.User;
import com.whl.datafiles.domain.FileInfo;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Test {


    @org.junit.Test
    public void test(){
        List<String> list=Arrays.asList("0","10");
        List<String> list1=new ArrayList<>();
        list.stream().filter(value->!value.isEmpty()).forEach(list1::add);
        list1.stream().filter(value->!value.isEmpty()).forEach(System.out::println);
        Stream.of("a", "c", null, "d")
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    @org.junit.Test
    public void ddd() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                countDownLatch.countDown();
            }
        },0,40,TimeUnit.MILLISECONDS);
        countDownLatch.await();
        System.out.println(2);
    }

    @org.junit.Test
    public void un() throws Exception {
        {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config,WXPayConstants.SignType.HMACSHA256,true);
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "腾讯充值中心-QQ会员充值");
            data.put("out_trade_no", "2016090910595900000012");
            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "123.12.12.123");
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            data.put("product_id", "12");
            try {
                Map<String, String> resp = wxpay.unifiedOrder(data);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @org.junit.Test
    public void dddd() throws Exception {
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config,WXPayConstants.SignType.HMACSHA256,true);
            Map<String, String> data = new HashMap<String, String>();
            data.put("out_trade_no", "2016090910595900000012");

            try {
                Map<String, String> resp = wxpay.orderQuery(data);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    @org.junit.Test
    public void testDao(){
        FileInfoDaoImpl fileInfoDao=new FileInfoDaoImpl();
        User user = new User();
        user.setId("this is my id!");
        user.setAge("123");
        user.setDescription("啊哈哈");
        fileInfoDao.add(user);
    }

}
