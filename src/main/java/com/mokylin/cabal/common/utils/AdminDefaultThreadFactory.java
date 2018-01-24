package com.mokylin.cabal.common.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminDefaultThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public AdminDefaultThreadFactory(String threadPrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                              Thread.currentThread().getThreadGroup();
        namePrefix = threadPrefix != null ? (threadPrefix+"-") : "pool-" +
                      poolNumber.getAndIncrement() +
                     "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                              namePrefix + threadNumber.getAndIncrement(),
                              0);
        t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
    
    public static void main(String[] args) throws Exception{
		
    	ExecutorService service = Executors.newFixedThreadPool(5);
//    	ExecutorService service = Executors.newFixedThreadPool(5, new AdminDefaultThreadFactory());
    	service.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("run");
				
			}
		});
    	
    	Thread.sleep(5000);
    	
	}
}