package com.cloud.storage.superDuperDrive.services;

import com.cloud.storage.superDuperDrive.mapper.CredentialsMapper;
import com.cloud.storage.superDuperDrive.model.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    @Autowired
    public void CredentialService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credentials> getCredentialsByUserId(Integer userId) {
        List<Credentials> list =   credentialsMapper.findByUserId(userId);
        for (Credentials item: list) {
            item.setDecryptedPassword(encryptionService.decryptValue(item.getPassword(),item.getKey()));
        }
        return  list;
    }

    public int createCredential(Credentials credential) {
        String key = RandomStringUtils.randomAlphanumeric(16);
        String encodedPassword = encryptionService.encryptValue(credential.getPassword(),key);
        credential.setPassword(encodedPassword);
        credential.setKey(key);
        return credentialsMapper.insertCredential(credential);
    }

    public int updateCredential(Credentials credential) {
        String key = RandomStringUtils.randomAlphanumeric(16);
        credential.setKey(key);
        String encodedPassword = encryptionService.encryptValue(credential.getPassword(),key);
        credential.setPassword(encodedPassword);
        return credentialsMapper.updateCredential(credential);
    }

    public int removeCredential(int credentialId) {
        return credentialsMapper.deleteCredential(credentialId);
    }
}
