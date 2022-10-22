package com.zx.fruit.service;

import com.zx.fruit.DAO.fruitDao;
import com.zx.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FruitServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/19 16:44
 * @Version 1.0
 */
public class FruitServiceImp implements FruitService {
    fruitDao fruitDao = null;

    @Override
    public List<Fruit> getFruitList(Integer pageNo, String keyword) {
        return fruitDao.QueryList(pageNo, keyword);
    }

    @Override
    public Fruit getFruit(Integer fid) {
        return fruitDao.Query(fid);
    }

    @Override
    public Long getFruitCount(String keyword) {
        Long count = fruitDao.count(keyword);
        return (count + 6 - 1) / 6;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        Fruit fruit1 = fruitDao.Query(2);
        fruit1.setFcount(3);
        boolean insert = fruitDao.insert(fruit);
        fruitDao.update(fruit1);
        return insert;
    }

    @Override
    public boolean delFruit(Integer fid) {
        return fruitDao.del(fid);
    }

    @Override
    public boolean editFruit(Fruit fruit) {
        return fruitDao.update(fruit);
    }
}
