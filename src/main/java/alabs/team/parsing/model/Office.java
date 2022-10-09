package alabs.team.parsing.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "t_offices")
@Setter
@Getter
@ToString
public class Office {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private Long date;

    @Column(name = "time_update")
    private Timestamp time_update;

    @OneToMany(mappedBy = "office", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phoneSet;

    @OneToMany(mappedBy = "office", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Currency> currencySet;
}