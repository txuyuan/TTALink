package server;

import Data.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class DataProc {

    public static void sort(ArrayList<String> dataArray){
        List<Coordinate> data = new ArrayList<>();
        for(String i : dataArray){
            String[] split = i.split("~", 3);
            Coordinate co = new Coordinate(Long.parseLong(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            data.add(co);
        }

        //TODO:Save data to MongoDB
    }

}
