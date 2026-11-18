package com.yassine.cqrseventsourcingaxon.query.repository;

import com.yassine.cqrseventsourcingaxon.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pc
 **/
public interface AccountRepository extends JpaRepository<Account,String> {
}
