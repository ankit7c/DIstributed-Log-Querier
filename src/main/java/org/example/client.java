package org.example;

import org.example.config.AppConfig;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
public class client {

    public void run_client(){

        AppConfig appConfig = new AppConfig();
        Properties properties = appConfig.readConfig();

        System.out.println("Please enter the command : ");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        System.out.println("Command to be executed is " + command);

        for (int nodes=0; nodes<1; nodes++){
            System.out.println(properties.getProperty("peer.machine.ip" + nodes));
            System.out.println(properties.getProperty("peer.machine.port" + nodes));
            client_component client = new client_component(properties.getProperty("peer.machine.ip" + nodes),
                    Integer.parseInt(properties.getProperty("peer.machine.port" + nodes)), command);
            client.start();
        }

    }


}

//class Solution {



//    public boolean compute(int[] nums) {
//
//        ArrayList<Integer> lastdigits = new ArrayList();
//        int start = 0;
//        int lastnumber = 0;
//        int nullno = -1001;
//        ArrayList<Integer> temp = new ArrayList();
//        temp.add(nums[0]);
//        lastnumber = nums[0];
//        nums[0] = nullno;
//        ArrayList<Integer> remaining = new ArrayList();
//        for(int i=1;i<nums.length;i++){
//            if(nums[i] != nullno) {
//                // System.out.println(lastnumber);
//                if (nums[i] - lastnumber == 1) {
//                    temp.add(nums[i]);
//                    System.out.println(nums[i]);
//                    lastnumber = nums[i];
//                    nums[i] = nullno;
//                    start = i;
//                } else if (nums[i] - lastnumber > 1) {
//                    remaining.addAll(temp);
//                    temp.clear();
//                    start = i;
//                } else {
//                    remaining.add(nums[i]);
//                    nums[i] = nullno;
//                }
//                if (temp.size() == 3) {
//                    System.out.println("found 1");
//                    lastdigits.add(temp.get(2));
//                    temp.clear();
//                    start = i++;
//                    if(i<nums.length) {
//                        temp.add(nums[i]);
//                        lastnumber = nums[i];
//                        nums[i] = nullno;
//                    }
//                }
//            }
//        }
//        remaining.addAll(temp);
//        System.out.println(remaining.size());
//        for (int i=0; i<remaining.size(); i++){
//            if(lastdigits.contains(remaining.get(i) - 1)){
//                lastdigits.set(lastdigits.indexOf(remaining.get(i) - 1), remaining.get(i));
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean compute2(int[] nums) {
//
//        ArrayList<ArrayList<Integer>> arr = new ArrayList();
//
//        ArrayList<Integer> lastdigits = new ArrayList();
//        int start = 0;
//        int lastnumber = 0;
//        int nullno = -1001;
//        ArrayList<Integer> temp = new ArrayList();
//        temp.add(nums[0]);
//        lastnumber = nums[0];
//        nums[0] = nullno;
//        ArrayList<Integer> remaining = new ArrayList();
//
//        for(int i=1;i<nums.length;i++){
//            if(nums[i] != nullno) {
//                if (nums[i] - lastnumber == 1) {
//                    temp.add(nums[i]);
//                    System.out.println(nums[i]);
//                    lastnumber = nums[i];
//                    nums[i] = nullno;
//                } else if (nums[i] - lastnumber > 1) {
//                    remaining.addAll(temp);
//                    temp.clear();
//                    start = i;
//                } else {
//                    remaining.add(nums[i]);
//                    nums[i] = nullno;
//                }
//                if (temp.size() == 3) {
//                    System.out.println("found 1");
//                    lastdigits.add(temp.get(2));
//                    temp.clear();
//                    start = i++;
//                    if(i<nums.length) {
//                        temp.add(nums[i]);
//                        lastnumber = nums[i];
//                        nums[i] = nullno;
//                    }
//                }
//        }
//        remaining.addAll(temp);
//        System.out.println(remaining.size());
//        for (int i=0; i<remaining.size(); i++){
//            if(lastdigits.contains(remaining.get(i) - 1)){
//                lastdigits.set(lastdigits.indexOf(remaining.get(i) - 1), remaining.get(i));
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
//}


