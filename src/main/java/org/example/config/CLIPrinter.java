package org.example.config;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CLIPrinter {

    public ReentrantLock lock = new ReentrantLock();

    public boolean printResult(List<String> results) {
        try{
            lock.lock();
            System.out.println("No of lines found are : " + results.size());
            results.forEach(System.out::println);
            return true;
        }catch (Exception e){
            return false;
        }finally {
            lock.unlock();
        }
    }
}
