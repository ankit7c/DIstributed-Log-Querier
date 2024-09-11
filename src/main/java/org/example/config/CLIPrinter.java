package org.example.config;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CLIPrinter {

    public ReentrantLock lock = new ReentrantLock();

    public boolean printResult(List<String> results) {
        try{
            lock.lock();
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " No of lines found are : " + results.size());
//            for (String result : results) {
//                System.out.println(threadName + " " + result);
//            };
            return true;
        }catch (Exception e){
            return false;
        }finally {
            lock.unlock();
        }
    }
}
