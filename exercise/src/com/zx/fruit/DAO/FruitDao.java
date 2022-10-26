package com.zx.fruit.DAO;

import com.zx.fruit.pojo.Fruit;

import java.util.List;

/**
 * @ClassName FruitDao
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:17
 * @Version 1.0
 */
public interface FruitDao {

    List<Fruit> getFruitList(String keyword, Integer pageNo);

    Fruit getFruit(Integer fid);

    Long fruitCount(String keyword);

    void addFruit(Fruit fruit);

    void Update(Fruit fruit);

    void del(Integer fid);


}
