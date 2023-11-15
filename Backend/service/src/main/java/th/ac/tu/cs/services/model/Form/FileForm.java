package th.ac.tu.cs.services.model.Form;

import java.sql.Blob;

public class FileForm {
    private byte[] data;
    private String name;
    private String type;
    private int size;
    public FileForm() {
    }
    public FileForm(byte[] data, String name, String type, int size) {
        this.data = data;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
