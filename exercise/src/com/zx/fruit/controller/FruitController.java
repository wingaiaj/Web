package com.zx.fruit.controller;

import com.zx.fruit.pojo.Fruit;
import com.zx.fruit.service.FruitService;
import com.zx.myssm.Utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;

/**
 * @ClassName FruitController
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:17
 * @Version 1.0
 */
public class FruitController {

    FruitService fruitService = null;

    public String index(String keyword, String op, Integer pageNo, HttpSession session) {

        if (pageNo == null) {
            pageNo = 1;
        }

        if (StringUtils.isNotEmpty(op) && "oper".equals(op)) {
            if (StringUtils.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);

        } else {
            if (StringUtils.isEmpty(keyword)) {
                keyword = "";
            } else {
                keyword = (String) session.getAttribute("keyword");

            }
        }

        List<Fruit> fruitAll = fruitService.getFruitAll(keyword, pageNo);
        Long count = fruitService.getCount(keyword);

        session.setAttribute("fruitList", fruitAll);
        session.setAttribute("count", count);
        session.setAttribute("pageNo", pageNo);


        return "index";


    }

    public String edit(Integer fid, HttpServletRequest request) {

        Fruit Fruit = fruitService.getOneFruit(fid);
        request.setAttribute("fruit", Fruit);

        return "edit";


    }

    public String update(Integer fid,String fname, Integer fprice, Integer fcount, String remark) {

        fruitService.updateFruit(new Fruit(fid,fname, fprice, fcount, remark));

        return "Redirect:fruit.do";
    }


    public String del(Integer fid) {

        fruitService.delFruit(fid);

        return "Redirect:fruit.do";
    }

    public String add(String fname, Integer fprice, Integer fcount, String remark) {

        fruitService.addFruit(new Fruit(fname, fprice, fcount, remark));

        return "Redirect:fruit.do";

    }


}
