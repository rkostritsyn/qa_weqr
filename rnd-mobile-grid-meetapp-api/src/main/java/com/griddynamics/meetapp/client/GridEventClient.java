package com.griddynamics.meetapp.client;

import com.griddynamics.meetapp.client.resource.EventResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "events", url = "https://www.griddynamics.com/api/")
public interface GridEventClient {

    @RequestMapping(method = RequestMethod.GET, value = "/events/previews/all")
    EventResource getEvents();
}
