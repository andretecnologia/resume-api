package com.system.shared.mapper;


import com.system.io.entity.Address;
import com.system.shared.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    AddressDTO addressToAddressDTO(Address address);

}
