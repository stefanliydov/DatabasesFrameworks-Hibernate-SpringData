package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ItemJSONImportDTO {

    @Expose
    @Size(min = 3, max=30)
    @NotNull
    private String name;
    @Expose
    @DecimalMin("0.01")
    @NotNull
    private BigDecimal price;
    @Expose
    @Size(min = 3, max=30)
    @NotNull
    private String category;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
