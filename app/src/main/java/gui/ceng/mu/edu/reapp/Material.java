package gui.ceng.mu.edu.reapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.HashMap;

public class Material implements Serializable {
    private String name;
    // Using bitmap photo as a byte[] type because bitmap doesn't Serializable
    private byte[] image;
    private int count;
    private HashMap<String ,String> owner ;

    public HashMap<String, String> getOwner() {
        return owner;
    }

    public void setOwner(HashMap<String, String> owner) {
        this.owner = owner;
    }

    public Material(String name, byte[] image) {
        super();
        this.name = name;
        this.image = image;
        this.count = 0;
        this.owner = new HashMap<>();
    }
    // convert to byte[] image to bitmap to use in ImageView
    public Bitmap getImageInBitmap() {
        if(image != null){
            Bitmap photo = BitmapFactory.decodeByteArray(image, 0, image.length);
            return Bitmap.createScaledBitmap(photo,250,250,true);
        }else {
            return null;
        }

    }
    public byte[] getImageInByte() {
            return image;
    }
    public void setImage( byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if(count <=0 ){
            this.count = 0;
        }else {
            this.count = count;
        }
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", count=" + count +
                ", owner=" + owner +
                '}';
    }
}
