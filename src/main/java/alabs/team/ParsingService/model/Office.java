package alabs.team.ParsingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_offices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phoneSet = new HashSet<Phone>();

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Currency> currencySet  = new HashSet<Currency>();
}