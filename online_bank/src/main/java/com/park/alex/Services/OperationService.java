package com.park.alex.Services;

import com.park.alex.Model.BankOperation;

public interface OperationService {
    void commit(BankOperation operation);
    void rollback(BankOperation operation);
}
