package io.fabric8.openshift;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.openshift.client.OpenShiftClient;

public class DeploymentConfigDelete {
    public static void main(String[] args) {
        try (OpenShiftClient client = new KubernetesClientBuilder().build().adapt(OpenShiftClient.class)) {
            client.deploymentConfigs().inNamespace("default").withName("frontend").delete();
            client.deploymentConfigs().inNamespace("default").withName("eclipse-jkube-sample-spring-boot-jib").delete();
            client.deploymentConfigs().inNamespace("default").withName("wordpress-mysql-example").withPropagationPolicy(DeletionPropagation.FOREGROUND).withGracePeriod(0l).delete();
        }
    }
}