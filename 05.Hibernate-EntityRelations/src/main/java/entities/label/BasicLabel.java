package entities.label;

import entities.shampoo.BasicShampoo;

public class BasicLabel implements Label {

    private int id;

    private String title;

    private BasicShampoo shampoo;

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public BasicShampoo getShampoo() {
        return this.shampoo;
    }
    @Override
    public void setShampoo(BasicShampoo shampoo) {
        this.shampoo = shampoo;
    }
}
