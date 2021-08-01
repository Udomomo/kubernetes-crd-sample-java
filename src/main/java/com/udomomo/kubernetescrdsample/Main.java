package com.udomomo.kubernetescrdsample;

import com.udomomo.kubernetescrdsample.api.model.v1.Nginx;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try (final KubernetesClient client = new DefaultKubernetesClient()) {
      MixedOperation<Nginx, KubernetesResourceList<Nginx>, Resource<Nginx>> nginxClient
              = client.customResources(Nginx.class);

      Nginx nginx = nginxClient.load(Main.class.getResourceAsStream("/nginx-cr.yaml")).get();
    }
  }
}
