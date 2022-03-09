package com.example.schoolmms.dto.address;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;
    private String countryName;
    private String cityName;
    private Long code;
    private String streetName;
    private Integer homeNumber;
    private Integer apartmentNumber;
}
