package toy.tiering.api.myteam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.common.domain.BaseEntity;
import toy.tiering.api.rank.domain.PlayerRank;
import toy.tiering.api.user.domain.User;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "myteams")
@Builder
//@Audited(withModifiedFlag = true)
public class MyTeam extends BaseEntity {

    @Id
    @Column(name = "myteam_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myTeamId;

    @Column(name = "myteam_name")
    private String myTeamName;

    @Enumerated(EnumType.STRING)
    @Column(name = "mysport_type")
    private SportType mySportType;

    @Column(name = "myteam_player")
    private String myTeamPlayer;

    @Column(name = "myteam_player_photo")
    private String myTeamPlayerPhoto;

    @Column(name = "myteam_player_position")
    private String myTeamPlayerPosition;

    @Column(name = "myteam_player_stat")
    private String myTeamPlayerStat;

    @Column(name = "myteam_player_season")
    private String myTeamPlayerSeason;

    @Column(name = "myteam_player_nation")
    private String myTeamPlayerNation;

    @Column(name = "myteam_logo")
    private String myTeamLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_rank")
    private PlayerRank playerRank;

    @Column(name = "nickname")
    private String nickName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void changeMyTeam(String myTeamName, String myTeamPlayer, String myTeamPlayerPhoto, String myTeamPlayerPosition, String myTeamPlayerStat, String myTeamPlayerSeason, String myTeamPlayerNation) {
        this.myTeamName = myTeamName;
        this.myTeamPlayer = myTeamPlayer;
        this.myTeamPlayerPhoto = myTeamPlayerPhoto;
        this.myTeamPlayerPosition = myTeamPlayerPosition;
        this.myTeamPlayerStat = myTeamPlayerStat;
        this.myTeamPlayerSeason = myTeamPlayerSeason;
        this.myTeamPlayerNation = myTeamPlayerNation;
    }


}
