package model;

import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.Base64;

public class CompressionDecorator extends DataSourceDecorator {

    public CompressionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String compressedData = compress(data);
        super.writeData(compressedData);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return decompress(data);
    }

    private String compress(String data) {
        byte[] input = data.getBytes();
        byte[] output = new byte[100];
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        int compressedDataLength = deflater.deflate(output);
        deflater.end();
        return Base64.getEncoder().encodeToString(Arrays.copyOf(output, compressedDataLength));
    }

    private String decompress(String data) {
        byte[] output = new byte[100];
        Inflater inflater = new Inflater();
        try {
            byte[] input = Base64.getDecoder().decode(data);
            inflater.setInput(input);
            int resultLength = inflater.inflate(output);
            inflater.end();
            return new String(output, 0, resultLength);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
