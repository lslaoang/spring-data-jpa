package io.lao.alloutjpa.service.bookreposervice;

import io.lao.alloutjpa.dao.JpaStudent;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepoService extends CrudRepository<JpaStudent,Integer> {
}
