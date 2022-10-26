package com.zx.fruit.service;

import com.zx.fruit.pojo.Fruit;

import java.util.List;

/**
 * @ClassName FruitService
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:25
 * @Version 1.0
 */
public interface FruitService {
    List<Fruit> getFruitAll(String keyword, Integer pageNo);

    Fruit getOneFruit(Integer fid);

    void addFruit(Fruit fruit);

    void updateFruit(Fruit fruit);

    Long getCount(String keyword);

    void delFruit(Integer fid);

}
