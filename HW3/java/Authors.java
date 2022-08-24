import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "authors")

public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "first")
    private String first;

    @Column(name = "last")
    private String last;

    @OneToMany(mappedBy = "authors", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<BookDetails> bookDetails = new ArrayList<>();

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getFirst(){
        return first;
    }
    public void setFirst(String first){
        this.first = first;
    }

    public String getLast(){
        return last;
    }
    public void setLast(String last){
        this.last = last;
    }

    public List<BookDetails> getBookDetails() {
        return bookDetails;
    }
    public void setBookDetails(List<BookDetails> bd){
        this.bookDetails = bd;
    }
    public void addBookDetails(BookDetails  bd){
        this.bookDetails.add(bd);
    }

}
