package org.matheuscordeiro.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@Builder
public record ProductDto(Long id, String name, String description, String category, String model, BigDecimal price) {
}
