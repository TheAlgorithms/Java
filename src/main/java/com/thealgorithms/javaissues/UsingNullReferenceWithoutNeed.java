package com.thealgorithms.javaissues;

import com.thealgorithms.javaissues.util.Person;

import java.util.List;

public class UsingNullReferenceWithoutNeed {

    public static void useNullReferenceWithoutNeed() {
        Person person = new Person();
        List<String> accountIds = person.getAccountIds();
        for (String accountId : accountIds) {
            processAccount(accountId);
        }
    }

    public static void processAccount(String accountId){
        System.out.println(accountId);
    }
}
