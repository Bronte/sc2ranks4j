package lib;

public class C_Sc2RanksListMember implements Comparable<C_Sc2RanksListMember> {
    
    private String m_division;
    private Long m_division_rank;
    private boolean m_is_random;
    private E_Leagues m_league;
    private Long m_losses;
    private Long m_wins;
    private Long m_points;
    private String m_ratio;
    private Long m_bnet_id;
    private Long m_character_code;
    private String m_fav_race;
    private String m_name;
    private String m_region;
    
    public C_Sc2RanksListMember(
            String division,
            Long division_rank,
            E_Leagues league,
            Long losses,
            Long wins,
            Long points,
            String ratio,
            Long bnet_id,
            Long character_code,
            String fav_race,
            String name,
            String region
    ) {
        m_division = division;
        m_division_rank = division_rank;
        m_league = league;
        m_losses = losses;
        m_wins = wins;
        m_points = points;
        m_ratio = ratio;

        m_bnet_id = bnet_id;
        m_character_code = character_code;
        m_fav_race = fav_race;
        m_name = name;
        m_region = region;
    }
    
    public String getDivision() {
        return m_division;
    }
    
    public Long getDivision_rank() {
        return m_division_rank;
    }
    
    public boolean getIs_random() {
        return m_is_random;
    }
    
    public E_Leagues getLeague() {
        return m_league;
    }
    
    public Long getLosses() {
        return m_losses;
    }
    
    public Long getWins() {
        return m_wins;
    }
    
    public Long getPoints() {
        return m_points;
    }
    
    public String getRatio() {
        return m_ratio;
    }
    
    public Long getBnet_id() {
        return m_bnet_id;
    }
    
    public Long getCharacter_code() {
        return m_character_code;
    }
    
    public String getFav_race() {
        return m_fav_race;
    }
    
    public String getName() {
        return m_name;
    }
    
    public String getRegion() {
        return m_region;
    }

    @Override
    public int compareTo( C_Sc2RanksListMember member ) {
        if( m_league.compareTo( member.getLeague() ) == 0 ) {
            return member.getPoints().compareTo( m_points );
        }
        return m_league.compareTo( member.getLeague() );
    }
}
