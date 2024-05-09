package com.clearsolutions.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.clearsolutions.dto.request.AddressRequestDto;
import com.clearsolutions.dto.request.UserDataRequestDto;
import com.clearsolutions.dto.request.UserRequestDto;
import com.clearsolutions.dto.response.AddressResponseDtoData;
import com.clearsolutions.dto.response.UserDataResponseDto;
import com.clearsolutions.dto.response.UserResponseDto;
import com.clearsolutions.entity.User;
import com.clearsolutions.exception.ValidationFailedException;
import com.clearsolutions.mappers.impl.UserMapperImpl;
import com.clearsolutions.service.UserService;
import com.clearsolutions.util.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    private final String baseUrl = "/api/v1/users";
    private UserRequestDto firstUserRequestDto;
    private UserResponseDto firstUserResponseDto;
    private UserResponseDto secondUserResponseDto;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapperImpl mapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        firstUserRequestDto = UserRequestDto.builder()
                .dataRequestDto(UserDataRequestDto.builder()
                        .email(AppConstants.FIRST_USER.getEmail())
                        .firstName(AppConstants.FIRST_USER.getFirstName())
                        .lastName(AppConstants.FIRST_USER.getLastName())
                        .birthDate(AppConstants.FIRST_USER.getBirthDate())
                        .phoneNumber(AppConstants.FIRST_USER.getPhoneNumber())
                        .build())
                .addressRequest(AddressRequestDto.builder()
                        .zipCode(AppConstants.FIRST_USER_ADDRESS.getZipCode())
                        .city(AppConstants.FIRST_USER_ADDRESS.getCity())
                        .address(AppConstants.FIRST_USER_ADDRESS.getConcreteAddress()).build())
                .build();

        firstUserResponseDto = UserResponseDto.builder()
                .dataResponseDto(UserDataResponseDto.builder()
                        .id(AppConstants.FIRST_USER.getId())
                        .email(AppConstants.FIRST_USER.getEmail())
                        .firstName(AppConstants.FIRST_USER.getFirstName())
                        .lastName(AppConstants.FIRST_USER.getLastName())
                        .birthDate(AppConstants.FIRST_USER.getBirthDate())
                        .phoneNumber(AppConstants.FIRST_USER.getPhoneNumber())
                        .build())
                .addressResponse(AddressResponseDtoData.builder()
                        .id(AppConstants.FIRST_USER_ADDRESS.getId())
                        .zipCode(AppConstants.FIRST_USER_ADDRESS.getZipCode())
                        .city(AppConstants.FIRST_USER_ADDRESS.getCity())
                        .address(AppConstants.FIRST_USER_ADDRESS.getConcreteAddress())
                        .build())
                .build();

        secondUserResponseDto = UserResponseDto.builder()
                .dataResponseDto(UserDataResponseDto.builder()
                        .id(AppConstants.SECOND_USER.getId())
                        .email(AppConstants.SECOND_USER.getEmail())
                        .firstName(AppConstants.SECOND_USER.getFirstName())
                        .lastName(AppConstants.SECOND_USER.getLastName())
                        .birthDate(AppConstants.SECOND_USER.getBirthDate())
                        .phoneNumber(AppConstants.SECOND_USER.getPhoneNumber())
                        .build())
                .addressResponse(AddressResponseDtoData.builder()
                        .id(AppConstants.SECOND_USER_ADDRESS.getId())
                        .zipCode(AppConstants.SECOND_USER_ADDRESS.getZipCode())
                        .city(AppConstants.SECOND_USER_ADDRESS.getCity())
                        .address(AppConstants.SECOND_USER_ADDRESS.getConcreteAddress())
                        .build())
                .build();
    }

    @Test
    void createUser_OK() throws Exception {
        when(mapper.toModel(any(UserRequestDto.class))).thenReturn(AppConstants.FIRST_USER);
        when(mapper.toDto(any(User.class))).thenReturn(firstUserResponseDto);
        when(userService.create(any(User.class))).thenReturn(AppConstants.FIRST_USER);

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUserRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").exists());
    }

    @Test
    void createUser_Fail() throws Exception {
        UserRequestDto wrongUserRequestDto = UserRequestDto.builder()
                .dataRequestDto(UserDataRequestDto.builder().email("^$#@").build()).build();
        User wrongUser = User.builder().email("%$#@").build();

        when(mapper.toModel(any(UserRequestDto.class))).thenReturn(wrongUser);
        when(mapper.toDto(any(User.class))).thenReturn(firstUserResponseDto);
        when(userService.create(any(User.class)))
                .thenThrow(new ValidationFailedException("Some data is wrong!"));

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongUserRequestDto)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void updateUser_Ok() throws Exception {
        User updatedUser = AppConstants.SECOND_USER;
        updatedUser.setId(AppConstants.FIRST_USER.getId());
        secondUserResponseDto.getDataResponseDto().setId(AppConstants.FIRST_USER.getId());

        UserRequestDto updatedUserRequestDto = UserRequestDto.builder()
                .dataRequestDto(UserDataRequestDto.builder()
                        .email(AppConstants.SECOND_USER.getEmail())
                        .firstName(AppConstants.SECOND_USER.getFirstName())
                        .lastName(AppConstants.SECOND_USER.getLastName())
                        .birthDate(AppConstants.SECOND_USER.getBirthDate())
                        .build())
                .addressRequest(AddressRequestDto.builder()
                        .zipCode(AppConstants.SECOND_USER_ADDRESS.getZipCode())
                        .city(AppConstants.SECOND_USER_ADDRESS.getCity())
                        .address(AppConstants.SECOND_USER_ADDRESS.getConcreteAddress())
                        .build())
                .build();

        when(mapper.toModel(any(UserRequestDto.class))).thenReturn(updatedUser);
        when(mapper.toDto(any(User.class))).thenReturn(secondUserResponseDto);
        when(userService.update(any(), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/{id}",
                                AppConstants.FIRST_USER.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id")
                        .value(String.valueOf(updatedUser.getId())));
    }

    @Test
    void patchUser_Ok() throws Exception {
        String testEmail = "newuseremail.gmail.com";
        User updatedUser = AppConstants.FIRST_USER;
        updatedUser.setEmail(testEmail);
        firstUserResponseDto.getDataResponseDto().setEmail(testEmail);
        firstUserRequestDto.getDataRequestDto().setEmail(testEmail);

        when(mapper.toModel(any(UserRequestDto.class))).thenReturn(updatedUser);
        when(mapper.toDto(any(User.class))).thenReturn(firstUserResponseDto);
        when(userService.patch(any(), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(MockMvcRequestBuilders.patch(baseUrl + "/{id}",
                                AppConstants.FIRST_USER.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstUserRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email")
                        .value(String.valueOf(AppConstants.FIRST_USER.getEmail())));
    }

    @Test
    void deleteUser_Ok() throws Exception {
        Long userForDeletionId = AppConstants.FIRST_USER.getId();

        when(userService.deleteById(userForDeletionId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", userForDeletionId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("Success, deleted User by id "
                        + userForDeletionId)));
        verify(userService, times(1)).deleteById(userForDeletionId);
    }

    @Test
    void deleteUser_Fail() throws Exception {
        Long userForDeletionId = AppConstants.SECOND_USER.getId();

        when(userService.deleteById(userForDeletionId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/{id}", userForDeletionId))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(content().string(containsString("Denied, User with id "
                        + userForDeletionId + " wasn't deleted")));
        verify(userService, times(1)).deleteById(userForDeletionId);
    }

    @Test
    void getUsersByBirthdateBetween_Ok() throws Exception {
        LocalDate dateFrom = LocalDate.of(1990, 1, 1);
        LocalDate dateTo = LocalDate.of(2001, 12, 31);

        List<User> testUsers = new ArrayList<>();
        testUsers.add(AppConstants.FIRST_USER);
        testUsers.add(AppConstants.SECOND_USER);

        when(userService.getUsersByBirthdateBetween(dateFrom, dateTo)).thenReturn(testUsers);

        when(mapper.toDto(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return UserResponseDto.builder()
                    .dataResponseDto(UserDataResponseDto.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .birthDate(user.getBirthDate())
                            .phoneNumber(user.getPhoneNumber())
                            .build())
                    .addressResponse(AddressResponseDtoData.builder()
                            .id(user.getAddress().getId())
                            .zipCode(user.getAddress().getZipCode())
                            .city(user.getAddress().getCity())
                            .address(user.getAddress().getConcreteAddress())
                            .build())
                    .build();
        });

        mockMvc.perform(get(baseUrl)
                        .param("from", dateFrom.toString())
                        .param("to", dateTo.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
                        .value(testUsers.size()));

        verify(userService, times(1)).getUsersByBirthdateBetween(dateFrom, dateTo);
    }

    @Test
    void getUsersByBirthdateBetween_Fail() throws Exception {
        LocalDate dateFrom = LocalDate.of(2010, 1, 1);
        LocalDate dateTo = LocalDate.of(1995, 4, 17);

        mockMvc.perform(get(baseUrl)
                        .param("from", dateFrom.toString())
                        .param("to", dateTo.toString()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Check dates correctness!"));

        verify(userService, never()).getUsersByBirthdateBetween(any(LocalDate.class),
                any(LocalDate.class));
    }
}
