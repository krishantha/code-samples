package com.krishantha.socketreader.controller;


import com.krishantha.socketreader.model.Response;
import com.krishantha.socketreader.service.SocketReaderServiceImpl;
import com.krishantha.socketreader.util.SocketReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketServiceController {


@Autowired
SocketReaderServiceImpl socketReaderService;


@RequestMapping(value = "/{id}/read")
    public Response getReading(@PathVariable("id") int id){

return socketReaderService.getReading(id);

    }



}
