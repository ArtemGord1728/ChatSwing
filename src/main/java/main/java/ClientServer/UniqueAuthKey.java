package main.java.ClientServer;

import java.util.UUID;

public class UniqueAuthKey
{
    private UniqueAuthKey(){
    }

    public static UUID getAuthKey() {
        byte[] arrayByte = new byte[100];
        UUID key = UUID.nameUUIDFromBytes(arrayByte);
        return key;
    }
}