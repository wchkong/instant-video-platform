package com.wchkong.common.thread;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.*;

@Log4j
public class ThreadPool {
    private ExecutorService commonExec = new ThreadPoolExecutor(20, 20, 5 * 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());//公用线程池
    private ExecutorService dynamicExec = new ThreadPoolExecutor(5, 10, 5 * 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());//公用线程池
    private ScheduledExecutorService schedule = Executors.newScheduledThreadPool(10);//公用定时任务线程池

    private ScheduledExecutorService scheduleForImportantJob = Executors.newScheduledThreadPool(15);//关键定时任务线程池

    private static class SingletonHolder {
        private static final ThreadPool INSTANCE = new ThreadPool();
    }

    private ThreadPool() {
    }

    public static ThreadPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void exec(Runnable command) {
        commonExec.execute(command);
    }
    public void execDynamic(Runnable command) {
        dynamicExec.execute(command);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return commonExec.submit(callable);
    }

    /**
     * @param command
     * @param delay
     * @param unit    任务提交后，需要等待多久执行，delay是等待时间。只执行一次，没有周期性。
     */
    public void schedule(Runnable command, long delay, TimeUnit unit) {
        schedule.schedule(command, delay, unit);
    }

    /**
     * @param command
     * @param delay   关键业务(使用此方法)
     * @param unit    任务提交后，需要等待多久执行，delay是等待时间。只执行一次，没有周期性。
     */
    public void scheduleForImportantJob(Runnable command, long delay, TimeUnit unit) {
        scheduleForImportantJob.schedule(command, delay, unit);
    }

    /**
     * @param command
     * @param initialDelay
     * @param delay
     * @param unit         周期性执行，delay表示时间间隔
     */
    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        schedule.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }

    public void shutdown() {
        System.out.println("shutdown");
        commonExec.shutdownNow();
        schedule.shutdownNow();
        dynamicExec.shutdown();
        try {
            commonExec.awaitTermination(Integer.MAX_VALUE, TimeUnit.NANOSECONDS);
            schedule.awaitTermination(Integer.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            log.error(e);
        }
    }

    public static class BlockUntilFreePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // Access to the task queue is intended primarily for
            // debugging and monitoring. This queue may be in active
            // use.
            //
            // So we are a little bit off road here :) But since we have
            // full control over executor we are safe.
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                // since we cound not rethrow interrupted exception. mark thread
                // as interrupted. and check thread status later in
                // blockExecute()
                Thread.currentThread().interrupt();
            }
        }
    }
}
