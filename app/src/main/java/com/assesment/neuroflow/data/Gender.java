package com.assesment.neuroflow.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gender implements Comparable<Gender>{
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("date_created")
    @Expose
    private long date_created;
    @SerializedName("name")
    @Expose
    private String name;

    public int getScore ()
    {
        return score;
    }

    public void setScore (int score)
    {
        this.score = score;
    }

    public long getDate_created ()
    {
        return date_created;
    }

    public void setDate_created (long date_created)
    {
        this.date_created = date_created;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [score = "+score+", date_created = "+date_created+", name = "+name+"]";
    }

    @Override
    public int compareTo(Gender user) {
        return (this.getDate_created() > user.getDate_created() ? -1 :
                this.getDate_created() == user.getDate_created() ? 0:1);
    }
}
