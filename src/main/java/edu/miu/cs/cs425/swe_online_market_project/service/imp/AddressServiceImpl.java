package edu.miu.cs.cs425.swe_online_market_project.service.imp;

import edu.miu.cs.cs425.swe_online_market_project.model.Address;
import edu.miu.cs.cs425.swe_online_market_project.repository.AddressRepository;
import edu.miu.cs.cs425.swe_online_market_project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }



    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Address address, Long id) {
        Address address1 = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found: " + id));
        address1.setId(address.getId());
        address1.setStreet(address.getStreet());
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setZipCode(address.getZipCode());
        address1.setAddressType(address.getAddressType());
        address1.setPhoneNumber(address.getPhoneNumber());
        address1.setUser(address.getUser());
        return addressRepository.save(address1);
    }

}
