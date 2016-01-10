package com.osudevelopers.seatallocation;

/**
 * 7人乗りWishオブジェクトクラス
 * Created by chilly on 2016/01/10.
 */
public class Car_Wish7 extends CarCar{
    /**
     * 7人乗りWishコンストラクタ
     *
     *   1       0
     *   4   3   2
     *   6       5
     *
     * @throws CarException
     */
    Car_Wish7() throws CarException {
        super("7人乗りウィッシュ",7,3,3);
        addCoordinate(0, 2, 0);
        addCoordinate(1, 0, 0);
        addCoordinate(2, 2, 1);
        addCoordinate(3, 1, 1);
        addCoordinate(4, 0, 1);
        addCoordinate(5, 2, 2);
        addCoordinate(6, 0, 2);
    }
}
