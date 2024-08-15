package io.fabric8.openshift;

import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.openshift.client.OpenShiftClient;

public class OpenshiftClientConnectWithOAuthToken {
  public static void main(String[] args) {
    try (KubernetesClient client = new KubernetesClientBuilder()
        .withConfig(new ConfigBuilder()
            .withMasterUrl("https://api.ci-ln-3sbdl1b-d5d6b.origin-ci-int-aws.dev.rhcloud.com:6443")
            .withOauthToken("0wlp6XGzOBC41oafKI6iU637WdDqUfhj6Lm6oDvtJnw")
            .build())
        .build()) {
      OpenShiftClient openShiftClient = client.adapt(OpenShiftClient.class);

      openShiftClient.projects().list().getItems().forEach(project -> System.out.println(project.getMetadata().getName()));
    } catch (KubernetesClientException e) {
      e.printStackTrace();
    }
  }
}
