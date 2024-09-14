package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CLIPrinter {

    public ReentrantLock lock = new ReentrantLock();
    private static final Logger logger = LoggerFactory.getLogger(CLIPrinter.class);

    public boolean printResult(List<String> results, List<Character> optionsList, String machineName) {
        try{
            lock.lock();
            String threadName = Thread.currentThread().getName();
            if(!optionsList.isEmpty() && optionsList.contains('c'))
                logger.info( machineName + " No of lines found are : " + results.get(0));
            else
                logger.info( machineName + " No of lines found are : " + results.size());
            return true;
        }catch (Exception e){
            return false;
        }finally {
            lock.unlock();
        }
    }
}
