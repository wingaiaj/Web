package com.zx.utils;

import com.zx.Exam.Student;

import java.io.*;
import java.util.*;

/**
 * @ClassName ExamUtils
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/24 14:29
 * @Version 1.0
 */
public class ExamUtils {
    //传入 fileList  获取标准答案
    public static String[] getStandardAnswer(File[] fileList) throws IOException {
        String StandardAnswer = null;
        for (File file : fileList) {
            //获取文件名
            String fileName = file.getName();
            //找到文本
            if ("标准答案.txt".equals(fileName)) {
                //读取
                FileReader fileReader = new FileReader(file);
                char[] cbuf = new char[1024];
                int len;
                while ((len = fileReader.read(cbuf)) != -1) {
                    StandardAnswer = new String(cbuf, 0, len);
                }
            }

        }
        return getRealAnswer(StandardAnswer);
    }

    //获取学生成绩
    public static String[] getStandardAnswerStd(File file) throws IOException {
        String StandardAnswer = null;

        //获取文件名
        String fileName = file.getName();
        //找到文本
        if ("标准答案".equals(fileName)) {
            return null;
        }
        //读取
        FileReader fileReader = new FileReader(file);
        char[] cbuf = new char[1024];
        int len;
        while ((len = fileReader.read(cbuf)) != -1) {
            StandardAnswer = new String(cbuf, 0, len);
        }

        fileReader.close();

        return getRealAnswer(StandardAnswer);
    }


    //获取学生姓名
    public static String getStName(File file) {
        String stringName = new String();


        //获取文件名
        String fileName = file.getName();
        //找到文本
        if ("标准答案.txt".equals(fileName)) {
            return null;
        }
        int dotIndex = fileName.lastIndexOf(".txt");
        //截取字符->学生姓名
        String StudentName = fileName.substring(0, dotIndex);
        stringName = StudentName;


        return stringName;
    }


    public static String[] clearNull(String[] s) {
        String[] relStr = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            if (s[i].startsWith("null")) {
                String subs1 = s[i].substring("null".length());
                relStr[i] = subs1;
            }//空
            else {
                relStr[i] = " ";
            }
        }
        return relStr;
    }

    //读答题卡系统：
//1、班级排名（从高到低）、平均分（保留2位小数）；
//2、求最高分、最低分的同学；
//3、将结果输出到本地文件。
//注意：读取的文件统一放到（D:\考试），输出的文件（成绩单.txt）统一放到（D盘）
//1-10个选择题，单选，每题5分
//11-15个选择题，多选，每题10分，少选5分，多选错0分
//项目以自己的名字（拼音）命名
    //获取成绩
    public static Integer infoScore(String[] student, String[] correct) {
        int point = 0;
        int flag = 0;
        for (int i = 0; i < 15; i++) {
            if (i < 10) {
                if (correct[i].equals(student[i])) {
                    point += 5;
                }
            }
            if (i >= 10) {
                if (correct[i].equals(student[i])) {
                    point += 10;
                } else if (correct[i].length() > student[i].length()) {
                    char[] charsCo = correct[i].toCharArray();
                    char[] charsStd = student[i].toCharArray();
                    Arrays.sort(charsCo);
                    Arrays.sort(charsStd);
                    for (int j = 0; j < charsStd.length; j++) {
                        if (String.valueOf(charsCo[j]).equals(String.valueOf(charsStd[j]))) {
                            flag++;
                        }
                    }
                    if (flag == charsStd.length) {
                        point += 5;
                    }

                }
            }
        }
        return point;
    }

    //根据ascll码提取所有去除其他字符的答案
    public static String[] getRealAnswer(String answer) {
        //15题
        int k = 0;
        boolean flag = false;
        String[] answerArr = new String[15];
        char[] chars = answer.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            flag = false;
            int f = 0;
            if (chars[i] == 12289) {
                if (i < chars.length - 1) {
                    if (chars[i + 1] < 65 || chars[i + 1] > 68) {
                        answerArr[k] += " ";
                        k++;
                    }
                } else {
                    if (chars[i] < 65 || chars[i] > 68) {
                        answerArr[k] += " ";
                        k++;
                    }
                }
            }
            if (k < 10) {
                if (chars[i] >= 65 && chars[i] <= 68) {
                    answerArr[k] += String.valueOf(chars[i]);
                    k++;
                    continue;
                }
            }

            if (k >= 10) {
                if (chars[i] >= 65 && chars[i] <= 68) {
                    flag = true;
                    answerArr[k] += String.valueOf(chars[i]);
                    if (i < chars.length - 1) {
                        if (chars[i + 1] >= 65 && chars[i + 1] <= 68) {
                            flag = false;
                        } else if (chars[i + 2] >= 65 && chars[i + 2] <= 68) {
                            flag = false;
                        }
                    }
                }
            }
            if (flag) {

                k++;
            }


        }

        return clearNull(answerArr);
    }

    public static Student max(List<Student> list) {
        Integer Max = 0;
        for (Student student : list) {
            if (student.getScore() > Max) {
                Max = student.getScore();
            }

        }
        for (Student student : list) {
            if (student.getScore() == Max) {
                return student;
            }
        }
        return null;
    }

    public static Student min(List<Student> list) {
        Integer min = list.get(1).getScore();
        for (Student student : list) {
            if (student.getScore() < min) {
                min = student.getScore();
            }

        }
        for (Student student : list) {
            if (student.getScore() == min) {
                return student;
            }
        }
        return null;
    }

    public static List<Student> sort(List<Student> list) {
        Collections.sort(list);
        return list;
    }

    public static double avg(List<Student> list) {
        double sum = 0;
        for (Student student : list) {
            sum += student.getScore();
        }
        return sum / list.size();
    }
}


