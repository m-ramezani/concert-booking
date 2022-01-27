package com.mahlagha.concertbooking.service;

import com.mahlagha.concertbooking.enumeration.TransactionType;
import com.mahlagha.concertbooking.util.ContextUtil;

import java.util.HashMap;
import java.util.Map;

public class TransactionStrategyFactory {

    private final static Map<TransactionType, Class<? extends TransactionStrategy>> transactionStrategyMap = new HashMap<>();

    static {
        transactionStrategyMap.put(TransactionType.SINGLE, SingleTransactionStrategy.class);
        transactionStrategyMap.put(TransactionType.MULTIPLE, MultipleTransactionStrategy.class);
    }

    public static TransactionStrategy getTransactionStrategy(TransactionType transactionType) {
        return ContextUtil.getAppContext().getBean(transactionStrategyMap.get(transactionType));
    }

}
