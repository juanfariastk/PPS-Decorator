package model;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String encryptedData = encode(data);
        super.writeData(encryptedData);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return decode(data);
    }

    private String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    private String decode(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}

