package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialsMapper credentialsMapper;
    private EncryptionService encryptionService;

    Logger logger = LoggerFactory.getLogger(CredentialService.class);

    public CredentialService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }


    public Integer createCredential(Credentials credential){
        logger.error("Now ready to insert the credential in Service  "+credential.getUrl()+"  "+credential.getUrlUserName()+" "+credential.getUrlPassWord());
        return credentialsMapper.createCredential(credential);
    }

    public Integer updateCredential(Credentials credential){
        //when update, encrypt the passwd first
        return credentialsMapper.updateCredential(credential);
    }


    //update a POJO credential from Form with it's key from DB
    public void updateCredentialWithKey(Credentials credential){
        credential.setKey(credentialsMapper.getKey(credential.getCredentialId()));
    }

    public Integer deleteCredential(Integer credentialId){
        return credentialsMapper.deleteCredential(credentialId);
    }
    public List<Credentials> getAllCredentials(){
        return credentialsMapper.getAllCredentials();
    }
    public List<Credentials> getCredentialsForUser(Integer userId) {return credentialsMapper.getCredentialsForUser(userId);}

    public void encryptPassword(Credentials credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        credential.setKey(encodedKey);

        String encryptedPassword = encryptionService.encryptValue(credential.getUrlPassWord(), encodedKey);

        credential.setUrlPassWord(encryptedPassword);
        return;

    }

    public void decryptPassword(Credentials credential){
               credential.setUrlPassWord(encryptionService.decryptValue(credential.getUrlPassWord(),credential.getKey()));
               return;

    }

    public String decryptPassword(String key, String encrptedPwd){
        return encryptionService.decryptValue(encrptedPwd,key);
    }


    public String getKeyById(Integer id){ return credentialsMapper.getKey(id);}
    public Integer getLastCredentialId() {return credentialsMapper.getLastCredentialId();}

}
