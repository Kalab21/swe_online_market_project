package com.markethub.app.service;

import com.markethub.app.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address createAddress(Address address);
    Address  updateAddress(Address address, Long id);
    void deleteAddressById(Long id);
}
