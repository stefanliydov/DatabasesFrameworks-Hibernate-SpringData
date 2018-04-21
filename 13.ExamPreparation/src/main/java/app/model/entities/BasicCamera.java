package app.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "cameras")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "camera_type")
public abstract class BasicCamera {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "make",nullable = false)
    private String make;

    @Column(name = "models",nullable = false)
    private String model;

    @Column(name = "is_full_name")
    private boolean isFullFrame;

    @Column(name = "min_ISO",nullable = false)
    @Size(min=100)
    private Integer minISO;

    @Column(name = "max_ISO")
    private Integer maxISO;

    @OneToMany(mappedBy = "primaryCamera")
    private Set<Photographer> primaryPhotographers;
    @OneToMany(mappedBy = "secondaryCamera")
    private Set<Photographer> secondaryPhotographers;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isFullFrame() {
        return this.isFullFrame;
    }

    public void setFullFrame(boolean fullFrame) {
        isFullFrame = fullFrame;
    }

    public Integer getMinISO() {
        return this.minISO;
    }

    public void setMinISO(Integer minISO) {
        this.minISO = minISO;
    }

    public Integer getMaxISO() {
        return this.maxISO;
    }

    public void setMaxISO(Integer maxISO) {
        this.maxISO = maxISO;
    }

    public Set<Photographer> getPrimaryPhotographers() {
        return this.primaryPhotographers;
    }

    public void setPrimaryPhotographers(Set<Photographer> primaryPhotographers) {
        this.primaryPhotographers = primaryPhotographers;
    }

    public Set<Photographer> getSecondaryPhotographers() {
        return this.secondaryPhotographers;
    }

    public void setSecondaryPhotographers(Set<Photographer> secondaryPhotographers) {
        this.secondaryPhotographers = secondaryPhotographers;
    }
}
