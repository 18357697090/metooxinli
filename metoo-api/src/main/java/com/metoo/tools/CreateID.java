package com.metoo.tools;

import java.io.Serializable;

public class CreateID implements Serializable {

    public static int create(){
        return (int) Math.round(Math.random() * 1000000000);
    }

}
