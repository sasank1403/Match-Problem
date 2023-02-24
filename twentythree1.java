import java.util.*;
import java.util.stream.Collectors;
public class twentythree1
{
    public static void main(String[] args) throws Exception {
        ArrayList<Team> teams=new ArrayList<>();
        teams.add(new Team("T1"));
        teams.add(new Team("T2"));
        teams.add(new Team("T3"));
        teams.add(new Team("T4"));
        teams.add(new Team("T5"));
        List<Match> matches=Scheduler.createSchedule(teams);
        System.out.println(matches);
        Simulator.playMatches(matches);
        Simulator.showPointsTable(teams,matches);
    }
}
class Match
{
    private Team team1;
    private Team team2;
    private Team winner;
    private Team loser;
    Match(Team team,Team team3)
    {
        this.team1=team;
        this.team2=team3;
    }
    public Team getTeam1()
    {
        return team1;
    }
    public Team getTeam2()
    {
        return team2;
    }
    public Team getWinner()
    {
        return winner;
    }
    public void setWinner(Team winner)
    {
        this.winner=winner;
    }
    public Team getLoser()
    {
        return loser;
    }
    public void setLoser(Team loser)
    {
        this.loser=loser;
    }
    @Override
    public String toString()
    {
        String matchDescription = team1 +" vs "+team2;
        if(winner!=null)
        {
            String result="\n winner="+this.winner.toString()+"Loser="+this.loser.toString();
            matchDescription+=result;
        }
        return matchDescription;
    }
}
class Scheduler
{
    public static List<Match> createSchedule(List<Team> teams)
    {
        List<Match> matches=new ArrayList<>();
        for(int i=0;i<teams.size();i++)
        {
            for(int j=i+1;j<teams.size();j++)
            {
                Match match=new Match(teams.get(i), teams.get(j));
                matches.add(match);
            }
        }
        return matches;
    }
}
class Simulator
{
    /**
     * @param matches
     */
    public static void playMatches(List<Match> matches)
    {
        for(Match match:matches)
        {
            int random=(int)((Math.random()*10)+1);
            if(random%2==0)
            {
                match.setWinner(match.getTeam1());
                match.setLoser(match.getTeam2());
            }
            else
            {
                match.setWinner(match.getTeam2());
                match.setLoser(match.getTeam1());
            }
        }
    }
    public static void showPointsTable(List<Team> teams,List<Match> matches)
    {
        for(Team team:teams)
        {
            int wonGames=matches.stream().filter(m ->m.getWinner().equals(team)).collect(Collectors.toList()).size();
            int lostGames=matches.stream().filter(m ->m.getLoser().equals(team)).collect(Collectors.toList()).size();
            System.out.println("------------");
            System.out.println(team);
            System.out.println("won matches: "+wonGames);
            System.out.println("lost matches: "+lostGames);
            System.out.println("------------");
        }
    }
}
class Team
{
    private String name;
    Team(String name)
    {
        this.name=name;
    }
    @Override
    public String toString()
    {
        return name;
    }
    public boolean equals(Object obj)
    {
        return this.name.equals(((Team)obj).name);
    }
}