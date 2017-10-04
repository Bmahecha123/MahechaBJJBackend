package com.example.mahechabjj.mahechabjj.Service;

public interface EncryptionService {

  String encryptString(String input);
  boolean checkPassword(String plainPassword, String encryptedPassword);
}
