package org.example;

import org.example.config.AppConfig;
import org.example.entities.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.unix4j.Unix4j;
//import org.unix4j.unix.Grep;
//import org.unix4j.unix.grep.GrepOptionSet_Fcilnvx;
import org.unix4j.Unix4j;
import org.unix4j.io.Output;
import org.unix4j.io.StreamOutput;
import org.unix4j.unix.Cut;
import org.unix4j.unix.Grep;
import org.unix4j.unix.Ls;
import org.unix4j.unix.Sort;
import org.unix4j.unix.grep.GrepOption;
import org.unix4j.unix.grep.GrepOptionSet_Fcilnvx;
import org.unix4j.util.Range;
import org.unix4j.variable.Arg;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GrepExecutor {

    public List<String> executeGrep(String request){
        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();
//        String[] command = request.split(" ");
//        //command[command.length - 1] = command[command.length - 1].replace("\"", "");
//        //System.out.println("Executing Grep Command with length: " + command.length);
//
//        if(!command[0].equals("grep")){
//            throw new IllegalArgumentException("First parameter should be grep");
//        }
//        List<Character>optionsList = new ArrayList<>();
//        if(command[1].startsWith("-")){
//            //handling case of -nci
//            String options = command[1];
//            for(int i=1;i<options.length();i++){
//                optionsList.add(options.charAt(i));
//            }
//            //handling case of -n -c -i
//            int k = 2;
//            while(k<command.length){
//                String option = command[k];
//                if(!option.startsWith("-")){
//                    break;
//                } else {
//                    optionsList.add(option.charAt(1));
//                }
//                k++;
//            }
//        }
//        String pattern = command[command.length-1].replace("\"", "");
        Command command = CommandProcessor.processCommand(request);
        GrepOptionSet_Fcilnvx grepOptions = convertGrepOptions(command.getOptionsList());

        List<String> grepOutput = new ArrayList<>();
        try {
            //TODO Get filetPath from properties file
            File file = new File(properties.getProperty("file.path"));
//            System.out.println(file.getAbsolutePath());

            if (grepOptions != null) {
                grepOutput = Unix4j.grep(grepOptions, command.getPattern(), file).toStringList();
            } else {
                grepOutput = Unix4j.grep(command.getPattern(), file).toStringList();
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to execute grep", e);
        }
        return grepOutput;
    }

    private static GrepOptionSet_Fcilnvx convertGrepOptions(List<Character>grepOptionsList) {
        GrepOptionSet_Fcilnvx grepOptions = null;
        if (grepOptionsList == null || grepOptionsList.isEmpty()) {
            return grepOptions;
        }
        for (char option : grepOptionsList) {
            if (option == 'n') {
                grepOptions = (grepOptions == null) ? Grep.Options.n : grepOptions.n;
            } else if (option == 'c') {
                grepOptions = (grepOptions == null) ? Grep.Options.c : grepOptions.c;
            } else if (option == 'l') {
                grepOptions = (grepOptions == null) ? Grep.Options.l : grepOptions.l;
            } else if (option == 'x') {
                grepOptions = (grepOptions == null) ? Grep.Options.x : grepOptions.x;
            } else if (option == 'i') {
                grepOptions = (grepOptions == null) ? Grep.Options.i : grepOptions.i;
            } else if (option == 'v') {
                grepOptions = (grepOptions == null) ? Grep.Options.v : grepOptions.v;
            } else if (option == 'F') {
                grepOptions = (grepOptions == null) ? Grep.Options.F : grepOptions.F;
            }
        }
        return grepOptions;
    }
}


