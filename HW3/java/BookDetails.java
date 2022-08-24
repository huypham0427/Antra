import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookDetails")

public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Authors authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Books books;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public Authors getAuthors(){
        return authors;
    }
    public void setAuthors(Authors authors){
        this.authors = authors;
    }

    public Books getBooks(){
        return books;
    }
    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public  boolean  equals ( Object  o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return  false ;
        }
        BookDetails temp = (BookDetails) o;
        return temp.id == id && Objects.equals(temp.authors, authors) && Objects.equals(temp.books, books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authors, books);
    }
}
