package com.personal.globalpayablesyestem.bank.utils;

public class BankAndBranchEndpointUtils {

    public static final String ADD_BANK = "/country/{countryId}/bank";
    public static final String GET_BANK = "/country/{countryId}/bank/{bankId}";
    public static final String GET_ALL_BANKS = "/banks";
    public static final String UPDATE_BANK = "/bank/{bankId}";
    public static final String DELETE_BANK = "/country/{countryId}/bank/{bankId}";


    public static final String ADD_BRANCH = "/bank/{bankId}/branch";
    public static final String GET_BRANCH = "/bank/{bankId}/branch/{branchId}";
    public static final String GET_ALL_BRANCHES = "/bank/branches";
    public static final String UPDATE_BRANCH = "/bank/branch/{branchId}";
    public static final String DELETE_BRANCH = "/bank/{bankId}/branch/{branchId}";
}
