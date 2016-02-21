package com.osudevelopers.seatallocation;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by chilly on 2016/01/24.
 */
public class CarAllocationLogic {
    /**
     * 呼び出しクラス
     *
     * @param context ApplicationContext
     * @param input   車のインスタンス集合
     * @param people  人のインスタンス集合
     * @return 結果
     * @throws CarException 異常時
     */
    public static ArrayList<CarCar> exec(Context context, ArrayList<CarCar> input, ArrayList<CarPeople> people) throws CarException {
        /**
         * 入力チェック
         */
        //車がnullの場合
        if (input == null) {
            throw new CarException(context.getString(R.string.error_noCarNull));
        }
        //車が無い場合
        if (input.size() == 0) {
            throw new CarException(context.getString(R.string.error_noCarZero));
        }
        //人がnullの場合
        if (people == null) {
            throw new CarException(context.getString(R.string.error_noPeopleNull));
        }
        //人が無い場合
        if (people.size() == 0) {
            throw new CarException(context.getString(R.string.error_noPeopleZero));
        }
        //車より人が少ない場合
        if (input.size() > people.size()) {
            throw new CarException(context.getString(R.string.error_carMoreThanPeople));
        }
        //同じ車が存在する場合
        HashSet<CarCar> temp = new HashSet<>();
        temp.addAll(input);
        if (input.size() != temp.size()) {
            throw new CarException(context.getString(R.string.error_sameCarExists));
        }
        //同じ人が存在する場合
        HashSet<CarPeople> temp2 = new HashSet<>();
        temp2.addAll(people);
        if (people.size() != temp2.size()) {
            throw new CarException(context.getString(R.string.error_samePeopleExists));
        }
        //ロジック実行
        allocateEquality(context, input, people);
        return input;
    }

    /**
     * メインロジック
     * @param context context
     * @param input 車集合
     * @param people 人集合
     * @throws CarException
     */
    private static void allocateEquality(Context context, ArrayList<CarCar> input, ArrayList<CarPeople> people) throws CarException {
        //ドライバ候補
        HashSet<CarPeople> drivers = new HashSet<>();
        //乗客候補
        HashSet<CarPeople> passengers = new HashSet<>();

        //ドライバか乗客を振り分ける
        for (CarPeople p : people) {
            if (p.isDriver) {
                drivers.add(p);
            } else {
                passengers.add(p);
            }
        }

        //ドライバが足りない場合例外
        if (drivers.size() < input.size()) {
            throw new CarException(context.getString(R.string.error_driverIsShortage));
        }

        //乗り合い可能な席数
        int maxpassengers = 0;
        for (CarCar car : input) {
            maxpassengers = maxpassengers + car.getLoadPeople();
        }

        // 座席が足りない場合例外
        if (people.size() > maxpassengers) {
            throw new CarException(context.getString(R.string.error_peopleCantRide));
        }
        /**
         * ドライバ選定手順
         */

        for (CarCar car : input) {
            int rand = getRandom(drivers.size());

            // 初期化
            car.refreshPeople();
            int i = 0;
            CarPeople p = null;

            for (CarPeople pp : drivers) {
                if (i == rand) {
                    //ドライバの決定
                    car.addDriver(pp);
                    p = pp;
                    break;
                }
                i++;
            }
            //選ばれたらドライバ候補からはずす
            drivers.remove(p);
        }

        //選ばれなかったドライバは全員乗客として登録
        passengers.addAll(drivers);
        int allPassengersNum = passengers.size();
/**
 * 振り分け処理
 */
        for (CarCar car : input) {
            if (car.getDriver() == null) {
                throw new CarException(context.getString(R.string.error_noDriver));
            }
            //均等になるように車に割り付ける
            int passengersNum = (int) (((double) allPassengersNum) * ((double) car.getLoadPeople() / (double) maxpassengers));
            for (int j = 0; j < passengersNum; j++) {
                int rand = getRandom(passengers.size());

                int i = 0;
                CarPeople p = null;
                for (CarPeople pp : passengers) {
                    if (i == rand) {
                        car.addPassenger(car.getNextIndex(), pp);
                        p = pp;
                        break;
                    }
                    i++;
                }
                passengers.remove(p);
            }
        }

        /**
         * 乱数の関係で漏れた人を救う
         */
        while (passengers.size() > 0) {
            Iterator<CarCar> it = input.iterator();
            CarCar car = it.next();
            while (it.hasNext() && car.getNextIndex() == -1) {
                car = it.next();
            }
            CarPeople cp = passengers.iterator().next();
            car.addPassenger(car.getNextIndex(), cp);
            passengers.remove(cp);
        }
    }

    /**
     * 乱数を生成。最大はsize
     *
     * @param size 最大値
     * @return 乱数
     */
    private static int getRandom(int size) {
        //TODO 検証：乱数の妥当性
        return (int) (Math.random() * size);
    }

}
