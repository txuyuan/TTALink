package TTA;

import Data.Coordinate;
import server.Server;

import java.util.Scanner;

public class Main {

    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        read(in);

        if(args[0] == "server"){
            while((new Server()).isActive())
                System.out.println("test");
            //TODO: Add loop to ensure multiple-client support
        }
    }

    //Read cmd input
    private static void read(Scanner in){
        Boolean readEn = true;
        while(readEn){
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
