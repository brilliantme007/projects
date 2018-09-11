package com.whl.datafiles.service.alipay;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.whl.datafiles.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlipayService {

    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;


    /**
     * 获取alipayClient
     * @return
     */
    public AlipayClient getAlipayClient(){
        if(alipayClient==null){
            alipayClient=new DefaultAlipayClient(alipayConfig.getGatewayUrl(),alipayConfig.getAppId(),alipayConfig.getMerchantPrivateKey(),alipayConfig.getFormat(),alipayConfig.getCharset(),alipayConfig.getAlipayPublicKey(),alipayConfig.getSignType());
        }
        return alipayClient;
    }

    /**
     * 电脑网站支付请求request
     * @return
     */
    public AlipayTradePagePayRequest getAlipayTradePagePayRequest(){
        AlipayTradePagePayRequest alipayTradePagePayRequest=new AlipayTradePagePayRequest();
        alipayTradePagePayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayTradePagePayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        return alipayTradePagePayRequest;
    }
}
