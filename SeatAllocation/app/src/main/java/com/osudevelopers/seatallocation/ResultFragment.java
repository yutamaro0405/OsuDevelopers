package com.osudevelopers.seatallocation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultFragment extends Fragment {

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_result, container, false);


        ViewGroup vg = (ViewGroup) root.findViewById(R.id.resultContainer);
        getActivity().getLayoutInflater().inflate(R.layout.result_row_3colomns, vg);
        getActivity().getLayoutInflater().inflate(R.layout.result_row_3colomns, vg);


        return root;
    }
}
