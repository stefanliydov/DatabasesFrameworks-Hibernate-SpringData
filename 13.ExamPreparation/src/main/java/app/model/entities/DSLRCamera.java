package app.model.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;


@Entity
@DiscriminatorValue(value = "DSLR")
public class DSLRCamera extends BasicCamera {

    @Column(name = "max_shutter_speed")
    private Integer maxShutterSpeed;

    public Integer getMaxShutterSpeed() {
        return this.maxShutterSpeed;
    }

    public void setMaxShutterSpeed(Integer maxShutterSpeed) {
        this.maxShutterSpeed = maxShutterSpeed;
    }
}
