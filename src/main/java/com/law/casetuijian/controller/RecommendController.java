package com.law.casetuijian.controller;


import com.law.casetuijian.pojo.LawCase;
import com.law.casetuijian.pojo.LawLabel;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@RestController
@RequestMapping("/similar_case_recommendation")
public class RecommendController {

    @RequestMapping(value = "/multi_label_classification",method= RequestMethod.POST)
    public String tuiJianCaseQuery(@RequestBody LawCase lawCase) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<String>(new TuijianCallable(lawCase.getAnjianmiaoshu(), lawCase.getDiqu(), lawCase.getShuliang()));
        new Thread(futureTask,"类案推荐线程").start();
        String result = (String) futureTask.get();
        System.out.println(result);
        return result;
    }


    @RequestMapping(value = "/multi_label_classification_interface",method= RequestMethod.POST)
    public String biaoQianInterface(@RequestBody LawLabel lawLabel) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new BiaoqianCallable(lawLabel.getAnjianmiaoshu(),lawLabel.getAnjianmingcheng(),lawLabel.getWenshuleixing()));
        new Thread(futureTask,"标签标注线程").start();
        String result = (String) futureTask.get();
        System.out.println(result);
        return result;
    }
}

class TuijianCallable implements Callable<String> {

    private String anjianmiaoshu;
    private String diqu;
    private String shuliang;

    public TuijianCallable(String anjianmiaoshu,String diqu,String shuliang) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.diqu = diqu;
        this.shuliang = shuliang;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        try {
            System.out.println(Thread.currentThread().getName());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", '1');
            jsonObject.put("anjian_miaoshu", anjianmiaoshu);
            jsonObject.put("diqu", diqu);
            jsonObject.put("shuliang", shuliang);
            String str = jsonObject.toJSONString();
            Socket socket = null;
            socket = new Socket(InetAddress.getByName("10.98.67.160"),12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("远程接口调用结束.");
        }
        return sb.toString();
    }
}

class BiaoqianCallable implements Callable<String> {

    private String anjianmiaoshu;
    private String anjianmingcheng;
    private String wenshuleixing;

    public BiaoqianCallable(String anjianmiaoshu,String anjianmingcheng,String wenshuleixing) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.anjianmingcheng = anjianmingcheng;
        this.wenshuleixing = wenshuleixing;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        try {
            System.out.println(Thread.currentThread().getName());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", '2');
            jsonObject.put("anjianmiaoshu", anjianmiaoshu);
            jsonObject.put("anjianmingcheng", anjianmingcheng);
            jsonObject.put("wenshuleixing", wenshuleixing);
            String str = jsonObject.toJSONString();
            Socket socket = null;
            socket = new Socket(InetAddress.getByName("10.98.67.160"),12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("远程接口调用结束.");
        }
        return sb.toString();
    }
}

class TuijianRunnable implements Runnable{

    private String anjianmiaoshu;
    private String diqu;
    private String shuliang;
    private String result;

    public TuijianRunnable(String anjianmiaoshu,String diqu,String shuliang) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.diqu = diqu;
        this.shuliang = shuliang;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("anjian_miaoshu", anjianmiaoshu);
            jsonObject.put("diqu", diqu);
            jsonObject.put("shuliang", shuliang);
            String str = jsonObject.toJSONString();
            Socket socket = null;
            socket = new Socket(InetAddress.getByName("10.98.67.160"),12345); //10.88.30.97
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            // 解析结果
            this.result = sb.toString();
            System.out.println(this.result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("远程接口调用结束.");
        }
    }
}

class BiaoqianRunnable implements Runnable{

    private String anjianmiaoshu;
    private String anjianmingcheng;
    private String wenshuleixing;
    private String result;

    public BiaoqianRunnable(String anjianmiaoshu,String anjianmingcheng,String wenshuleixing) {
        this.anjianmiaoshu = anjianmiaoshu;
        this.anjianmingcheng = anjianmingcheng;
        this.wenshuleixing = wenshuleixing;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("anjian_miaoshu", anjianmiaoshu);
            jsonObject.put("anjianmingcheng", anjianmingcheng);
            jsonObject.put("wenshuleixing", wenshuleixing);
            String str = jsonObject.toJSONString();
            Socket socket = null;
            socket = new Socket(InetAddress.getByName("10.98.67.160"),12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            // 解析结果
            this.result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("远程接口调用结束.");
        }
    }
}