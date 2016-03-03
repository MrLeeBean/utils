package com.nookio.utils.push.pushAction;

import java.util.concurrent.*;

/**
 * Created by nookio on 16/1/4.
 */
public class PushProcessor {
    private static PushProcessor instance;

    public static PushProcessor getInstance() {
        if (instance == null) {
            synchronized (PushProcessor.class) {
                if (instance == null) {
                    instance = new PushProcessor();
                }
            }
        }
        return instance;
    }

    public enum Priority {
        LOW,
        NORMAL,
        HIGH
    }

    private ExecutorService service;

    private PushProcessor() {
        int processors = Runtime.getRuntime().availableProcessors();
        service = new PushExecutorService(processors * 2);
    }

    public Future<?> submit(Runnable task) {
        return service.submit(task);
    }

    static class PushExecutorService extends ThreadPoolExecutor {
        private static final int DEFAULT_THREAD_COUNT = 3;

        PushExecutorService() {
            super(DEFAULT_THREAD_COUNT, DEFAULT_THREAD_COUNT, 0, TimeUnit.MILLISECONDS,
                    new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory());
        }

        PushExecutorService(int threads) {
            super(threads, threads, 0, TimeUnit.MILLISECONDS,
                    new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory());
        }

        @Override
        public Future<?> submit(Runnable task) {
            PushFutureTask ftask = new PushFutureTask((PushWorker) task);
            execute(ftask);
            return ftask;
        }

        private static final class PushFutureTask extends FutureTask<PushWorker>
                implements Comparable<PushFutureTask> {
            private final PushWorker worker;

            public PushFutureTask(PushWorker worker) {
                super(worker, null);
                this.worker = worker;
            }

            @Override
            public int compareTo(PushFutureTask o) {
                return 0;
            }
        }
    }
}
