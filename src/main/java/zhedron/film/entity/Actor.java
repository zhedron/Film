package zhedron.film.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "actors")
@Data
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    @Size (min = 3, max = 20, message = "name could be 5 word min and max 20")
    private String name;

    @Column
    @Size (max = 25, message = "last name could be less 25 words")
    private String lastName;

    @Column
    private String birthDay;

    @Column
    private int age;

    @OneToMany
    @JoinColumn (name = "movie_id")
    private List<Movie> movies;
}
