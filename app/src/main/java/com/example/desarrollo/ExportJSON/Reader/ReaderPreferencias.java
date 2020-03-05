package com.example.desarrollo.ExportJSON.Reader;

public class ReaderPreferencias {
     private String nombreFruta;
     private String imgUrlFruta;
     private String background;
     private boolean isSelect;

    public ReaderPreferencias(String nombreFruta, String imgUrlFruta, String background) {
        this.nombreFruta = nombreFruta;
        this.imgUrlFruta = imgUrlFruta;
        this.background = background;
    }

    public String getNombreFruta() {
        return nombreFruta;
    }

    public void setNombreFruta(String nombreFruta) {
        this.nombreFruta = nombreFruta;
    }

    public String getImgUrlFruta() {
        return imgUrlFruta;
    }

    public void setImgUrlFruta(String imgUrlFruta) {
        this.imgUrlFruta = imgUrlFruta;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean selecte) {
        isSelect = selecte;
    }
}
