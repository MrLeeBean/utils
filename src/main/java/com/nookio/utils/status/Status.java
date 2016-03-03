package com.nookio.utils.status;

/**
 * Created by nookio on 15/11/23.
 */
public class Status {

    public enum OrderStatus{
        /** 已取消 */
        CANCEL {
            public Integer id = 1;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "已取消";
            }

            @Override
            public OrderStatus getStatus() {
                return CANCEL;
            }

            @Override
            public OrderStatus getNext() {
                return WAITCONFIRM;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return false;
            }

            @Override
            public OrderStatus getBefore() {
                return null;
            }
        },
        /** 待审核 */
        WAITCONFIRM {
            public Integer id = 2;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "待审核";
            }

            @Override
            public OrderStatus getStatus() {
                return WAITCONFIRM;
            }

            @Override
            public OrderStatus getNext() {
                return WAITPAYMENT;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return CANCEL;
            }
        },
        /** 等待付款 */
        WAITPAYMENT {
            public Integer id = 3;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "等待付款";
            }

            @Override
            public OrderStatus getStatus() {
                return WAITPAYMENT;
            }

            @Override
            public OrderStatus getNext() {
                return ADMEASUREPRODUCT;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return WAITCONFIRM;
            }
        },
        /** 正在配货 */
        ADMEASUREPRODUCT {
            public Integer id = 4;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "正在配货";
            }

            @Override
            public OrderStatus getStatus() {
                return ADMEASUREPRODUCT;
            }

            @Override
            public OrderStatus getNext() {
                return WAITDELIVER;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return WAITPAYMENT;
            }
        },
        /** 等待发货 */
        WAITDELIVER {
            public Integer id = 5;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "等待发货";
            }

            @Override
            public OrderStatus getStatus() {
                return WAITDELIVER;
            }

            @Override
            public OrderStatus getNext() {
                return DELIVERED;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return ADMEASUREPRODUCT;
            }
        },
        /** 已发货 */
        DELIVERED {
            public Integer id = 6;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "已发货";
            }

            @Override
            public OrderStatus getStatus() {
                return DELIVERED;
            }

            @Override
            public OrderStatus getNext() {
                return RECEIVED;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return WAITDELIVER;
            }
        },
        /** 已收货 */
        RECEIVED {
            public Integer id = 7;

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName(){
                return "已收货";
            }

            @Override
            public OrderStatus getStatus() {
                return RECEIVED;
            }

            @Override
            public OrderStatus getNext() {
                return null;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasBefore() {
                return true;
            }

            @Override
            public OrderStatus getBefore() {
                return DELIVERED;
            }
        };

        public abstract Integer getId();

        public abstract String getName();

        public abstract OrderStatus getStatus();

        public abstract OrderStatus getNext();

        public abstract boolean hasNext();

        public abstract boolean hasBefore();

        public abstract OrderStatus getBefore();

        public static int isBefore(OrderStatus now, OrderStatus target){
            if (now.getId() < target.getId()){
                return 1;
            }else if (now.getId() == target.getId()){
                return 0;
            }else {
                return -1;
            }
        }

    }

    public enum CardStatus{

    }
}
