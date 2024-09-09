package org.example;

import org.unix4j.Unix4j;

import java.io.File;
import java.util.List;

public class GrepExecutor {

    public static List<String> executeGrep(String request){
        System.out.println("Executing Grep Request: " + request);
        String[] command = request.split(" ");
        command[2] = command[2].replace("\"", "");
        System.out.println(command[2]);
        System.out.println("Executing Grep Command: " + command.length);
        if(!(command.length ==3 || command.length ==4)){
            //TODO Later Throw error for now adding sout
            System.out.println("Invalid command");
        }
        String parameter = "";
        String pattern = "";
        String filePath  = "";

        if(command.length == 3 ){
            pattern = command[1];
            filePath = command[2];
        } else if(command.length == 4){
            parameter = command[1];
            pattern = command[2];
            filePath = command[3];
        }
        //TODO For now not utilizing option Add later .
        File file1 = new File(filePath);
        List<String> info1 = Unix4j.grep(pattern, file1).toStringList();
        System.out.println(info1.size());
        return info1;

       // System.out.println(info1);
    }

//    public static void main(String[] args) {
//        String request = "grep -n "INFO" C:\\Users\\saura\\Documents\\Distributed_Systems\\Plain_Java\\log1.txt";
//        String[] command = request.split(" ");
//        System.out.println("Executing Grep Command: " + command.length);
//        if(!(command.length ==3 || command.length ==4)){
//            //TODO Later Throw error for now adding sout
//            System.out.println("Invalid command");
//        }
//        String parameter = "";
//        String pattern = "";
//        String filePath  = "";
//
//        if(command.length == 3 ){
//            pattern = command[1];
//            filePath = command[2];
//        } else if(command.length == 4){
//            parameter = command[1];
//            pattern = command[2];
//            filePath = command[3];
//        }
//        //TODO For now not utilizing option Add later .
//        File file1 = new File(filePath);
//        List<String> info1 = Unix4j.grep(pattern, file1).toStringList();
//        System.out.println(info1);
//    }
}


