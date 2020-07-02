package com.griddynamics.testwearapp

import com.griddynamics.testwearapp.detail.FeedbackData
import com.griddynamics.testwearapp.main.MainData

const val pgOverview = "Central Park of Culture and Rest Gorky in Kharkov is the best theme park in" +
    " Ukraine, one of the best in Europe. This is confirmed by a number of awards and positive" +
    " feedback from our visitor."

const val  stubFeedbackText =
    "A huge amount of entertainment for different ages (from kids to adults)," +
        " sane prices. For families with children this is probably the best place in Kharkov." +
        " It's nice to take a walk and take a photo."

val stubImage = arrayListOf(
    R.mipmap.pg1,
    R.mipmap.pg2,
    R.mipmap.pg3,
    R.mipmap.pg4,
    R.mipmap.pg5
)

val stubs = arrayListOf(
    MainData(0, "Gorky Central Park", pgOverview, stubImage),
    MainData(1, "Shevchenko park", pgOverview, stubImage),
    MainData(2, "Kharkov Dolphinarium", pgOverview, stubImage),
    MainData(3, "Strilka Square", pgOverview, stubImage),
    MainData(4,"Kharkov Historical Museum", pgOverview, stubImage),
    MainData(4,"Constitution Square", pgOverview, stubImage)

)

val stubFeedBack = arrayListOf(
    FeedbackData("Alexandra Sh.", stubFeedbackText),
    FeedbackData("Vladislav D.", stubFeedbackText),
    FeedbackData("Ihor B.", stubFeedbackText),
    FeedbackData("Objora", stubFeedbackText)
)
