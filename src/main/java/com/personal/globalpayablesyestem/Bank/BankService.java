package com.personal.globalpayablesyestem.Bank;

import com.personal.globalpayablesyestem.Bank.Branch.Branch;
import com.personal.globalpayablesyestem.Bank.exceptions.AlreadyExistException;
import com.personal.globalpayablesyestem.Country.Country;
import com.personal.globalpayablesyestem.Country.CountryRepository;
import com.personal.globalpayablesyestem.userAuth.exception.CredentialMisMatchError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BankService {

    private final BankRepository bankRepository;
    private final CountryRepository countryRepository;

    public Bank addBank(String countryId, Bank bank) {

        Country country = countryRepository.findById(countryId).get();

        // Check if the bank already exists in the country
        boolean bankExists = country.getBanks().stream()
                .anyMatch(existingBank -> existingBank.getName().equals(bank.getName()));

        System.out.println(bankExists);

        if (bankExists) {
            throw new AlreadyExistException("Bank already exists in this Country");
        }

        // If the bank doesn't exist, add it to the country and save
        Bank savedBank = bankRepository.save(bank);

        country.getBanks().add(savedBank);
        countryRepository.save(country);

        return savedBank;
    }

    public Bank getBank(String countryId, String bankId) {
        Country country = countryRepository.findById(countryId).orElse(null); // Use 'orElse' to handle the case where the bank is not found
        if (country != null) {
            List<Bank> banks = country.getBanks();
            for (Bank bank : banks) {
                if (bank.getId().equals(bankId)) {
                    return bank;
                }
            }
        }
        throw new CredentialMisMatchError("Credential Mismatch Error");
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

    public void deleteBank(String countryId, String bankId) {
        Country country = countryRepository.findById(countryId).get();

        Bank bank = bankRepository.findById(bankId).get();

        List<Bank> banksToBeSaved = new ArrayList<>();

        country.getBanks().forEach(bankToBeAdded -> {
            if (!bankToBeAdded.getName().equals(bank.getName())) {
                banksToBeSaved.add(bankToBeAdded);
            }
        });

        country.setBanks(banksToBeSaved);

        countryRepository.save(country);

        bankRepository.delete(bank);
    }
}
