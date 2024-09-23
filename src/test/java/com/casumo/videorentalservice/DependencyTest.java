package com.casumo.videorentalservice;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.Test;

public class DependencyTest {

  private static final String BUSINESS_LAYER = "..business..";
  private static final String BUSINESS_LAYER_NAME = "Business";
  private static final String PRESENTATION_LAYER = "..presentation..";
  private static final String PRESENTATION_LAYER_NAME = "Presentation";
  private static final String PERSISTENCE_LAYER = "..persistence..";
  private static final String PERSISTENCE_LAYER_NAME = "Persistence";

  @Test
  public void testLayerRules() {
    layeredArchitecture()
        .consideringAllDependencies()
        .layer(PRESENTATION_LAYER_NAME).definedBy(PRESENTATION_LAYER)
        .layer(BUSINESS_LAYER_NAME).definedBy(BUSINESS_LAYER)
        .layer(PERSISTENCE_LAYER_NAME).definedBy(PERSISTENCE_LAYER)

        .whereLayer(PRESENTATION_LAYER_NAME).mayNotBeAccessedByAnyLayer()
        .whereLayer(BUSINESS_LAYER_NAME).mayOnlyBeAccessedByLayers(PRESENTATION_LAYER_NAME)
        .whereLayer(PERSISTENCE_LAYER_NAME).mayOnlyBeAccessedByLayers(BUSINESS_LAYER_NAME);
  }
}
