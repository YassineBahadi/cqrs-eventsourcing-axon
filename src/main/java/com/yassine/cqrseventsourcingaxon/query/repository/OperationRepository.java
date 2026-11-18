package com.yassine.cqrseventsourcingaxon.query.repository;

import com.yassine.cqrseventsourcingaxon.query.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pc
 **/
public interface OperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByAccountId(String id);

}
