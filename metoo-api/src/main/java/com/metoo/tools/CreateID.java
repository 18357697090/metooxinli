package com.metoo.tools;

public class CreateID {

    public static int create(){
        return (int) Math.round(Math.random() * 1000000000);
    }

}
