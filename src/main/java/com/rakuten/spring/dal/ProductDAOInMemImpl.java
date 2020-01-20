package com.rakuten.spring.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.rakuten.spring.domain.Product;

//@Repository
public class ProductDAOInMemImpl implements ProductDAO {

  Map<Integer, Product> db = new ConcurrentHashMap<>();
  AtomicInteger ideSequene = new AtomicInteger(0);

  @Override
  public Product save(Product toBeSaved) {
    int id = ideSequene.incrementAndGet();
    toBeSaved.setId(id);
    db.put(id, toBeSaved);
    return toBeSaved;
  }

  @Override
  public Product finById(int id) {

    return db.get(id);
  }

  @Override
  public List<Product> findAll() {

    return new ArrayList<>(db.values());
  }

  @Override
  public void deleteById(int id) {

    db.remove(id);
  }
}
