package com.zx.qqzone.pojo;

import java.sql.Date;
import java.util.List;

/**
 * @ClassName Topic
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 19:58
 * @Version 1.0
 */
public class Topic {
   private Integer id;
    private String title;
    private String content;
    private Date topicDate;
    private Integer author;
    private String headImg;
    private String NickName;

    //一个日志多个回复
    private List<Reply> replyList;

    public Topic() {
    }

 public Topic(String title, String content, Date topicDate, Integer author) {
  this.title = title;
  this.content = content;
  this.topicDate = topicDate;
  this.author = author;
 }

 public String getNickName() {
  return NickName;
 }

 public void setNickName(String nickName) {
  NickName = nickName;
 }

 public Integer getId() {
  return id;
 }

 public void setId(Integer id) {
  this.id = id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getContent() {
  return content;
 }

 public void setContent(String content) {
  this.content = content;
 }

 public Date getTopicDate() {
  return topicDate;
 }

 public void setTopicDate(Date topicDate) {
  this.topicDate = topicDate;
 }

 public Integer getAuthor() {
  return author;
 }

 public void setAuthor(Integer author) {
  this.author = author;
 }

 public List<Reply> getReplyList() {
  return replyList;
 }

 public void setReplyList(List<Reply> replyList) {
  this.replyList = replyList;
 }

 public String getHeadImg() {
  return headImg;
 }

 public void setHeadImg(String headImg) {
  this.headImg = headImg;
 }
}
