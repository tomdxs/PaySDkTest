package com.dhcc.paysdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.dhcc.dhccpay.DHCPay;
import com.dhcc.dhccpay.helper.AttrGet;
import com.dhcc.dhccpay.pay.paylibrary.WxPayUtil;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        AttrGet.setInit(this);

        WxPayUtil.getSign(this);

        findViewById(R.id.qrPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DHCPay dhcPay = new DHCPay.Builder(PayActivity.this)//当前Activity
                        .wxpay(true)    //存在微信支付
                        .alipay(true)   //存在支付宝支付
                        .unionpay(true) //存在银联支付
                        .setMer_id("321") //聚合平台内部商户号
                        .setShop_name("周大福总店")//店铺名称
                        .setTran_order_id("123123" + ((int) (Math.random() * 1000)))//订单号
                        .setOrder_amt(200001)//支付进入  单位是：分
                        .setOrder_time("2018-11-12 15:36:35")//支付时间
                        .setValid_time(15)//支付有效时间 单位是：分钟（不传默认15分钟）
                        .setTitle("周大福戒指")//商品名称
                        .setBody("商品描述")//商品描述
                        .setNotifyUrl("https://www.baidu.com/")//回调通知地址
                        .setSys_id("TEST")//接入系统标识 测试为：TEST
                        .build();
                dhcPay.startPay();  //跳转页面
            }
        });

    }
}
