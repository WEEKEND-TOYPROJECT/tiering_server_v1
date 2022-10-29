package toy.tiering.api.player;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.team.Team;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "players")
@Builder
@Audited(withModifiedFlag = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_photo")
    private String playerPhoto;

    @Column(name = "player_position")
    private String playerPosition;

    @Column(name = "player_stat")
    private String playerStat;

    @Column(name = "player_season")
    private String playerSeason;

    @Column(name = "player_nation")
    private String playerNation;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
