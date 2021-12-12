package io.lao.alloutjpa.repository;

import io.lao.alloutjpa.dao.JpaBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<JpaBook,Integer> {
}
