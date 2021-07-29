package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private int mark;
}
