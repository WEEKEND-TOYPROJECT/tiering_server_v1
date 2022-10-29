package toy.tiering.api.rank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.common.domain.BaseEntity;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.player.domain.Player;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "ranks")
@Builder
@Audited(withModifiedFlag = true)
public class Rank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long rankId;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_rank")
    private PlayerRank playerRank;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "myteam_id")
    private MyTeam myTeam;

}
