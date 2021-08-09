package me.xuyuan.TTA;

import java.util.Scanner;

public class ReadIn extends Thread{
    private Scanner in;

    public ReadIn(Scanner in){
        this.in = in;
    }

    public void run(){
        Boolean readEn = true;
        while(true){
            String line = in.nextLine();
            switch (line){
                case "exit": {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }

    }

}
