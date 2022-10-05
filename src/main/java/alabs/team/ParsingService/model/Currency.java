package alabs.team.ParsingService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_currencies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "usd_purchase")
    private double usd_purchase;

    @Column(name = "usd_sale")
    private double usd_sale;

    @Column(name = "eur_purchase")
    private double eur_purchase;

    @Column(name = "eur_sale")
    private double eur_sale;

    @Column(name = "rub_purchase")
    private double rub_purchase;

    @Column(name = "rub_sale")
    private double rub_sale;

    @Column(name = "time_update")
    private Timestamp time_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
}