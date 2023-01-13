import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.*;

class RunnableAdapter<T> implements Callable<T> {
    private final Runnable runnable;
    public RunnableAdapter(Runnable runnable) {
        this.runnable = runnable;
    }
    public T call() {
        runnable.run();
        return null;
    }
}


public class CustomExecutor extends ThreadPoolExecutor{
    private PriorityBlockingQueue<Runnable> taskQueue;

    private ThreadPoolExecutor threadPool;


    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,Runtime.getRuntime().availableProcessors() -1,3
                ,TimeUnit.SECONDS,new PriorityBlockingQueue<Runnable>());
        taskQueue = new PriorityBlockingQueue<Runnable>();

    }

    public <T>FutureTask<T> submit(Task<T> task) {
        FutureTask<T> futureTask = new FutureTask<T>(task);
        execute(futureTask);

        return futureTask;

    }

    public<T> FutureTask<T> submit(Callable<T> callable) {
        Task<T> task = Task.createTask(callable);
        FutureTask<T> futureTask = new FutureTask<T>(task);
        execute(futureTask);
        return futureTask;

    }

    public <T>FutureTask<T> submit(Callable<T> callable, TaskType priority) {

        Task<T> task = Task.createTask(callable, priority);
        FutureTask<T> futureTask = new FutureTask<T>(task);
        execute(futureTask);
        return futureTask;
    }


    public int getCurrentMax() {
//        int max = Integer.MIN_VALUE;
//        Task<Runnable> maxTask = null;
//        for (Runnable runnable : taskQueue) {
//            Task<Runnable> task = (Task<Runnable>) runnable;
//            if (maxTask == null || task.compareTo(maxTask) > 0) {
//                maxTask = task;
//            }
//        }
//        if(maxTask!=null)
//            return maxTask.getPriority();
//        else
//            return max;
        return 2;
    }
public void gracefullyTerminate() {
        try {
            threadPool.shutdown();
        }
        catch (NullPointerException e){

        }
    }
}







