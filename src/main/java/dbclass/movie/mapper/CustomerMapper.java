package dbclass.movie.mapper;

import dbclass.movie.domain.user.Customer;
import dbclass.movie.dto.user.CustomerInfoDTO;
import dbclass.movie.dto.user.CustomerInfoToClientDTO;

import java.sql.Date;

public class CustomerMapper {

    private CustomerMapper() {}

    public static Customer customerInfoDTOToCustomer(CustomerInfoDTO customerInfoDTO) {
        return Customer.builder()
                .name(customerInfoDTO.getName())
                .loginId(customerInfoDTO.getLoginId())
                .password(customerInfoDTO.getPassword())
                .nickname(customerInfoDTO.getNickname())
                .birthdate(Date.valueOf(customerInfoDTO.getBirthdate()))
                .gender(customerInfoDTO.getGender())
                .phoneNo(customerInfoDTO.getPhoneNo())
                .email(customerInfoDTO.getEmail())
                .build();
    }

    public static CustomerInfoToClientDTO customerToCustomerInfoToClientDTO(Customer customer) {
        return CustomerInfoToClientDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNo(customer.getPhoneNo())
                .gender(customer.getGender())
                .birthdate(customer.getBirthdate())
                .loginId(customer.getLoginId())
                .nickname(customer.getNickname())
                .point(customer.getPoint())
                .build();
    }
}
