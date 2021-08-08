package me.xuyuan.TTA;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ReadIn extends Thread{
    private Scanner in;

    public ReadIn(Scanner in){
        this.in = in;
    }

    public void run(){
        MainData data = Main.data;
        Boolean readEn = true;
        while(readEn){
            System.out.print("> ");
            String line = in.nextLine();
            switch (line){
                case "exit": {
                    System.out.println("Exiting...");
                    readEn = false;
                    List<Boolean> connections = data.getConnections();
                    TimeUnit time = TimeUnit.SECONDS;
                    if(data.getConnections()!=null) while(data.getConnections().contains(true)) {
                        try {
                            time.sleep(Long.parseLong("1"));
                        }catch(InterruptedException e){e.printStackTrace();}
                    }
                    System.exit(0);
                }
                //TODO: Graceful termination, allow existing transfers to complete
            }
        }
    }

}
