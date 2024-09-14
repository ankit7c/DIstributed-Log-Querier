package org.example;

import org.unix4j.Unix4j;
import org.unix4j.unix.Grep;

import java.io.File;
import java.util.List;

public class Unix4jnew {
        public static void main(String[] args) {
            File f2 = new File("log1.txt");
            File f3 = new File("log2.txt");
            File f4 = new File("vm1.log");

            List<String> info2 = Unix4j.grep("INFO", f2).toStringList();
            List<String> info3 = Unix4j.grep(Grep.Options.i, "INFO", f2).toStringList();
            List<String> info4 = Unix4j.grep(Grep.Options.v, "INFO", f2).toStringList();
            List<String> info5 = Unix4j.grep(Grep.Options.F, "info.*dog", f2).toStringList();
            List<String> info6 = Unix4j.grep(Grep.Options.n, "INFO", f2).toStringList();
            List<String> info7 = Unix4j.grep(Grep.Options.c, "INFO", f2).toStringList();
            List<String> info8 = Unix4j.grep(Grep.Options.l, "INFO", f2, f3).toStringList();
            List<String> info9 = Unix4j.grep(Grep.Options.x, "info", f2).toStringList();

            System.out.println(info3);
            System.out.println(info4);
            System.out.println(info5);
            System.out.println(info6);
            System.out.println(info7);
            System.out.println(info8);
            System.out.println(info9);

            System.out.println(info3.size());
            //   System.out.println("Start at "+Start +"END ********************  "+LocalDateTime.now());

            System.out.println("_");
            // System.out.println(info2);
        }
}
