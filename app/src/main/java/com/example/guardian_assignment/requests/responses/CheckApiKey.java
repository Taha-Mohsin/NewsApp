package com.example.guardian_assignment.requests.responses;


public class CheckApiKey {

    protected static boolean isApiKeyValid(Root response){
        return response.getError() == null;
    }

}
