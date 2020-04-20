package com.iiith.slcm.controller;

import com.iiith.slcm.businessentities.DeploymentResponse;
import com.iiith.slcm.businessentities.ServerInfo;
import com.iiith.slcm.businessentities.ServiceSchema;
import com.iiith.slcm.dataentities.Topology;
import com.iiith.slcm.service.ServiceLCM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        System.out.println("Received Request: /service/start " + serviceSchema);
        serviceLCM.startService(serviceSchema);
    }

    @RequestMapping(value = "/service/redeploy", method = RequestMethod.POST)
    public void redeployService(@RequestBody ServiceSchema serviceSchema) {
        System.out.println("Received Request: /service/redeploy " + serviceSchema);
        serviceLCM.redeployService(serviceSchema);
    }

    @RequestMapping(value = "/service/stop", method = RequestMethod.POST)
    public void stopService(@RequestBody ServiceSchema serviceSchema) {
        System.out.println("Received Request: /service/redeploy " + serviceSchema);
        serviceLCM.stopService(serviceSchema);
    }

    @RequestMapping(value = "/service/update", method = RequestMethod.POST)
    public void allotedServer(@RequestBody ServerInfo serverInfo) {
        System.out.println("Received Request: /service/redeploy " + serverInfo);
        serviceLCM.updateServiceWithIpPort(serverInfo);
    }

    @RequestMapping(value = "/service/deploymentStatus", method = RequestMethod.POST)
    public void deploymentStatus(@RequestBody DeploymentResponse deploymentResponse) {
        System.out.println("Received Request: /service/redeploy " + deploymentResponse);
        if ("success".equalsIgnoreCase(deploymentResponse.getStatus())) {
            // DELTE service info
            serviceLCM.updateTopology(deploymentResponse);
            serviceLCM.deleteServiceInfo(deploymentResponse);
        } else {
            // TODO:
        }
    }


    @RequestMapping(value = "/service/topology/{userId}", method = RequestMethod.GET)
    public List<Topology> getTopology(@PathVariable("userId") String userId) {
        System.out.println("Received Request: /service/topology/{userId} " + userId);
        return serviceLCM.getTopologyForUser(userId);
    }

    @RequestMapping(value = "/service/topology/serviceName/{serviceName}", method = RequestMethod.GET)
    public List<Topology> getTopologyForServiceName(@PathVariable("serviceName") String serviceName) {
        System.out.println("Received Request: /service/topology/serviceName/{serviceName} " + serviceName);
        return serviceLCM.getTopologyForServiceName(serviceName);
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
