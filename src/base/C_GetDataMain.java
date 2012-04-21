package base;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import lib.C_Sc2RanksListMember;
import lib.E_Leagues;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class C_GetDataMain {
    
    private static String m_groupUrl = 
            "http://sc2ranks.com/api/clist/9430/all/all/1/0.json?appKey=starcade-gaming.org";
    
    private static String m_tableHeader = 
            "\n<table width=\"95%\">"
            +"\n<tr>"
            +"\n<td><span style=\"font-size: 16pt;\">Name</span></td>"
            +"\n<td><span style=\"font-size: 16pt;\">Rank</span></td>"
            +"\n<td><span style=\"font-size: 16pt;\">Points</span></td>"
            +"\n<td><span style=\"font-size: 16pt;\">Wins</span></td>"
            +"\n<td><span style=\"font-size: 16pt;\">Losses</span></td>"
            +"\n<td><span style=\"font-size: 16pt;\">Race</span></td>"
            +"\n</tr><p></p>";

    /**
     * @param args
     * @throws IOException 
     * @throws ParseException 
     */
    @SuppressWarnings( "unchecked" )
    public static void main( String[] args ) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        
        ContainerFactory containerFactory = C_Sc2Ranks.getContainerFactory();
        LinkedList<Object> json = (LinkedList<Object> )parser.parse(
                C_Sc2Ranks.getData( m_groupUrl ), 
                containerFactory 
        );
        
        ArrayList<C_Sc2RanksListMember> members = new ArrayList<C_Sc2RanksListMember>( 70 );
        for ( Object member : json ) {
            HashMap<Object, Object> memberEntry = (HashMap<Object, Object>)member;
            HashMap<Object, Object> otherMemberData 
                = (HashMap<Object, Object>)((LinkedList<Object>)memberEntry.get( "members" )).get( 0 );

            C_Sc2RanksListMember memberObject = new C_Sc2RanksListMember(
                    (String )memberEntry.get( "division" ),//String division,
                    (Long )memberEntry.get( "division_rank" ),//Number division_rank,
                    E_Leagues.valueOf( (String )memberEntry.get( "league" ) ),//String league,
                    (Long )memberEntry.get( "losses" ),//Number losses,
                    (Long )memberEntry.get( "wins" ),//Number losses,
                    (Long )memberEntry.get( "points" ),//Number points,
                    (String )memberEntry.get( "ratio" ),//String ratio,
                    (Long )otherMemberData.get( "bnet_id" ),  //Number bnet_id,
                    (Long )otherMemberData.get( "character_code" ),   //Number character_code,
                    (String )otherMemberData.get( "fav_race" ),  //String fav_race,
                    (String )otherMemberData.get( "name" ),  //String name,
                    (String )otherMemberData.get( "region" )   //String region
            );
            members.add( memberObject );
        }
        Collections.sort( members );
        
        StringBuilder builder;
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("/home/fm/ranks.txt") );
        bufferedWriter.write( m_tableHeader );
        
        String colorCode = "BGCOLOR=\"#696565\"";
        int i = 0;
        
        for ( C_Sc2RanksListMember c2RanksListMember : members ) {
            if( i % 2 == 0 ) {
                colorCode = "BGCOLOR=\"#373737\"";
            }
            else {
                colorCode = "BGCOLOR=\"#282828\"";
            }
            i++;
            
            builder = new StringBuilder();
            builder.append( "\n<tr " + colorCode + ">" );

            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getName() );
            builder.append( "</td>" );
            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getLeague() );
            builder.append( "</td>" );
            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getPoints() );
            builder.append( "</td>" );
            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getWins() );
            builder.append( "</td>" );
            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getLosses() );
            builder.append( "</td>" );
            builder.append( "\n<td>" );
            builder.append( c2RanksListMember.getFav_race() );
            builder.append( "</td>" );
            builder.append( "\n</tr>" );
            
            
            bufferedWriter.write( builder.toString() );
        }
        bufferedWriter.write( "</table>" );
        bufferedWriter.close();
    }
}
