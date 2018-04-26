package app.exam.domain.dto.json;


import com.google.gson.annotations.Expose;

import java.util.List;

public class OrderJSONExportDTO {

    @Expose
    private String customer;
    @Expose
    private ItemJSONExportDTO[] items;

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ItemJSONExportDTO[] getItems() {
        return this.items;
    }

    public void setItems(ItemJSONExportDTO[] items) {
        this.items = items;
    }
}
