package io.lao.alloutjpa.repository;

import io.lao.alloutjpa.dao.JpaStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<JpaStudent, String> {
}
