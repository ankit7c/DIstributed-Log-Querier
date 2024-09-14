package org.example;

import org.example.entities.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {

    public static Command processCommand(String request) {

        String[] command = request.split(" ");
        if(!command[0].equals("grep")){
            throw new IllegalArgumentException("First parameter should be grep");
        }
        List<Character> optionsList = new ArrayList<>();
        if(command[1].startsWith("-")){
            //handling case of -nci
            String options = command[1];
            for(int i=1;i<options.length();i++){
                optionsList.add(options.charAt(i));
            }
            //handling case of -n -c -i
            int k = 2;
            while(k<command.length){
                String option = command[k];
                if(!option.startsWith("-")){
                    break;
                } else {
                    optionsList.add(option.charAt(1));
                }
                k++;
            }
        }
        return new Command(command[0], optionsList, command[command.length-1].replace("\"", ""));
    }
}
