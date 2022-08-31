package com.xtb10034.kpc.service;



import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.xtb10034.kpc.unil.CommonUtil.daysBetween;
import static com.xtb10034.kpc.unil.CommonUtil.getDate;


@Component
public class Service {
    private static Log logger = LogFactory.getLog(com.xtb10034.kpc.service.Service.class);

    @Value("${cloud.master.url}")
    private String url;

    @Value("${cloud.token}")
    private String token;

    @Value("${cloud.pod.name}")
    private String podName;

    @Value("${cloud.clear.days}")
    private double clearDays;

    @Value("${cloud.namespace.name}")
    private String nameSpace;


    public void startProcess() throws Exception{
        logger.warn("===Start to connect to k8s.===");


        Config config = new ConfigBuilder().withMasterUrl(url).withOauthToken(token).build();
        KubernetesClient client = new DefaultKubernetesClient(config);

        clearOldPod(client,nameSpace);

        logger.warn("===finish k8s.===");
    }

    public void clearOldPod(KubernetesClient client,String nameSpace) throws Exception {
        logger.warn("===Start to clear old pod.===");
        PodList podList = client.pods().inNamespace(nameSpace).list();
        for (Pod b : podList.getItems()) {
            String realPodName = b.getMetadata().getName();
            String podStatus = b.getStatus().getPhase();
            String createTime = b.getMetadata().getCreationTimestamp().replace("T"," ").replace("Z","");
            String curTime = getDate();
            double days = daysBetween(createTime,curTime);
            System.out.println("realPodName： "+ realPodName);
            System.out.println("podStatus： "+ podStatus);
            System.out.println("createTime： "+ createTime);
            System.out.println("days： "+ days);


            if(days > clearDays && podStatus.equalsIgnoreCase("Succeeded") && realPodName.contains(podName)){
                logger.warn(realPodName+" is old.");
                client.pods().inNamespace(nameSpace).withName(realPodName).delete();
            }
        }
        logger.warn("===Finish clearing old pod.===");
    }

}
