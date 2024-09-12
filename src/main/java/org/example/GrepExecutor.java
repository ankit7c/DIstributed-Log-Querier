package org.example;

import org.unix4j.Unix4j;
import org.unix4j.unix.Grep;
import org.unix4j.unix.grep.GrepOptionSet_Fcilnvx;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrepExecutor {

    public static List<String> executeGrep(String request){
        String[] command = request.split(" ");
        //command[command.length - 1] = command[command.length - 1].replace("\"", "");
        //System.out.println("Executing Grep Command with length: " + command.length);

        if(!command[0].equals("grep")){
            throw new IllegalArgumentException("First parameter should be grep");
        }
        List<Character>optionsList = new ArrayList<>();
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
        String pattern = command[command.length-1].replace("\"", "");
        GrepOptionSet_Fcilnvx grepOptions = convertGrepOptions(optionsList);


        //TODO Get filetPath from properties file
        File file = new File("D:\\Distributed Systems\\logs\\vm1.log");
        List<String> grepOutput = new ArrayList<>();
        if(grepOptions!=null){
            grepOutput = Unix4j.grep(grepOptions,pattern,file).toStringList();
        } else {
            grepOutput = Unix4j.grep(pattern,file).toStringList();
        }
        return grepOutput;
    }

    private static GrepOptionSet_Fcilnvx convertGrepOptions(List<Character>grepOptionsList){
        GrepOptionSet_Fcilnvx grepOptions = null;
        if(grepOptionsList==null || grepOptionsList.isEmpty()){
            return grepOptions;
        }
        for(char option : grepOptionsList){
            if(option == 'n'){
                grepOptions = (grepOptions==null) ? Grep.Options.n : grepOptions.n;
            } else if(option == 'c'){
                grepOptions = (grepOptions==null) ? Grep.Options.c : grepOptions.c;
            } else if(option == 'l'){
                grepOptions = (grepOptions==null) ? Grep.Options.l : grepOptions.l;
            } else if(option == 'x'){
                grepOptions = (grepOptions==null) ? Grep.Options.x : grepOptions.x;
            } else if(option == 'i'){
                grepOptions = (grepOptions==null) ? Grep.Options.i : grepOptions.i;
            } else if(option == 'v'){
                grepOptions = (grepOptions==null) ? Grep.Options.v : grepOptions.v;
            } else if(option == 'F'){
                grepOptions = (grepOptions==null) ? Grep.Options.F : grepOptions.F;
            }
        return grepOptions;
    }
}
}


