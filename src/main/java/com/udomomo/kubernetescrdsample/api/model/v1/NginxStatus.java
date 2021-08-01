package com.udomomo.kubernetescrdsample.api.model.v1;

import lombok.Data;

@Data
public class NginxStatus {
  private int availableReplicas;
}
