package com.metoo.tools;

import java.io.Serializable;
import java.util.Date;

public class CreateOrderNumber implements Serializable {

    public static String createOrderNumber(Integer uid){
        Integer x = (int) new Date().getTime();
        return "a";
    }
}
