package com.example.ssendhil.myGallery;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.ArrayList;
public class MyDiffUtilCallBack extends DiffUtil.Callback {
    private final ArrayList<MyIcon> oldIcons;
    private final ArrayList<MyIcon> newIcons;
    public MyDiffUtilCallBack(ArrayList<MyIcon> oldL, ArrayList<MyIcon> newL){
        oldIcons = oldL;
        newIcons = newL;
    }
    @Override
    public int getOldListSize(){
        return oldIcons.size();
    }
    @Override
    public int getNewListSize(){
        return newIcons.size();
    }
    @Override
    public boolean areItemsTheSame(int oldPos, int newPos){
        Log.d("CAME HERE", "DIFFuTIL ARE ITEMS THE SAME");
        return ((oldIcons.get(oldPos).getMyId() == newIcons.get(newPos).getMyId())
                && (oldIcons.get(oldPos).getMyRating() == newIcons.get(newPos).getMyRating()));
        // compare ratings also??
    }
    @Override
    public boolean areContentsTheSame(int oldPos, int newPos) {
        Log.d("CAME HERE", "DIFFuTIL ARE CONTENTS THE SAME");
        return  oldIcons.get(oldPos).getMyRating() == newIcons.get(newPos).getMyRating();
    }
    @Nullable
    @Override
    public Object getChangePayload(int oldPos, int newPos) {
        return super.getChangePayload(oldPos,newPos);
    }
}