package com.osudevelopers.seatallocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by chilly on 2016/01/24.
 */
public class CarAllocationLogic {
    /**
     * 呼び出しクラス
     * @param input 車のインスタンス集合
     * @param people 人のインスタンス集合
     * @return 結果
     * @throws CarException 異常時
     */
    public static ArrayList<CarCar> exec(ArrayList<CarCar> input, ArrayList<CarPeople> people) throws CarException {
        ArrayList<CarCar> output;
        /**
         * メインロジック
         */
        allocateEquality(input,people);
        return input;
    }

    private static void allocateEquality(ArrayList<CarCar> input, ArrayList<CarPeople> people) throws CarException {
        /**
         * ドライバ候補
         */
        HashSet<CarPeople> drivers=new HashSet<>();
        /**
         * 乗客候補
         */
        HashSet<CarPeople> passengers=new HashSet<>();

        /**
         * ドライバか乗客を振り分ける
         */
        for(CarPeople p:people){
            if(p.isDriver){
                drivers.add(p);
            }else{
                passengers.add(p);
            }
        }

/**
 * 乗り合い可能な席数
 */
        int maxpassengers=0;
        for(CarCar car:input){
            maxpassengers=maxpassengers+car.getLoadPeople();
            maxpassengers--;
        }

        /**
         * ドライバ選定手順
         */

        for(CarCar car:input){
            int rand=getRandom(drivers.size());

            // 初期化
            car.refreshPeople();
            int i=0;
            CarPeople p = null;

            for(CarPeople pp:drivers){
                if(i==rand){
                //ドライバの決定
                    car.addDriver(pp);
                    p=pp;
                    break;
                }
                i++;
            }
             //選ばれたらドライバ候補からはずす
            drivers.remove(p);
        }

         //選ばれなかったドライバは全員乗客として登録
        passengers.addAll(drivers);
        int allPassengersNum=passengers.size();

        for(CarCar car:input){
            if(car.getDriver()==null){
                throw new CarException("ドライバ不在");
            }
            //均等になるように車に割り付ける
            int passengersNum=(int)(((double)allPassengersNum)*((double)car.getLoadPeople()/(double)maxpassengers));
            for(int j=0;j<passengersNum;j++){
                int rand=getRandom(passengers.size());

                int i=0;
                CarPeople p = null;
                for(CarPeople pp:passengers){
                    if(i==rand){
                        car.addPassenger(car.getNextIndex(),pp);
                        p=pp;
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
        Iterator it=input.iterator();
        while(passengers.size()>0){
            CarCar car;
            if(it.hasNext()){
                car= (CarCar) it.next();
            }else{
                it=input.iterator();
                car= (CarCar) it.next();
            }
            Iterator it2=passengers.iterator();
            car.addPassenger(car.getNextIndex(), (CarPeople) it2.next());
        }
    }

    /**
     * 乱数を生成。最大はsize
     * @param size 最大値
     * @return 乱数
     */
    private static int getRandom(int size) {
        //TODO 検証：乱数の妥当性
        return (int)(Math.random()*size);
    }

}
