package com.osudevelopers.seatallocation;

/**
 * 5人乗りセダンオブジェクトクラス
 * Created by chilly on 2016/01/10.
 */
public class Car_Sedan5 extends CarCar{
    /**
     * 5人乗りセダンオブジェクトクラス
     *
     *   1       0
     *   3   4   2
     *
     * @throws CarException
     */
    Car_Sedan5() throws CarException {
        super("5人乗りセダン",5,3,2);
        //indexは着席順
        addCoordinate(0, 2, 0);
        addCoordinate(1, 0, 0);
        addCoordinate(2, 2, 1);
        addCoordinate(3, 0, 1);
        addCoordinate(4, 1, 1);
    }

}
