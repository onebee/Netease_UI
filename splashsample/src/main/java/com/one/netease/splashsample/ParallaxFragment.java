package com.one.netease.splashsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 *
 *
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class ParallaxFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater original, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Bundle args = getArguments();

        int layoutId = args.getInt("layoutId");

        ParallaxLayoutInflater inflater = new ParallaxLayoutInflater(original,getActivity(),this);
        return inflater.inflate(layoutId, null);


    }
}
