package com.zx.myssm.springmvc;

import javax.servlet.http.HttpSession;

/**
 * @ClassName PageController
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 20:18
 * @Version 1.0
 */
public class PageController {

    public String page(String page, HttpSession session) {
        return page;
    }

    public String page1(String page, Integer id,String ReplyContent ,HttpSession session) {
        session.setAttribute("ReplyId", id);
        session.setAttribute("ReplyContent", ReplyContent);
        return page;
    }
}
