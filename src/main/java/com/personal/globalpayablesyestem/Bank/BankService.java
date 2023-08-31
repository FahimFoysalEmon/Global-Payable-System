package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    public Bank updateBank(String bankId, Bank bank) {
        Bank bankToBeUpdated = bankRepository.findById(bankId).get();
        BeanUtils.copyProperties(bank, bankToBeUpdated);
        bankToBeUpdated.setId(bankId);
        return bankRepository.save(bankToBeUpdated);
    }

    public void deleteBank(String bankId) {
        bankRepository.deleteById(bankId);
    }
}
