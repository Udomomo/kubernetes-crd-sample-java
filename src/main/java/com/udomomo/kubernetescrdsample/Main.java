package com.udomomo.kubernetescrdsample;

import com.udomomo.kubernetescrdsample.api.model.v1beta1.Nginx;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    try (final KubernetesClient client = new DefaultKubernetesClient()) {
      CustomResourceDefinition crd = CustomResourceDefinitionContext
              .v1beta1CRDFromCustomResourceType(Nginx.class)
              .build();
      client.apiextensions().v1beta1().customResourceDefinitions().createOrReplace(crd);
      logger.info("CustomResourceDefinition Nginx is created.");

      MixedOperation<Nginx, KubernetesResourceList<Nginx>, Resource<Nginx>> nginxClient
              = client.customResources(Nginx.class);
      Nginx nginx = nginxClient.load(Main.class.getResourceAsStream("/nginx-cr.yaml")).get();
      nginxClient.inNamespace("develop").create(nginx);

      KubernetesResourceList<Nginx> nginxs = nginxClient.inNamespace("develop").list();
      logger.info("{} nginxs object is found.", nginxs.getItems().size());

      nginxClient.inNamespace("develop").withName("example").delete();
    }
  }
}
