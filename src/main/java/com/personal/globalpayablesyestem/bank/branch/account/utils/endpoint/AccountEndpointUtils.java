package com.personal.globalpayablesyestem.bank.branch.account.utils.endpoint;

public class AccountEndpointUtils {

    public static final String ADD_ACCOUNT = "/bank/{bankId}/branch/{branchId}";
    public static final String BANK_DEPOSIT ="/bank/{bankId}/branch/{branchId}/deposit" ;
    public static final String GET_BALANCE ="/bank/{bankId}/branch/{branchId}/account/balance" ;
    public static final String SEND_MONEY ="/bank/{bankId}/branch/{branchId}/accounts/{accountNumber}/{amt}" ;
}
