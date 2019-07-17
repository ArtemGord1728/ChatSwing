package main.java.CoreApp;

import java.util.UUID;

public class UniqueAuthKey {
    private UniqueAuthKey() {
    }

    public UniqueAuthKey getAuthKey() {
    	return new UniqueAuthKey();
    }
    
    public static UUID setAuthKey() {
        byte[] arrayByte = {10, 20};
        return UUID.nameUUIDFromBytes(arrayByte);
    }
}