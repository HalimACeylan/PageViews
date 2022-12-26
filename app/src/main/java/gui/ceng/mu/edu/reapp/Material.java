package gui.ceng.mu.edu.reapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Material implements Serializable {
    private String name;
    private byte[] image;
    private int count;

    public Material(String name,byte[] image) {
        super();
        this.name = name;
        this.image = image;
        this.count = 0;
    }

    public Bitmap getImage() {
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
                '}';
    }
}
