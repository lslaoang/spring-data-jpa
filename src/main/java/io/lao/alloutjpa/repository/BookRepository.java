package io.lao.alloutjpa.repository;

import io.lao.alloutjpa.dao.Aklat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Aklat,Integer> {
}
