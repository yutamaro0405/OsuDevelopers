package com.osudevelopers.seatallocation;

/**
 * Created by chilly on 2016/01/10.
 */
public class Car_Sedan5 extends CarCar{

    Car_Sedan5() throws CarException {
        super("5人乗りセダン",5,3,2);
        addCoordinate(0, 2, 0);
        addCoordinate(1,0,1);
        addCoordinate(2,1,0);
        addCoordinate(3,1,1);
        addCoordinate(40,1,2);
    }

}
