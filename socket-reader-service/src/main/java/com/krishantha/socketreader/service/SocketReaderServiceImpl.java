package com.krishantha.socketreader.service;

import com.krishantha.socketreader.model.Response;
import com.krishantha.socketreader.util.SocketReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketReaderServiceImpl {


    public Response getReading(int station){


        String connection=getConnetionString(station);
        if(connection==null){
            throw new RuntimeException("Invalid station id");
        }
        String data[]=getConnetionString(station).split(":");
        SocketReader socketReader= new SocketReader(data[0],Integer.valueOf(data[1]));
        Response response= new Response();
        String reading =socketReader.readValue();



if(reading!=null){
    String[] responseValue=reading.split(",");
    response.setLitValue(Double.valueOf(responseValue[0]));
    response.setUnitPrice(Double.valueOf(responseValue[1]));

    response.setTotal(Double.valueOf(responseValue[2]));
    return response;
}else {
    throw new RuntimeException("Reading is null");
}


    }


    private String getConnetionString(int stationId){


        switch (stationId){
            case 1:
                return "192.168.8.101:3001";
            case 2:
                return "192.168.8.102:3002";
            case 3:
                return "192.168.8.103:3003";
            case 4:
                return "192.168.8.104:3004";
        }
        return null;
    }



}
