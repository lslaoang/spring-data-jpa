package io.lao.alloutjpa.service;

import io.lao.alloutjpa.domain.Aklat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookService extends JpaRepository<Aklat,Integer> {

}
