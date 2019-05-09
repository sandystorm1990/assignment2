package au.edu.utas.shiduoz.assignment2.models;

public class Entry {
    private int mId;
    private String mMood, mWeather, mActivity, mDescription, mLocation, mMedia, mCreate, mUpdate;

    public void setmId(int id)
    {
        this.mId = id;
    }
    public void setmMood(String mood)
    {
        this.mMood = mood;
    }
    public void setmWeather(String weather)
    {
        this.mWeather = weather;
    }
    public void setmActivity(String activity)
    {
        this.mActivity = activity;
    }
    public void setmLocation(String location)
    {
        this.mLocation = location;
    }
    public void setmMedia(String media)
    {
        this.mMedia = media;
    }
    public void setmDescription(String description)
    {
        this.mDescription = description;
    }
    public void setmCreate(String create)
    {
        this.mCreate =create;
    }
    public void setmUpdate(String update)
    {
        this.mUpdate = update;
    }

    //
    public int getmId()
    {
        return this.mId;
    }
    public String getmMood()
    {
        return this.mMood;
    }
    public String getmWeather()
    {
        return this.mWeather;
    }
    public String getmActivity()
    {
        return this.mActivity;
    }
    public String getmLocation()
    {
        return this.mLocation;
    }
    public String getmMedia()
    {
        return this.mMedia;
    }
    public String getmDescription()
    {
        return this.mDescription;
    }
    public String getmCreate()
    {
        return this.mCreate;
    }
    public String getmUpdate()
    {
        return this.mUpdate;
    }
}
