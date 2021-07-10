package com.cloud.storage.superDuperDrive.services;

import com.cloud.storage.superDuperDrive.mapper.UsersMapper;
import com.cloud.storage.superDuperDrive.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final HashService hashService;

    @Autowired
    public UsersService(UsersMapper usersMapper, HashService hashService){
        this.usersMapper = usersMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return usersMapper.getUser(username) == null;
    }

    public int createUser(Users users){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);
        users.setPassword(hashedPassword);
        users.setSalt(encodedSalt);
        return usersMapper.insert(users);
    }

    public Users getUser(String username) {
        return usersMapper.getUser(username);
    }
}
