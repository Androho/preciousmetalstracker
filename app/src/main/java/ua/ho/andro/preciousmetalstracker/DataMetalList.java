package ua.ho.andro.preciousmetalstracker;

public class DataMetalList {

    public String dataMetalName;
    public String dataMetalVol;
    public String dataMetalCoast;
    public int dataPhotoId;
    public int dataTrendId;


    public DataMetalList(int photoId, String metalName, int trendId, String metalCoast, String metalVol) {
        this.dataMetalName = metalName;
        this.dataMetalVol = metalVol;
        this.dataMetalCoast = metalCoast;
        this.dataPhotoId = photoId;
        this.dataTrendId = trendId;
    }

    public String getDataMetalName() {
        return dataMetalName;
    }

    public DataMetalList setDataMetalName(String dataMetalName) {
        this.dataMetalName = dataMetalName;
        return this;
    }

    public String getDataMetalVol() {
        return dataMetalVol;
    }

    public DataMetalList setDataMetalVol(String dataMetalVol) {
        this.dataMetalVol = dataMetalVol;
        return this;
    }

    public String getDataMetalCoast() {
        return dataMetalCoast;
    }

    public DataMetalList setDataMetalCoast(String dataMetalCoast) {
        this.dataMetalCoast = dataMetalCoast;
        return this;
    }

    public int getDataPhotoId() {
        return dataPhotoId;
    }

    public DataMetalList setDataPhotoId(int dataPhotoId) {
        this.dataPhotoId = dataPhotoId;
        return this;
    }

    public int getDataTrendId() {
        return dataTrendId;
    }

    public DataMetalList setDataTrendId(int dataTrendId) {
        this.dataTrendId = dataTrendId;
        return this;
    }
}