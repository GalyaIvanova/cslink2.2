package com.example.cslink.user.controller.service.impl;

import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.exceptions.ResourceAlreadyExistsException;
import com.example.cslink.management.authentication.config.JwtTokenUtil;
import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.model.dataccess.dao.UserProfileDao;
import com.example.cslink.user.model.datatypes.builder.UserProfileBuilder;
import com.example.cslink.user.model.datatypes.enums.Role;
import com.example.cslink.user.model.dto.UserProfileDto;
import com.example.cslink.user.model.entity.UserProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceImplTest {

    @Mock
    private UserProfileDao userProfileRepository;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Test
    public void testGetUserProfileById() {
        Long id=1L;
        UserProfile expectedUserProfile=new UserProfileBuilder()
                .withId(id)
                .withUsername("test_user")
                .withPassword("test_password")
                .withRole(Role.CUSTOMER)
                .withToken("test_token")
                .build();
        UserProfile test_user2=new UserProfileBuilder()
                .withId(id)
                .withUsername("test_user")
                .withRole(Role.CUSTOMER)
                .build();
        UserProfileDto expectedDto=userProfileMapper.toDtoModel(test_user2);
        when(userProfileRepository.findById(id)).thenReturn(Optional.of(expectedUserProfile));
        when(userProfileMapper.toDtoModel(expectedUserProfile)).thenReturn(expectedDto);

        UserProfileDto actualDto=userProfileService.getUserProfileById(id);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testValidateUserProfile() {
        UserProfile expectedUserProfile=new UserProfileBuilder()
                .withId(1L)
                .withUsername("test_user")
                .withPassword("test_password")
                .withRole(Role.CUSTOMER)
                .withToken("old.test.token")
                .build();
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        grantedAuthorities.add(expectedUserProfile.getRole());
        User userDetails=new User(expectedUserProfile.getUsername(), expectedUserProfile.getPassword(),
                grantedAuthorities);
        UserProfile new_test_token=new UserProfileBuilder()
                .withId(expectedUserProfile.getId())
                .withUsername(expectedUserProfile.getUsername())
                .withPassword(expectedUserProfile.getPassword())
                .withRole(expectedUserProfile.getRole())
                .withToken("new.test.token")
                .build();
        UserProfileDto expectedDto=userProfileMapper.toDtoModel(new_test_token);
        when(userProfileRepository.findByUsername(expectedUserProfile.getUsername()))
                .thenReturn(Optional.of(expectedUserProfile));
        when(jwtTokenUtil.validateToken(expectedUserProfile.getToken(), userDetails))
                .thenReturn(true);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn("new.test.token");


        UserProfileDto actualDto=userProfileService.validateUserProfile(expectedUserProfile);

        assertEquals(expectedDto, actualDto);
    }

    @Test
    public void testCreateUserProfileWithExistingUsername() {
        String existingUsername="testuser";
        UserProfileDto userProfileDto=new UserProfileDto();
        UserProfile profile=new UserProfileBuilder().withUsername(existingUsername)
                .withPassword("password").withRole(Role.COSMETOLOGIST).build();
        when(userProfileMapper.toEntity(userProfileDto)).thenReturn(profile);
        when(userProfileRepository.findByUsername(existingUsername)).thenReturn(Optional.of(profile));

        assertThrows(ResourceAlreadyExistsException.class, () -> {
            userProfileService.createUserProfile(userProfileDto);
        });
    }

    @Test
    public void testGetAllUserProfiles() {
        //TODO
        // UserProfile userProfile1=new UserProfileBuilder()
        //         .withUsername("user1")
        //         .withPassword("password1")
        //         .withRole(Role.CUSTOMER)
        //         .build();
        // UserProfile userProfile2=new UserProfileBuilder()
        //         .withUsername("user2")
        //         .withPassword("password2")
        //         .withRole(Role.COSMETOLOGIST)
        //         .build();
        // List<UserProfile> userProfiles=Arrays.asList(userProfile1, userProfile2);
        // UserProfileDao mockRepository=Mockito.mock(UserProfileDao.class);
        // when(mockRepository.findAll()).thenReturn(userProfiles);
        //
        // List<UserProfileDto> result=userProfileService.getAllUserProfiles();
        // Assertions.assertEquals(2, result.size());
        // Assertions.assertEquals("user1", result.get(0).getUsername());
        // Assertions.assertEquals("password1", result.get(0).getPassword());
        // Assertions.assertEquals(Role.CUSTOMER, result.get(0).getRole());
        // Assertions.assertEquals("user2", result.get(1).getUsername());
        // Assertions.assertEquals("password2", result.get(1).getPassword());
        // Assertions.assertEquals(Role.COSMETOLOGIST, result.get(1).getRole());
    }

    @Test
    public void testDeleteUserProfile() {
        // Arrange
        Long id=1L;
        UserProfile userProfile=new UserProfileBuilder().withId(id).build();
        when(userProfileRepository.findById(id)).thenReturn(Optional.of(userProfile));

        // Act
        userProfileService.deleteUserProfile(id);

        // Assert
        Mockito.verify(userProfileRepository, times(1)).findById(id);
        Mockito.verify(userProfileRepository, times(1)).delete(userProfile);
    }

    @Test
    public void testDeleteNonExistentUserProfile() {
        // Arrange
        Long userId=1L;
        when(userProfileRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(CustomResourceNotFoundException.class, () -> {
            userProfileService.deleteUserProfile(userId);
        });
        Mockito.verify(userProfileRepository, times(1)).findById(userId);
        Mockito.verify(userProfileRepository, times(0)).delete(any(UserProfile.class));
    }
}

