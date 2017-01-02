package ua.ho.andro.preciousmetalstracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by piter on 21.06.16.
 */
public class Gold {
    @SerializedName("title")
    @Expose
    private String title;

    /**
     *
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
