package com.osudevelopers.seatallocation;

import java.util.HashMap;

/**
 * 車インタフェース
 * Created by chilly on 2016/01/10.
 */
public class CarCar {
    /**
     * 車名
     */
    String name="";
    /**
     * 積載可能人数
     */
    int loadPeople=0;

    /**
     * 最大列数(横方向)
     */
    int maxRow;
    /**
     * 最大列数(前後方向)
     */
    int maxColumn;

    /**
     * 乗員が乗る場所
     */
    HashMap <Integer,CarCoordinate> carLayout;

    /**
     * コンストラクタ
     * @param name 車名
     * @param loadPeople 積載人数
     * @param maxRow 最大列数(横方向)
     * @param maxColumn 最大列数(前後方向)
     */
    CarCar(String name,int loadPeople,int maxRow,int maxColumn){
        this.name=name;
        this.loadPeople=loadPeople;
        this.maxRow=maxRow;
        this.maxColumn=maxColumn;

        carLayout=new HashMap<>();
    }

    /**
     * 席の座標を追加する
     * @param index 座席インデックス(ドライバは0)
     * @param row 座標(横方向)
     * @param column 座標(前後方向)
     */
    protected void addCoordinate(int index,int row,int column) throws CarException {
        if(index>loadPeople||row>maxRow-1||column>maxColumn-1){
            throw new CarException("CarException:仕様外の指定");
        }
        carLayout.put(index,new CarCoordinate(row,column));
    }
}

class CarCoordinate{
    /**
     * 座標(横方向)
     */
    int row;
    /**
     * 座標(縦方向)
     */
    int column;
    CarCoordinate(int row,int column){
        this.row=row;
        this.column=column;
    }
}