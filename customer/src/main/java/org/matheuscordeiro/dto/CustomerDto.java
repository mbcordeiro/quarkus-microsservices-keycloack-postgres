package org.matheuscordeiro.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
public record CustomerDto(Long id,String name, String phone,String email, String address, Long age) {
}
