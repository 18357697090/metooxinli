package com.metoo.order.alipay.config;

public class AlipayConfig {
    // 1.商户appid
    public static String APPID = "2021002110628087";

    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC44WbOk2EscZf/2j4hT3/gYs0AyCsnRvZo8C6hK225x7xn7ytiCfQdccSo6BLW+LnbDTHPv+M8XNwVgpzX5HgXNrmw17Ll4jU3luYlf8RDOEmg0Z0ogK5lb0J22R2s5aXjKHH+0wXJDjZLNKepS2qudYgHP8O9Bfa4aBSTVcJVnfxDk80as1uEyMf333oFcaDZjNLXRI6HyRANsanqCYRwh/p0eQ4tUGRfBXReOnHUMIWcNBm1s6RH27zyzJZ5AcGWsGhRiB9U/9qNarbcwnEPwWfLv1Y3Rsv0sh5PwzwkVonE3Pe1NvdX100hAqUTyaZsydA2rhRGDYE7XfwWOT/hAgMBAAECggEAGZnEtag81X2pUZlz7wLmLzkfMBOHvjmaV2+i1ViBKi+ZTD8XH81HC2i8lbMFINVyTv3lvuHQYoRtSWE7vL17T6OZVP6ZXDHVsqvnlJu7np9Fusor2/btr0gXyQNJk659KRZpqqKn825PYFybNtslm6DUxEQ1I5CW2HQ5HSnL7jTkxy9IklYKqszlIIJ7YolWHsEndzl+Ab0WBsA7pcAfDoW+1WQA8hENdINshuAk78t96ojKA2wjr6zYq3EBwLb0MG6coNc/EZqkBtfxntC3Xx5efas8FA8ExYtQRDa5VuPcoNY8zLfvX/z8csg8zAP8ShXLWbk/NMaFDX+ojGSKQQKBgQDtIZAf749/Yn6Nzgx5uKycYDeXUtB8mFKQNrmQLXAhhbIDJ6xCKm3LTgrN5W8xPN/Pqx/zarlCvdufEYbCFUL5sUapl/uox4s03rYoodB00WQsExK7GlWCGRgkAHa+YJW+fOuu5fTv5BV8sFV5IlsUHtsgAAbJCTQu/oPjwd9vSQKBgQDHl3n3Fnk4qkV9daJCI9h92zYyK16RaDJEfeekqxZxpxUd4vaJjEsDp09rFSrxm0fccl7cWaSCNfMhdFZa1+Q3ppivBxJRr+hQ5k6JlR8AkHX0qTHuNaa1LbdvBnZlEGukwWp0MmSjrYUJ9zAEwM4bp2HFL8ghr0YZ9aGlkoWT2QKBgBhnwOaYiENLBs1fbIdGi3VYrcAERPLd1nmDOFvvYUAB+405kxAIP96RvKjuOSBvLHW6HgK2dDS7R5Q6gI0PFSKPrT8dJkh7qQca+NJ7vPGY719d4yqgjjZikMp7xbzYQfEl+De68ReTeOapW1KpR4htmI3Vkt0z9kvz4+0X8gAJAoGAVYOZ9vqC/3s8gK9Y0Luo8utsAfzA7M3W06+aAtxmplUu3/oxJOed8DfL+BY+qvubw2ChMgmaSUuyGsBMi2l6Vzmiq0L9X1Bd2eOC/MGOYeCDGUswoF4OSgwHTqNXMJ/jbtiLsrMwmUwr9wQYZ8DvzbteUWzSc1Q0VcT++ACaCKkCgYAvW+/BsO4oVleVLqCNi7KJjJK+mGeuv+zSc5xNNqio4Z90wvoW28SDmtxdpDHQgVCWadYxXS098U0vRoeYKEVe3xyz7KqtiPyKohLGiSQhotpV5zuwSJaUtzk4Rg0k7BzrzI9A1ScORE4zL0NsYd6JZ0PscWdghmuxpmEdxF5e9Q==";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnM6Ebzz9wEQZTb9geA8xX/e7uHuGA2b45LhcShF656+hIAZxwyAlKRNYLiTJcXMZoDCNpBs4+MUu001h3sdV77W9oxD/HDeRf+ZlicQa+DgJNu40zyBsC3MCl4w9J7qb7Zu9mysngLG0/Sd2TzOTrAosMKy1ekHsSUyRL7nQNZK5Uhrj3K7xLHipssK/GlnHFOHZ/SmI+kGsA9pmrO8HJ8cEVcb0WahLtcRBlSKv9VJQX+Y1zieTRXr5yD87e+4VWRlt6i1doJOGzctxFRAR0cvqyQgQwSJxuh0b//+9FB9Hg+kOfVHjMA5z05PLazTn692nE3QIWQylFG14u+I+VQIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.metooxinli.com:8088/alipayorder/notify";

    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://www.metooxinli.com/";

    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    public static String CHARSET = "UTF-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGNTYPE = "RSA2";

    //10.签约产品代码
    public static String QUICK_MSECURITY_PAY = "QUICK_MSECURITY_PAY";
}
