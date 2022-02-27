package com.example.schoolmms.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String countryName;
    private String cityName;
    private Long code;
    private String streetName;
    private Integer homeNumber;
    private Integer apartmentNumber;
}
