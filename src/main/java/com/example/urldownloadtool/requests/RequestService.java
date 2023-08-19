package com.example.urldownloadtool.requests;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.urldownloadtool.requests.RequestClass;




public class RequestService {
    public ArrayList<RequestClass> requestList = new ArrayList<>();

    // These will be initialized by takeParams() to download videos later on
    String idParameter;
    String urlParameter;
    

    public void sumRequestsIntoList(RequestClass clientRequest){
        requestList.add(clientRequest);
        
    }

    // this returns the List of Requests
    public ArrayList<RequestClass> returnListOfRequests(){

        return requestList;
    }

    
    // this will be returned on a endpoint
    public String returnParams(){
        return this.idParameter + " passed this URL: " + this.urlParameter;
    }

    // public Video processVideo(){}
    

}
