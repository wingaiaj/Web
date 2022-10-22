package com.zx.fruit.service;

import com.zx.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FruitService
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/19 16:44
 * @Version 1.0
 */
public interface FruitService {

    List<Fruit> getFruitList(Integer pageNo, String keyword);

    Fruit getFruit(Integer fid);

    Long getFruitCount(String keyword);

    boolean addFruit(Fruit fruit);

    boolean delFruit(Integer fid);

    boolean editFruit(Fruit fruit);


}
