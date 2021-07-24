package me.xuyuan.TTA;

import java.util.List;

public class MainData {

    int cInd;
    List<Boolean> connections;
    Boolean allowNew = true;

    public MainData(){

    }

    public void setStatus(int cInd, Boolean status){
        connections.set(cInd, status);
    }

    public Boolean getAllowed(){
        return allowNew;
    }

    public List<Boolean> getConnections(){
        allowNew = false;
        return connections;
    }

    public void updateInd(int cInd){this.cInd = cInd;}

}
