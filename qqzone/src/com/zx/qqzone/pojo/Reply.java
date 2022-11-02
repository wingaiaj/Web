package com.zx.qqzone.pojo;

import java.sql.Date;

/**
 * @ClassName Reply
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 19:58
 * @Version 1.0
 */
public class Reply {
    private Integer id;
    private String content;
    private Date replyDate;
    private Integer author;
    private Integer topic;
    private String headImg;
    private String NickName;
    private HostReply hostReply;



    public Reply() {
    }

    public Reply( String content, Date replyDate, Integer author, Integer topic) {
        this.content = content;
        this.replyDate = replyDate;
        this.author = author;
        this.topic = topic;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }


    public Integer getTopic() {
        return topic;
    }

    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}
