package com.zx.qqzone.pojo;

import java.util.List;

/**
 * @ClassName BasicUser
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 19:56
 * @Version 1.0
 */
public class BasicUser {
    private Integer id;
    private String loginId;
    private String nickName;
    private String pwd;
    private String headImg;

    //好友列表
    private List<BasicUser> friendList;
    //日志列表
    private List<Topic> topicList;
    //回复列表
    private List<Reply> replyList;
    //用户信息
    private DetailUser detailUser;


    public BasicUser() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public List<BasicUser> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<BasicUser> friendList) {
        this.friendList = friendList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public DetailUser getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(DetailUser detailUser) {
        this.detailUser = detailUser;
    }
}
