package entities.label;

import entities.shampoo.BasicShampoo;

public interface Label {

    int getId();

    void setId(int id);

    String getTitle() ;

    void setTitle(String title) ;

    BasicShampoo getShampoo();

    void setShampoo(BasicShampoo shampoo) ;
}
