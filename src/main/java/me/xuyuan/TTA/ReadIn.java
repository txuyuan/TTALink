package me.xuyuan.TTA;

import java.util.Scanner;

public class ReadIn extends Thread{
    private Scanner in;

    public ReadIn(Scanner in){ this.in = in; }

    public void run(){
        Boolean readEn = true;
        while(readEn){
            System.out.print("> ");
            String line = in.nextLine();
            switch (line){
                case "exit": {
                    System.out.println("Exiting...");
                    readEn = false;
                    System.exit(0);
                }
                //TODO: Graceful termination, allow existing transfers to complete
            }
        }
    }

}
