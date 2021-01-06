package com.loongya.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

public class ConstantUtil {

    public static final double DISTANCELIMIT = 10000; // 10公里以内
    public static final String DISTANCE_LIMIT = "DISTANCE_LIMIT"; // 距离限制key
    public static final String HEAD_IMG_DEFAULT = ""; // 默认头像

    /**
     * 0表示正常，1表示被封
     *
     * @author loongya
     */
    @Getter
    public enum TjUserState{
        COMM(0, "表示正常"),
        FREEZE(1, "表示被封");

        private Integer code;
        private String msg;

        TjUserState(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
    /**
     * 是否
     *
     * @author loongya
     */
    @Getter
    public enum YesOrNo{
        YES(0, "是"),
        NO(1, "否");

        private Integer code;
        private String msg;

        YesOrNo(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
    /**
     * 等级： 1 青铜 2 白银 3 黄金 4 铂金 5 砖石  6 星耀  7 王者
     *
     * @author loongya
     */
    @Getter
    public enum TjUserInfoLevel{
        ONE(1, "青铜"),
        TWO(2, "白银"),
        THREE(3, "黄金"),
        FOUR(4, "铂金"),
        FIVE(5, "砖石"),
        SIX(6, "星耀"),
        SEVEN(7, "王者");

        private Integer code;
        private String msg;

        TjUserInfoLevel(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
    /**
     * 周几数字表示
     *
     * @author loongya
     */
    @Getter
    public enum PrWeekNum{
        MONDAY(1, "周一"),
        TUESDAY(2 ,"周二"),
        WEDNESDAY(3 ,"周三"),
        THURSDAY(4 ,"周四"),
        FRIDAY(5 ,"周五"),
        SATURDAY(6 ,"周六"),
        SUNDAY(7 ,"周日"),
        ;

        private Integer code;
        private String msg;

        PrWeekNum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static Integer getByMsg(String msg){
            for(PrWeekNum e: PrWeekNum.values()){
                if(e.getMsg().equals(msg)){
                    return e.getCode();
                }
            }
            return null;
        }
    }

    /**
     * 1: 申请 2: 同意 3: 拒绝 4: 删除
     *
     * @author loongya
     */
    @Getter
    public enum LeUserWithdrawStatus{
        APPLY(1, "申请"),
        AGREE(2, "同意"),
        REFUST(3, "拒绝"),
        DELETE(4, "删除")
        ;

        private Integer code;
        private String msg;

        LeUserWithdrawStatus(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 1: 收入 2： 支出
     *
     * @author loongya
     */
    @Getter
    public enum LeUserAccountBalanceDailyType{
        INCOMMING(1, "收入"),
        OUTMONENY(2, "支出")
        ;

        private Integer code;
        private String msg;

        LeUserAccountBalanceDailyType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 1: 微信 2： 支付宝。 3： 银行卡 提现类型
     *
     * @author loongya
     */
    @Getter
    public enum LeUserWithdrawType{
        APPLY(1, "微信"),
        AGREE(2, "支付宝"),
        REFUST(3, "银行卡")
        ;

        private Integer code;
        private String msg;

        LeUserWithdrawType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**

     * 订单状态：1：1 未支付，2 已支付 3： 已续课  4：已完成。5：已取消。
     * @author loongya
     */
    @Getter
    public enum OrOrderStatus{
        NO_PAYED(1, "未支付"),
        YES_PAYED(2, "已支付"),
        YES_CONTINUE(3, "已续课"),
        YES_COMPILE(4, "已完成"),
        YES_CANCAL(4, "已取消"),
        ;
        static final Map<Integer, OrOrderStatus> map = new HashMap<>();

        static {
            EnumSet.allOf(OrOrderStatus.class).forEach(e -> {
                map.put(e.getCode(), e);
            });
        }

        private Integer code;
        private String msg;

        OrOrderStatus(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code) {
            if (OU.isNotBlack(map)) {
                return OU.isBlack(map.get(code)) ? "" : map.get(code).getMsg();
            }
            return "";
        }
    }

    /**

     * 支付方式（1： 微信 2：支付宝 3： 余额支付)
     * @author loongya
     */
    @Getter
    public enum OrOrderPayType{
        WCHAT(1, "微信"),
        ALIPAY(2, "支付宝"),
        BALANCE(3, "余额支付")
        ;
        static final Map<Integer, OrOrderPayType> map = new HashMap<>();

        static {
            EnumSet.allOf(OrOrderPayType.class).forEach(e -> {
                map.put(e.getCode(), e);
            });
        }

        private Integer code;
        private String msg;

        OrOrderPayType(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code) {
            if (OU.isNotBlack(map)) {
                return OU.isBlack(map.get(code)) ? "" : map.get(code).getMsg();
            }
            return "";
        }
    }
    /**

     * 等级（1： 初级。2： 高级。3：精英）
     * @author loongya
     */
    @Getter
    public enum OrTrustOrderLevel{
        FIRST_TEACH(1, "初级老师"),
        MIDD_TEACH(2, "高级老师"),
        HEIGH_TEACH(3, "精英老师")
        ;
        static final Map<Integer, OrTrustOrderLevel> map = new HashMap<>();

        static {
            EnumSet.allOf(OrTrustOrderLevel.class).forEach(e -> {
                map.put(e.getCode(), e);
            });
        }

        private Integer code;
        private String msg;

        OrTrustOrderLevel(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code) {
            if (OU.isNotBlack(map)) {
                return OU.isBlack(map.get(code)) ? "" : map.get(code).getMsg();
            }
            return "";
        }
    }

}