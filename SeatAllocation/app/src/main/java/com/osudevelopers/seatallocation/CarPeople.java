package com.osudevelopers.seatallocation;

import java.io.Serializable;

/**
 * Created by chilly on 2016/01/10.
 */
public class CarPeople  implements Serializable {
    String name="";
    Boolean isDriver=false;

    CarPeople(String name,Boolean isDriver){
        this.name=name;
        this.isDriver=isDriver;
    }
}
