import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;


public class Task <T> implements Callable <T> ,ThreadFactory, Comparable<Task<T>> {

    private Callable <T> callable;
    private TaskType priority;

    private Task (Callable <T> callable, TaskType priority){
        this.callable = callable;
        this.priority= priority;
    }

    private Task(Callable <T> callable){
        this.callable = callable;
        this.priority = TaskType.OTHER;
    }

    public static Task createTask(Callable callable, TaskType priority){
        return new Task(callable,priority);
    }
    public static Task createTask(Callable callable){

        return new Task(callable);
    }

    @Override
    public T call() throws Exception {
        try {
            return (T) callable.call();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Thread newThread(Runnable r){
        Thread t = new Thread();
        t.setPriority(this.priority.getPriorityValue());
        return t;
    }

    public int compare(Task o1, Task o2) {
        return o1.compareTo(o2);
    }


    @Override
    public int compareTo(Task<T> otherTask) {
        return this.priority.getPriorityValue() - otherTask.priority.getPriorityValue();
    }

    public int getPriority (){
        return this.priority.getPriorityValue();
    }

}
