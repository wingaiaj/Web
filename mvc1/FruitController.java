package com.zx.fruit.controller;

import com.zx.fruit.DAO.fruitDao;
import com.zx.fruit.DAO.fruitDaoImp;
import com.zx.fruit.pojo.Fruit;
import com.zx.mySpring.Utils.StringUtils;
import com.zx.mySpring.view.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName FruitController
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/17 9:16
 * @Version 1.0
 */
public class FruitController extends ViewBaseServlet {

    fruitDaoImp fruitDaoImp = new fruitDaoImp();

    //之前FruitServlet是一个Sevlet组件，那么其中的init方法一定会被调用
    //之前的init方法内部会出现一句话：super.init();

    private ServletContext servletContext ;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String fname = req.getParameter("fname");
        String fpriceStr = req.getParameter("fprice");
        String fcountStr = req.getParameter("fcount");
        String remark = req.getParameter("remark");
        int fprice = Integer.parseInt(fpriceStr);
        int fcount = Integer.parseInt(fcountStr);

        boolean insert = fruitDaoImp.insert( new Fruit(fname, fprice, fcount, remark));

        resp.sendRedirect("fruit.do");
    }


    protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        int fid = 0;
        if (StringUtils.isNotEmpty(fidStr)) {
            fid = Integer.parseInt(fidStr);
        }
        boolean del = fruitDaoImp.del( fid);

        resp.sendRedirect("fruit.do");

    }
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fidStr = req.getParameter("fid");
        int fid = 0;
        if (StringUtils.isNotEmpty(fidStr)) {
            fid = Integer.parseInt(fidStr);
        }
        Fruit fruit = fruitDaoImp.Query( fid);

        req.setAttribute("fruit",fruit);

        processTemplate("edit",req,resp);

    }
    protected void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //默认页面为1
        int pageNo = 1;
        //获取session
        HttpSession session = request.getSession();

        String op = request.getParameter("op");

        String keyword = null;
        //判断是否为表单提交
        if(StringUtils.isNotEmpty(op)&&"oper".equals(op)){
            //为表单提交
            //获取关键字
            keyword = request.getParameter("keyword");
            if(StringUtils.isEmpty(keyword)){
                keyword = "";
            }
            //保存在session 作用域中
            session.setAttribute("keyword",keyword);

        }else {
            //获取参数
            String pageNoStr = request.getParameter("pageNo");

            //不为空
            if (StringUtils.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            //session 作用域中获取 keyword
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj==null){
                keyword = "";
            }
            else {
                keyword = (String)keywordObj;
            }
        }
        //根据pageno 查询库存
        List<Fruit> fruitList = fruitDaoImp.QueryList(pageNo, keyword);

        Long count = fruitDaoImp.count(keyword);

        //保存在作用域
        session.setAttribute("count", (count+6-1)/6);
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);

        //处理模板
        super.processTemplate("index", request, response);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String fidStr = req.getParameter("fid");
        String fname = req.getParameter("fname");
        String fpriceStr = req.getParameter("fprice");
        String fcountStr = req.getParameter("fcount");
        String remark = req.getParameter("remark");
        int fid = 0;
        int fcount = 0;
        int fprice = 0;
        if (StringUtils.isNotEmpty(fidStr)) {
            fid = Integer.parseInt(fidStr);
        }
        if (StringUtils.isNotEmpty(fpriceStr)) {
            fprice = Integer.parseInt(fpriceStr);
        }
        if (StringUtils.isNotEmpty(fcountStr)) {
            fcount = Integer.parseInt(fcountStr);
        }

        boolean update = fruitDaoImp.update(new Fruit(fid, fname, fprice, fcount, remark));

        resp.sendRedirect("index");
    }

}
