package com.treasurehunt;

public class Progress
{
    private String roundnumber,problemnumber,score,timestamp, id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoundnumber()
    {
        return roundnumber;
    }

    public void setRoundnumber(String roundnumber)
    {
        this.roundnumber = roundnumber;
    }

    public String getProblemnumber()
    {
        return problemnumber;
    }

    public void setProblemnumber(String problemnumber)
    {
        this.problemnumber = problemnumber;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score){
        this.score=score;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }
}
