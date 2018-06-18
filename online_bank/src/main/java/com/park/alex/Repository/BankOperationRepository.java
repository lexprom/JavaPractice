package com.park.alex.Repository;

import com.park.alex.Model.BankOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankOperationRepository extends CrudRepository<BankOperation, Long> {
}
