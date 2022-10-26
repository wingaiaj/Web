package com.zx.Exam;

/**
 * @ClassName Student
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/24 14:31
 * @Version 1.0
 */
//读答题卡系统：
//1、班级排名（从高到低）、平均分（保留2位小数）；
//2、求最高分、最低分的同学；
//3、将结果输出到本地文件。
//注意：读取的文件统一放到（D:\考试），输出的文件（成绩单.txt）统一放到（D盘）
//1-10个选择题，单选，每题5分
//11-15个选择题，多选，每题10分，少选5分，多选错0分
//项目以自己的名字（拼音）命名
public class Student implements Comparable<Student> {
    String name;
    Integer score;

    public Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
       return o.getScore()-this.getScore();
    }

    @Override
    public String toString() {
        return "{name="+name+",score="+score+"}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
