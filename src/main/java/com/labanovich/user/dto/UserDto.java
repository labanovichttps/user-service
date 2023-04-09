package com.labanovich.user.dto;

import java.math.BigDecimal;

public record UserDto(Long id,
                      String username,
                      BigDecimal balance) {
}
