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
