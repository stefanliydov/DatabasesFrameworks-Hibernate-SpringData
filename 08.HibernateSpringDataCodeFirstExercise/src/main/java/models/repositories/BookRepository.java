package models.repositories;

import models.entities.Book;
import models.entities.ReducedBook;
import models.enums.AgeRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(int ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(int editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(double price, double price2);

  // @Query("select b FROM Book as b WHERE b.releaseDate.getYear NOT LIKE :year")
  // List<Book> findAllByYearDifferent(@Param("year") int releaseYear);

    List<Book> findAllByReleaseDateBefore(Date releaseDate);

    List<Book> findAllByTitleContaining(String title);

    @Query("Select count(b) FROM Book as b WHERE length(b.title)>= :num ")
    int countAllByTitleGreaterThan(@Param("num") int length);

    @Query("SELECT new models.entities.ReducedBook(b.title,b.editionType,b.ageRestriction,b.price) FROM Book b WHERE b.title = :title")
    ReducedBook getAllProjections(@Param("title") String title);

    List<Book> findAllByReleaseDateAfter(Date date);

   // @Query("UPDATE Book as b set b.copies = b.copies+:num Where b.bookId=:bookId ")
   // @Modifying
   // void increaseCopies(@Param("bookId") long bookId,@Param("num") int num);

    List<Book> findAllByCopiesLessThan(int size);

    @Query("delete from Book b where b.copies< :copies")
    @Modifying
    void deleteWithCopiesLessThan(@Param("copies") int copies);
}
