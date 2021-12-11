package io.lao.alloutjpa.service;

import io.lao.alloutjpa.dao.Aklat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepoService extends JpaRepository<Aklat,Integer> {

    void saveBook(Aklat aklat);

}
