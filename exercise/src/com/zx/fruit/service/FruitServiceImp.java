package com.zx.fruit.service;

import com.zx.fruit.DAO.FruitDao;
import com.zx.fruit.DAO.FruitDaoImp;
import com.zx.fruit.pojo.Fruit;

import java.util.List;

/**
 * @ClassName FruitServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:24
 * @Version 1.0
 */
public class FruitServiceImp implements FruitService {
    FruitDao fruitDao = null;

    @Override
    public List<Fruit> getFruitAll(String keyword, Integer pageNo) {
        return fruitDao.getFruitList(keyword, pageNo);
    }

    @Override
    public Fruit getOneFruit(Integer fid) {
        return fruitDao.getFruit(fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDao.addFruit(fruit);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDao.Update(fruit);
    }

    @Override
    public Long getCount(String keyword) {
        Long count =  fruitDao.fruitCount(keyword);

        return (count + 10 - 1) / 10;
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDao.del(fid);
    }
}
