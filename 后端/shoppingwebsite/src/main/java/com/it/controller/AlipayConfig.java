
package com.it.controller;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000127628368";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyK0uYlER+CI0ztsnbsT9wnvJPkdepvpX7i6GSQb5D5P9YS4M13JyiHnAVolxpeCdcmtvqVHsQgfDe5KuD5rlJ0m/1PaFrlKr4NyaTRG0V/Pw7rsqox9AWgMlRHfrixkcRHkxOHG6S7hYqzCBnPfvBhek/t2kW+KZF8FxwfSH1i/T0lm7WZsN9trj6aIjNloWNGv9AqRZIaQbrAoX3R2acUh03HcCz4liceDOU6DWBOOzl4mpiMJgQhyaJgV/QB5U1+a9gRusvBSDuCDB/QGs9Vkw8/RKMOtI7pnF5fb7+aawIKGbobrdyA64UNdLt+kO1xmGIJpCfx6S6b1GENN53AgMBAAECggEAXZaCchZH2BAz45s7cywqz8SeMY/BQge7jtNsvn/MKqvKOR4JQSyuFjZ419ReSIR14J3zsKZJsoUe+mYRdgqm/5ELgD2sYe9I03NczvSdfHRUJiGNkJTMyFVD6lkeJSrTv7kzicBDkOiBOl/ZEYIskR3/RJnaGNfpt0kntWr1gJ3wYqhZV27R9TtD0cZwWAz5c7V+WIs+YcsFRpndIETbMZp9uqY1CQk0llch2dAe33jaCMdMdV1aWChOvutuMN5hpya43TX0AuDsh3sdFkk+m++e9coilMKVMKIobP/R4utqsBu7AU606fb/nlbQVr3yJA7rpaahWYWKYNQegZaaYQKBgQDV9GgSVNA1RZ0ECkzDeXcgbshj7TjSUpbDutNer0zzjA4XnnUWLfxajjHpRVcOK8f5QEoo+PC6iiJ7e93DzKbMKlDoi6yrMH9J0mfeG8Uv6UYnFszwYZjlTQTn0VEr1MJI1jI4jNBRBt4D9w6wpjdtSVeU+N/+S2LMHYM8TLk/kQKBgQDVLpgQKkhs29thqCEMwIs2YfcQRXfu2YeyVDDDAcsNsZwB7dnzKRjaZr2hDM9mLMInccwffvcfw999SaKW/cn+EEJ5JlaGRwV0Sg4BXY1TT0HlUasT39uUqtC8iTbeJXy4UuUEU6Ylr8dhesCYUo6D+6TEgllbKE+6M9szGyRJhwKBgBSCKv+m1ZZlp9ldS2JpYDvhop/Be3Ldq8nzPJOU5iFqSWeGLt8DZW8gtjQQC3o2RCLmQLWQtzKKTyfbBbThBxQG8l54FQML0Qe4fXBS5SHkkG7qxpY8JLXGHjRCYi97Y2wr7ABzrvw1Vk62/dd8908oepazDHWJsFy0b4H2MMOhAoGBAMWWfRymhCBZmrmmApP2NlG+dScgrCu2dT5FKDjmhZR4pTdohFULJ1zuUx5daDG0lHPI1r/uhxBrFM7kHA3Exp5z5ZiyfwnP8+834ElFs4Ke+F2a120BVF00AWpt52v45ONW+auL+Hf/t1XTV4UXJ76O08XLZxTFhTf5blas9nIrAoGAYW2tl7IGT44gE3LBs9Zs9poqDgOFr0EAIeGXgWT2S0pSf0bBkqj46fG74oC0l5LiTuFfEgwvAV2MrXBQjLAaUa0MF76MAjOx8Frm47qSFvykxW1A7vF09mMOJ00NZaHVxG6+rV1vqNmwUCAEGinvqlRaJbxJir9mFTznhanUETY=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtsu7NDlq7fo8vl0OHDiZkv7WyU/sXYvklQic40yrMiPFJUFoub1fki4kSJlKMzDAZXfC+NzvlzfU+dXV4jYbdNmisxgVcmwqgbeKv9Mjvv2Z91odU+iLIv5tVERbVGrhPph3s3cEA2BnwXvsglWECVxQTyEP/eC9Gvx6VKKDDVd+v4c47X79dAn3BCott/aPiWgyie/3wH/u8Bue/rSX0WtLhhmLaLPOT7M549PyHspRpM5VpV+NJgvncXA7Z0Y+QR/0kbFdNCOmE0zmA2eYbCK2ALBLdo3axCqedj3HUjH4BY1EIvSqQkVgQeNwUD3ftaBnRbUx5eUpW3zGQCmN1QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:8088/alipay/notify_url.jsp";

//    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://kyd.free.idcfengye.com/notify";


    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // public static String return_url = "http://kyd.free.idcfengye.com/returnUrl";
    public static String return_url = "http://127.0.0.1:8088/shoppingwebsite/alipay/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
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