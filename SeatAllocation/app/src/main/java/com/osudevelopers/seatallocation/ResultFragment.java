package com.osudevelopers.seatallocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_result, container, false);
        ViewGroup vg = (ViewGroup) root.findViewById(R.id.resultContainer);

        CarCar car=(CarCar) getArguments().getSerializable("car");
        int pageNum=getArguments().getInt("num");
        for(int i=0;i<car.getMaxColumn();i++){
            getActivity().getLayoutInflater().inflate(R.layout.result_row_3colomns, vg);
            //TextView sss
            //sss.setText(car.getCoordinate());
        }

        for(int i=0;i<car.getLoadPeople();i++){
            CarCoordinate coordinate=car.getCoordinate(i);
            LinearLayout tr=(LinearLayout)vg.getChildAt(coordinate.getColumn());
            TextView tv=null;
            switch (coordinate.getRow()){
                case 0:
                    tv =(TextView)tr.findViewById(R.id.name1);
                    break;
                case 1:
                    tv =(TextView)tr.findViewById(R.id.name2);
                    break;
                case 2:
                    tv =(TextView)tr.findViewById(R.id.name3);
                    break;
            }
            CarPeople cp=car.getSeatMap().get(i);
            if(cp==null){
                tv.setText("");

            }else{
                tv.setText(cp.name);

            }

        }


        return root;
    }
}
