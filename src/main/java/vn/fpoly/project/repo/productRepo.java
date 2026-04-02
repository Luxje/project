package vn.fpoly.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fpoly.project.model.products;

import java.util.List;

@Repository
public interface productRepo extends JpaRepository<products,Integer> {

    @Query(value = "select * from products where product_name like %?1%", nativeQuery = true)
    List<products> searchproduct(String name);
}
