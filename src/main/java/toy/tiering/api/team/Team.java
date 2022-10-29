package toy.tiering.api.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.myteam.SportType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "teams")
@Builder
@Audited(withModifiedFlag = true)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name")
    private String teamName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport_type")
    private SportType sportType;

    @Column(name = "team_logo")
    private String teamLogo;
}
