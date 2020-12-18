package com.system.service;

import com.system.shared.dto.AddressDTO;

public interface AddressService {
    AddressDTO getAddress(String resumeId);
}
