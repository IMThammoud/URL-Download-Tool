package com.example.urldownloadtool.requests;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    
    @GetMapping("/api/showRequests")
    public ArrayList<RequestClass> showRequests(RequestClass myRequest,RequestService myService){

        // adds requests to a List
        myService.sumRequestsIntoList(myRequest);
        

        return myService.returnListOfRequests();

    }

    @PostMapping("/api/returnPost")
    public String returnPostData(RequestClass myRequest, RequestService myService,@RequestParam(name="id") String id, @RequestParam(name="url") String url){

        myService.idParameter = id;
        myService.urlParameter = url;

        // myservice.processVideo();

        return myService.returnParams();


    }

    
}
