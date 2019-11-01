package com.example.TheBeatles

class Covers
{
    private var covername : String = ""
    private var releaseDate : String = ""
    private var pic : String = ""

    constructor(covername : String, releaseDate : String, pic : String)
    {
        this.covername = covername
        this.releaseDate = releaseDate
        this.pic = pic
    }

    public fun setCoverName(covername : String)
    {
        this.covername = covername
    }

    public fun getCoverName() : String
    {
        return covername
    }

    public fun getDate() : String
    {
        return releaseDate
    }

    public fun setDate(releaseDate : String)
    {
        this.releaseDate = releaseDate
    }

    public fun setPic(pic : String)
    {
        this.pic = pic
    }

    public fun getPic() : String
    {
        return pic
    }

}