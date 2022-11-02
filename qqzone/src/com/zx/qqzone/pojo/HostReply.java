package com.zx.qqzone.pojo;

import java.sql.Date;

/**
 * @ClassName HostReplyDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 19:59
 * @Version 1.0
 */
public class HostReply {
    private Integer id;
    private String content;
    private Date hostReplyDate;
    private Integer author;
    private Integer reply;

    public HostReply() {
    }

    public HostReply(String content, Date hostReplyDate, Integer author, Integer reply) {
        this.content = content;
        this.hostReplyDate = hostReplyDate;
        this.author = author;
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getHostReplyDate() {
        return hostReplyDate;
    }

    public void setHostReplyDate(Date hostReplyDate) {
        this.hostReplyDate = hostReplyDate;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }
}
