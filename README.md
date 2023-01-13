#  Ex2_1

## Description
The Ex2_1 class contains methods for creating text files, counting the number of lines in a file using only a single thread, <br />
using multiple threads, and using a thread pool.  <br />
The createTextFiles method creates a specified number of text files with random number of lines  <br />
and the getNumOfLines, getNumOfLinesThreads, and getNumOfLinesThreadPool methods read and count the number of lines in the files using different approaches. <br />
ThreadNumOfLines class extends Thread and counts the number of lines in a file. <br />
It also defines a LineNumCallable class that implements the Callable interface and counts the number of lines in a file.  <br />
## Calculate the times of different functions:
- Number of files: 200
- Seed: 10
- Bound: 1000

Time | Function| Sum of lines
--- | --- | ---
4618 milliseconds | getNumOfLines |   1011605
3775 milliseconds | getNumOfLinesThreads |   1011605
517 milliseconds | getNumOfLinesThreadPool |   1011605


- Number of files: 300
- Seed: 100
- Bound: 100000

Time | Function| Sum of lines
--- | --- | ---
2059 milliseconds | getNumOfLines | 14745392
1969 milliseconds | getNumOfLinesThreads | 14745392
997 milliseconds | getNumOfLinesThreadPool | 14745392

* NOTE: The times can be different in different computers.  <br />
  when I run the functions their output tume was less than my old laptop where I try to run same functions.

## Class diagram:
![This is an image](https://i.postimg.cc/Wz1D4SB2/matala-2-thread.png)





#  Ex2_2
## Task
Java class "Task"  implements three interfaces: Callable, ThreadFactory, and Comparable. <br />
The class has a generic type "T" which specifies that the class can work with any type of data. <br />
The class has two constructors, one that takes in a "Callable" object and a "TaskType" object as parameters,<br />
and another that takes in only a "Callable" object. <br />
The class also has two static methods, "createTask" which creates an object of the Task class, <br />
one that takes in a "Callable" object and a "TaskType" object as parameters,<br />
and another that takes in only a "Callable" object. <br />
The class also has an overridden "call" method that returns an object of type T and throws an exception in case of failure. <br />
The class also has an overridden "newThread" method that creates a new thread <br />
and sets its priority to the priority value of the TaskType object. <br />
The class also has an overridden "compareTo" method that compares the priority value of two Task objects. <br />
The class also has a "getPriority" method that returns the priority value of the Task object. <br />

## TaskType
This is an enumeration in Java that defines three types of tasks: COMPUTATIONAL, IO, and OTHER, <br />
each with a specific priority level. <br />
Each enumeration value has an associated integer value that represents the priority level, <br />
with 1 being the highest priority and 10 being the lowest. <br />

## CustomExecutor
This is a custom implementation of the ThreadPoolExecutor class in Java, which adds support for priority-based task execution. <br />
The class uses a PriorityBlockingQueue to store tasks, which allows for tasks to be executed in order of priority. <br />
The class defines several methods for submitting tasks to the executor, including: <br />
- submit(Task<T>)
- submit(Callable<T>)
- submit(Callable<T>, TaskType)
  
which allow the user to specify the priority of a task. 

## Class diagram:
![This is an image](https://i.postimg.cc/g2WDKL63/matala-2-ex2-2.png)
