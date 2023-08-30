package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BankService {

    private final BankRepository bankRepository;

    public Bank addBank(Bank bank) {
        System.out.println("Adding bank......");
        if (bankRepository.existsByName(bank.getName())) {
            throw new AlreadyExistException("Bank with the given name already exists.");
        }
        return bankRepository.save(bank);
    }

    public Bank getBank(String bankId) {
        System.out.println("Bank Details");
        return bankRepository.findById(bankId).get();
    }

    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }
}
