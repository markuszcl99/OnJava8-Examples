package com.markus.onjava.concurrent.executor.count;

import com.markus.onjava.Nap;

import java.io.*;
import java.util.Vector;

/**
 * @author: markus
 * @date: 2023/2/28 10:01 PM
 * @Description: IO密集型-线程大小设置测试
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class IOTypeTest implements Runnable {

    // 任务被初始化起始时间
    private long initStartTime = 0;

    // 整体执行时间，包括在队列中等待的时间
    Vector<Long> wholeTimeList;

    // 任务真正被执行的时间
    Vector<Long> runTimeList;

    /**
     * 构造函数
     *
     * @param runTimeList
     * @param wholeTimeList
     */
    public IOTypeTest(Vector<Long> runTimeList, Vector<Long> wholeTimeList) {
        this.initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    public void readAndWrite() throws IOException {
        // 查找具体路径的文件
        File sourceFile = new File("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/concurrent/src/main/java/com/markus/onjava/concurrent/executor/count/input/test.pdf");
        // 创建字符输入流
        BufferedReader input = new BufferedReader(new FileReader(sourceFile));
        BufferedWriter output = new BufferedWriter(new FileWriter("/Users/zhangchenglong/IdeaProjects/OnJava8-Examples/concurrent/src/main/java/com/markus/onjava/concurrent/executor/count/output/test.pdf"));
        // 读取源文件，写入到新的文件
        String line = null;
        while ((line = input.readLine()) != null) {
            output.write(line);
        }
        // 刷新流
        output.flush();
        // 关闭输入输出流
        output.close();
        input.close();

    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            // 等两秒
            new Nap(2);
            readAndWrite();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();

        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        System.out.println("单个线程花费时间: " + runTime);
    }
}
