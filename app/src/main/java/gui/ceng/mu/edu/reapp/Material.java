package gui.ceng.mu.edu.reapp;

import android.graphics.Bitmap;
import android.view.View;

import java.io.Serializable;

public class Material implements Serializable {
    String name;
    Bitmap image;
    int count;

    public Material(String name,Bitmap image) {
        super();
        this.name = name;
        this.image = image;
        this.count = 0;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
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
