package com.example.ssendhil.myGallery;
import java.util.Objects;
public class MyIcon {
    private Integer myId;
    private Integer myRating = 0;
    public MyIcon(Integer i){
        myId = i;
    }
    public Integer getMyId(){
        return myId;
    }
    public void setMyId(Integer i){
        myId = i;
    }
    public Integer getMyRating(){
        return myRating;
    }
    public void setMyRating(Integer r){
        myRating = r;
    }
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (! (o instanceof MyIcon)) return false;
        MyIcon icon = (MyIcon) o;
        return icon.getMyId() == this.myId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(myId,myRating);
    }
}