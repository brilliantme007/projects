package com.whl.datafiles.web.alipay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.whl.datafiles.config.AlipayConfig;
import com.whl.datafiles.service.alipay.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@EnableAutoConfiguration
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private AlipayConfig alipayConfig;

    @RequestMapping("/index.html")
    public String index(){
        return "alipay/index";
    }

    /**
     * 支付宝支付请求
     * @param request
     * @param response
     * @param WIDout_trade_no
     * @param WIDsubject
     * @param WIDtotal_amount
     * @param WIDbody
     * @param model
     * @return
     */
    @RequestMapping("/pay.html")
    public String pay(HttpServletRequest request, HttpServletResponse response, @Param("WIDout_trade_no") String WIDout_trade_no,
                      @Param("WIDsubject")String WIDsubject,
                      @Param("WIDtotal_amount")String WIDtotal_amount,
                      @Param("WIDbody")String WIDbody, Model model){
        AlipayClient alipayClient = this.alipayService.getAlipayClient();
        AlipayTradePagePayRequest alipayTradePagePayRequest = this.alipayService.getAlipayTradePagePayRequest();
        alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ WIDout_trade_no +"\","
                + "\"total_amount\":\""+ WIDtotal_amount +"\","
                + "\"subject\":\""+ WIDsubject +"\","
                + "\"body\":\""+ WIDbody +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        try {
            String body = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
            model.addAttribute("result",body);
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }
        return "alipay/return";
    }

    /**
     * 支付宝同步回调请求
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/return.html",method = RequestMethod.GET)
    public String returnurl(HttpServletRequest request, HttpServletResponse response,Model model){

        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(),alipayConfig.getCharset(),alipayConfig.getSignType());
        } catch (Exception e) {
            model.addAttribute("result",e.getMessage());
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {

            try {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

                model.addAttribute("result","trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            } catch (Exception e) {
                model.addAttribute("result",e.getMessage());
            }
        }else {
            model.addAttribute("result","验签失败");
        }
        return "alipay/return";
    }


    /**
     * 支付宝支付状态查询请求
     * @param request
     * @param response
     * @param WIDTQout_trade_no
     * @param WIDTQtrade_no
     * @param model
     * @return
     */

    @RequestMapping("/query.html")
    public String search(HttpServletRequest request, HttpServletResponse response, @Param("WIDTQout_trade_no") String WIDTQout_trade_no,
                      @Param("WIDTQtrade_no")String WIDTQtrade_no, Model model){

        AlipayClient alipayClient = this.alipayService.getAlipayClient();

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTQout_trade_no +"\","+"\"trade_no\":\""+ WIDTQtrade_no +"\"}");

        //请求
        try {
            String result = alipayClient.execute(alipayRequest).getBody();
            model.addAttribute("result",result);
        } catch (AlipayApiException e) {
            model.addAttribute("result",e.getMessage());
        }

        return "alipay/return";
    }

    /**
     * 支付宝退款请求
     * @param request
     * @param response
     * @param WIDTRout_trade_no
     * @param WIDTRtrade_no
     * @param WIDTRrefund_amount
     * @param WIDTRrefund_reason
     * @param WIDTRout_request_no
     * @param model
     * @return
     */
    @RequestMapping("/refund.html")
    public String refund(HttpServletRequest request, HttpServletResponse response, @Param("WIDTRout_trade_no") String WIDTRout_trade_no,
                      @Param("WIDTRtrade_no")String WIDTRtrade_no,
                      @Param("WIDTRrefund_amount")String WIDTRrefund_amount,
                      @Param("WIDTRrefund_reason")String WIDTRrefund_reason,
                      @Param("WIDTRout_request_no")String WIDTRout_request_no,
                         Model model){

        AlipayClient alipayClient = this.alipayService.getAlipayClient();

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTRout_trade_no +"\","
                + "\"trade_no\":\""+ WIDTRtrade_no +"\","
                + "\"refund_amount\":\""+ WIDTRrefund_amount +"\","
                + "\"refund_reason\":\""+ WIDTRrefund_reason +"\","
                + "\"out_request_no\":\""+ WIDTRout_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            result=e.getMessage();
        }

      model.addAttribute("result",result);

        return "alipay/return";
    }

    /**
     * 支付宝退款查询请求
     * @param request
     * @param response
     * @param WIDRQout_trade_no
     * @param WIDRQtrade_no
     * @param WIDRQout_request_no
     * @param model
     * @return
     */
    @RequestMapping("/refundQuery.html")
    public String refundQuery(HttpServletRequest request, HttpServletResponse response, @Param("WIDRQout_trade_no") String WIDRQout_trade_no,
                      @Param("WIDRQtrade_no")String WIDRQtrade_no,
                      @Param("WIDRQout_request_no")String WIDRQout_request_no,
                         Model model){

        AlipayClient alipayClient = this.alipayService.getAlipayClient();

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDRQout_trade_no +"\","
                +"\"trade_no\":\""+ WIDRQtrade_no +"\","
                +"\"out_request_no\":\""+ WIDRQout_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            result=e.getMessage();
        }

        //输出
        model.addAttribute("result",result);

        return "alipay/return";
    }

    /**
     * 支付宝关闭交易请求
     * @param request
     * @param response
     * @param WIDTCout_trade_no
     * @param WIDTCtrade_no
     * @param model
     * @return
     */
    @RequestMapping("/tradeClose.html")
    public String tradeClose(HttpServletRequest request, HttpServletResponse response, @Param("WIDTCout_trade_no") String WIDTCout_trade_no,
                      @Param("WIDTCtrade_no")String WIDTCtrade_no,
                         Model model){

        AlipayClient alipayClient = this.alipayService.getAlipayClient();

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTCout_trade_no +"\"," +"\"trade_no\":\""+ WIDTCtrade_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            result=e.getMessage();
        }

        model.addAttribute("result",result);
        return "alipay/return";
    }

}
