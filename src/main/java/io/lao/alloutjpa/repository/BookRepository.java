package io.lao.alloutjpa.repository;

import io.lao.alloutjpa.dao.Aklat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Aklat,Integer> {
}
