package toy.tiering.api.myteam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.common.domain.BaseEntity;
import toy.tiering.api.user.domain.User;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "myteams")
@Builder
@Audited(withModifiedFlag = true)
public class MyTeam extends BaseEntity {

    @Id
    @Column(name = "myteam_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myTeamId;

    @Column(name = "myteam_name")
    private String myTeamName;

    @Enumerated(EnumType.STRING)
    @Column(name = "mysport_type", nullable = false)
    private SportType mySportType;

    @Column(name = "myteam_player", nullable = false)
    private String myTeamPlayer;

    @Column(name = "myteam_player_photo", nullable = false)
    private String myTeamPlayerPhoto;

    @Column(name = "myteam_player_position", nullable = false)
    private String myTeamPlayerPosition;

    @Column(name = "myteam_player_stat", nullable = false)
    private String myTeamPlayerStat;

    @Column(name = "myteam_player_season", nullable = false)
    private String myTeamPlayerSeason;

    @Column(name = "myteam_player_nation", nullable = false)
    private String myTeamPlayerNation;

    @Column(name = "myteam_logo", nullable = false)
    private String myTeamLogo;

    @Column(name = "nickname")
    private String nickName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
