package toy.tiering.api.rank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import toy.tiering.api.myteam.MyTeam;
import toy.tiering.api.player.Player;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "ranks")
@Builder
@Audited(withModifiedFlag = true)
public class Rank {

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
