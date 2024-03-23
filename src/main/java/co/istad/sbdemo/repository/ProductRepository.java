package co.istad.sbdemo.repository;

import co.istad.sbdemo.model.Category;
import co.istad.sbdemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
}
