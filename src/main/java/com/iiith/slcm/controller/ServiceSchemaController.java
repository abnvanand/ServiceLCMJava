package com.iiith.slcm.controller;

import com.iiith.slcm.businessentities.ServiceSchema;
import com.iiith.slcm.service.ServiceLCM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceSchemaController {

    @Autowired
    private ServiceLCM serviceLCM;
		
		/*@RequestMapping("/topics")
		public List<Topic> getAllTopics(){
			return topicService.getAllTopics();
		}*/

    @RequestMapping(value = "/service/start", method = RequestMethod.POST)
    public void startService(@RequestBody ServiceSchema serviceSchema) {
        serviceLCM.startService(serviceSchema);

    }

//    @RequestMapping(value = "/topics/add", method = RequestMethod.POST)
//    public void addTopic(@RequestBody Service topic) {
//        serviceLCM.addTopic(topic);
//
//    }
		
		/*@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
		public void updateTopic(@RequestBody Topic topic, @PathVariable String id){
			topicService.updateTopic(topic, id);
		}
		
		@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
		public void deleteTopic(@PathVariable String id){
			topicService.deleteTopic(id);
		}*/
}
