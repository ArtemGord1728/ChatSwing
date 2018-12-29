package main.java.ClientServer;

import java.util.UUID;

public class UniqueAuthKey
{
    private UniqueAuthKey(){
    }

    public static UUID getAuthKey() {
        byte[] arrayByte = {10, 20};
        UUID key = UUID.nameUUIDFromBytes(arrayByte);
        return key;
    }
}