package com.zx.Exam;

import com.zx.utils.ExamUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GetGrades
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/24 14:34
 * @Version 1.0
 */
public class GetGrades {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        try {
            //创建file 对象
            File fileTest = new File("D:/考试");
            //获取目录下所有文件
            File[] files = fileTest.listFiles();
            //获取标准答案 string数组
            String[] standardAnswer = ExamUtils.getStandardAnswer(files);
            for (File StdFile : files) {
                // 获取所有学生的答案
                String[] standardAnswerStd = ExamUtils.getStandardAnswerStd(StdFile);
                //获取学生姓名
                String stName = ExamUtils.getStName(StdFile);
                if (stName != null) {
                    //和正确答案对比 得出分数
                    Integer integer = ExamUtils.infoScore(standardAnswerStd, standardAnswer);
                    list.add(new Student(stName, integer));
                }
            }
            //成绩最高 学生
            Student maxStudent = ExamUtils.max(list);
            System.out.println(maxStudent);
            //成绩最低
            Student minStudent = ExamUtils.min(list);
            minStudent.toString();
            //排序
            List<Student> sort = ExamUtils.sort(list);
            //平均分
            double avg = ExamUtils.avg(list);

            //创建新文件写出
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:/考试/成绩单.txt"));

            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb1.append("成绩单");
            sb2.append("排序结果为:");
            bufferedWriter.newLine();
            bufferedWriter.write(sb1.toString());
            bufferedWriter.newLine();
            bufferedWriter.write(sb2.toString());
            for (Student s : list) {
                //拼接
                StringBuilder sb = new StringBuilder();
                sb.append("Student").append(" { ").append(" ' ").append(s.getName()).append(" ' ").append(" , ").append("'").append(s.getScore()).append(" ' ").append(" } ");
                //调用字符缓冲输出流对象的方法写数据
                bufferedWriter.write(sb.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            bufferedWriter.newLine();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("max: Student").append(" { ").append(" ' ").append(maxStudent.getName()).append(" ' ").append(" , ").append("'").append(maxStudent.getScore()).append(" ' ").append(" } ");
            bufferedWriter.write(sb3.toString());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("min: Student").append(" { ").append(" ' ").append(minStudent.getName()).append(" ' ").append(" , ").append("'").append(minStudent.getScore()).append(" ' ").append(" } ");
            bufferedWriter.write(sb4.toString());
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            //平均分
            StringBuilder sb5 = new StringBuilder();
            sb5.append("avg: ").append(avg);
            bufferedWriter.write(sb5.toString());

            //释放资源
            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
