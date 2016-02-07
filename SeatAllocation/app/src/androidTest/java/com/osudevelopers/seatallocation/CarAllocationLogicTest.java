package com.osudevelopers.seatallocation;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class CarAllocationLogicTest extends ApplicationTestCase<Application> {
    public CarAllocationLogicTest() {
        super(Application.class);
    }

    ArrayList<CarCar> input;
    ArrayList<CarPeople> people;

    public void testCarAllocationLogic() throws Exception {
        Car_Sedan5 c1 = new Car_Sedan5();
        Car_Sedan5 c2 = new Car_Sedan5();
        Car_Wish7 c3 = new Car_Wish7();
        Car_Wish7 c4 = new Car_Wish7();
        CarPeople p1 = new CarPeople("Asan", false);
        CarPeople p2 = new CarPeople("Bsan", true);
        CarPeople p3 = new CarPeople("Csan", false);
        CarPeople p4 = new CarPeople("Dsan", false);
        CarPeople p5 = new CarPeople("Esan", true);
        CarPeople p6 = new CarPeople("Fsan", false);
        CarPeople p7 = new CarPeople("Gsan", false);
        CarPeople p8 = new CarPeople("Hsan", true);
        CarPeople p9 = new CarPeople("Isan", false);
        CarPeople p10 = new CarPeople("Jsan", false);
        CarPeople p11 = new CarPeople("Ksan", false);
        CarPeople p12 = new CarPeople("Lsan", true);
        CarPeople p13 = new CarPeople("Msan", false);
        CarPeople p14 = new CarPeople("Nsan", true);
        CarPeople p15 = new CarPeople("Osan", false);
        CarPeople p16 = new CarPeople("Psan", true);
        CarPeople p17 = new CarPeople("Qsan", false);
        CarPeople p18 = new CarPeople("Rsan", false);
        CarPeople p19 = new CarPeople("Ssan", false);
        CarPeople p20 = new CarPeople("Tsan", false);
        CarPeople p21 = new CarPeople("Usan", true);
        ArrayList<CarPeople> driver=new ArrayList<>();
        driver.add(p2);
        driver.add(p5);
        driver.add(p8);
        driver.add(p12);
        driver.add(p14);
        driver.add(p16);
        driver.add(p21);
        ArrayList<CarPeople> all=new ArrayList<>();
        all.add(p1);
        all.add(p2);
        all.add(p3);
        all.add(p4);
        all.add(p5);
        all.add(p6);
        all.add(p7);
        all.add(p8);
        all.add(p9);
        all.add(p10);
        all.add(p11);
        all.add(p12);
        all.add(p13);
        all.add(p14);
        all.add(p15);
        all.add(p16);
        all.add(p17);
        all.add(p18);
        all.add(p19);
        all.add(p20);
        all.add(p21);

        //車も人も居ないときはException
        reflesh();
        checkException();
        //車しか居ないときもException
        reflesh();
        input.add(c1);
        checkException();
        //人しか居ないときもException
        reflesh();
        people.add(p2);
        checkException();
        //ドライバが居ないときもException
        reflesh();
        input.add(c1);
        people.add(p1);
        people.add(p3);
        people.add(p4);
        checkException();
        //人が多すぎてもException
        people.add(p2);
        people.add(p6);
        people.add(p7);
        checkException();
        //ドライバが少なくてもException
        input.add(c3);
        checkException();
        //同じ車が存在するとException
        input.add(c3);
        checkException();
        //同じ人が存在するとException
        input=new ArrayList<>();
        input.add(c1);
        input.add(c3);
        people.add(p2);
        checkException();
        for (int i = 0; i < 99999; i++) {
            reflesh();
            int carnum=(int)(Math.random()*15.0+1.0);
            if(carnum>8){
                input.add(c1);
                carnum=carnum-8;
            }
            if(carnum>4){
                input.add(c2);
                carnum=carnum-4;
            }
            if(carnum>2){
                input.add(c3);
                carnum=carnum-2;
            }
            if(carnum==1){
                input.add(c4);
            }
            carnum=0;
            for(CarCar c:input){
                carnum=carnum+c.getLoadPeople();
            }

            carnum=(int)(Math.random()*carnum+1.0);
            HashSet<CarPeople> peoples=new HashSet<>();
            while(peoples.size()==carnum){
                if(peoples.size()<input.size()){
                    peoples.add(getRandomMember(driver));
                }else{
                    peoples.add(getRandomMember(all));
                }
            }
            people.addAll(peoples);
            CarAllocationLogic.exec(input, people);
            int ps=0;
            HashSet<CarPeople> hs=new HashSet<>();
            for(CarCar c:input){
                ps=ps+c.getSeatMap().size();
                //ドライバがドライバ席に乗っているか
                assertTrue(c.getDriver().isDriver);
                //車の中身表示
                System.out.println(c.getName());
                for(int idx:c.getSeatMap().keySet()){
                    System.out.println(idx + "," + c.getSeatMap().get(idx));
                }
            }
            //車に乗っている人が人数と一致するか
            assertEquals(people.size(),ps);
            //車に乗っている人が重複していないか
            assertEquals(people.size(), hs.size());
        }

        assertTrue(true);
    }

    private CarPeople getRandomMember(ArrayList<CarPeople> driver) {
        int g=(int)(Math.random()*driver.size());
        Iterator<CarPeople> it=driver.iterator();
        int i=0;
        while(it.hasNext()){
            CarPeople cc=it.next();
            if(i==g){
                return cc;
            }
            i++;
        }
        return null;
    }

    private void checkException() {
        try {
            CarAllocationLogic.exec(input, people);
            fail();
        } catch (CarException e) {
        }

    }

    private void reflesh() {
        input = new ArrayList<>();
        people = new ArrayList<>();
    }


}

