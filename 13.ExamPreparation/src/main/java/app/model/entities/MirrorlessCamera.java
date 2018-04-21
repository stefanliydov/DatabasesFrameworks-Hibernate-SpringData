package app.model.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "Mirrorless")
public class MirrorlessCamera extends BasicCamera{

    @Column(name = "max_video_resolution")
    private String maxVideoResolution;

    @Column(name = "max_framerate")
    private Integer maxFramerate;

    public String getMaxVideoResolution() {
        return this.maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution) {
        this.maxVideoResolution = maxVideoResolution;
    }

    public Integer getMaxFramerate() {
        return this.maxFramerate;
    }

    public void setMaxFramerate(Integer maxFramerate) {
        this.maxFramerate = maxFramerate;
    }
}
