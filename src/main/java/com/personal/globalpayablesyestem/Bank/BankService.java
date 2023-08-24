package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BankService {

    private final BankRepository bankRepository;

    public Bank addBank(Bank bank) {
        if (bankRepository.existsByName(bank.getName())) {
            throw new AlreadyExistException("Bank with the given name already exists.");
        }
        return bankRepository.save(bank);
    }
}
