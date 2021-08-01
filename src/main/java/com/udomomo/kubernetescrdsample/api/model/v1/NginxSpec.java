package com.udomomo.kubernetescrdsample.api.model.v1;

import lombok.Data;

import java.util.HashMap;

@Data
public class NginxSpec {
  private int replicas;
  private HashMap<String, String> labels;
}
