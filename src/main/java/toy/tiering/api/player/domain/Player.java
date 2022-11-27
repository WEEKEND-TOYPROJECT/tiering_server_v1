package toy.tiering.api.player.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.common.domain.BaseEntity;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.domain.SportType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "players")
@Builder
//@Audited(withModifiedFlag = true)
public class Player extends BaseEntity {

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

    @Column(name = "player_team")
    private String playerTeam;

    @Column(name = "player_team_logo")
    private String playerTeamLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport_type")
    private SportType sportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myteam_id")
    private MyTeam myTeam;


    public void changePlayer(String playerName, String playerPhoto, String playerPosition, String playerStat, String playerSeason, String playerNation, String playerTeam, String playerTeamLogo) {
        this.playerName = playerName;
        this.playerPhoto = playerPhoto;
        this.playerPosition = playerPosition;
        this.playerStat = playerStat;
        this.playerSeason = playerSeason;
        this.playerNation = playerNation;
        this.playerTeam = playerTeam;
        this.playerTeamLogo = playerTeamLogo;
    }
}


