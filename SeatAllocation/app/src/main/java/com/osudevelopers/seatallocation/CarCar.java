package com.osudevelopers.seatallocation;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 車の基本クラス
 * Created by chilly on 2016/01/10.
 */
public class CarCar {
    /**
     * 車名
     */
    private String name="";
    /**
     * 積載可能人数
     */
    private int loadPeople=0;

    /**
     * 最大列数(横方向)
     */
    private int maxRow;
    /**
     * 最大列数(前後方向)
     */
    private int maxColumn;

    /**
     * 乗員が乗る場所
     */
    private HashMap <Integer,CarCoordinate> carLayout;
    /**
     * 座席ハッシュ
     */
    private HashSet<Integer> seatHash;

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
     * @param index 座席番号(ドライバは0、助手席は1)
     * @param row 座標(横方向)
     * @param column 座標(前後方向)
     */
    protected void addCoordinate(int index,int row,int column) throws CarException {
        //座席が車の外にある場合、Exceptionをthrow
        if(index>loadPeople||row>maxRow||column>maxColumn||row<0||column<0){
            throw new CarException("CarException:仕様外の指定");
        }
        CarCoordinate coordinate=new CarCoordinate(row,column);
        if(seatHash.contains(getCoordinateHash(coordinate))){
            throw new CarException("CarException:既に定義された座席の再定義");
        }
        carLayout.put(index,coordinate);
        seatHash.add(getCoordinateHash(coordinate));
    }

    /**
     * 座席番号から座標を取得する
     * @param index 座席番号(ドライバは0、助手席は1)
     * @return 座標
     */
    public CarCoordinate getCoordinate(int index){
       return carLayout.get(index);
   }

    /**
     * 最大の座席座標を取得する
     * @return 座標
     */
    public CarCoordinate getMaxCoordinate(){
        return new CarCoordinate(maxRow,maxColumn);
    }

    /**
     * 積載人数を取得する
     * @return 積載人数
     */
    public int getLoadPeople(){
        return loadPeople;
    }

    /**
     * 座席座標のハッシュを求める
     * @return 座席座標のハッシュ
     */
    private int getCoordinateHash(CarCoordinate cc){
        return cc.row*maxRow+cc.column;
    }
}

class CarCoordinate{
    /**
     * 座標(横方向)
     */
    int row;
    /**
     * 座標(前後方向)
     */
    int column;

    /**
     * コンストラクタ
     * @param row 座標(横方向)
     * @param column 座標(前後方向)
     */
    CarCoordinate(int row,int column){
        this.row=row;
        this.column=column;
    }

    /**
     * 座標(横方向)の取得
     * @return 座標(横方向)
     */
    public int getRow(){
        return row;
    }

    /**
     * 座標(前後方向)の取得
     * @return 座標(前後方向)
     */
    public int getColumn(){
        return column;
    }
}