package com.udomomo.kubernetescrdsample.api.model.v1beta1;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Version;

@Version("v1beta1")
@Group("udomomo.com")
@Plural("Nginxs")
public class Nginx extends CustomResource<NginxSpec, NginxStatus> implements Namespaced {
}
