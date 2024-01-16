package com.example.composecodelab.ui.data

/**
 * @author Created by chanhypark on 1/11/24
 **/
import com.example.composecodelab.R
import com.example.composecodelab.ui.model.Affirmation
import com.example.composecodelab.ui.model.LandScape

class DataSource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2),
            Affirmation(R.string.affirmation3, R.drawable.image3),
            Affirmation(R.string.affirmation4, R.drawable.image4),
            Affirmation(R.string.affirmation5, R.drawable.image5),
            Affirmation(R.string.affirmation6, R.drawable.image6),
            Affirmation(R.string.affirmation7, R.drawable.image7),
            Affirmation(R.string.affirmation8, R.drawable.image8),
            Affirmation(R.string.affirmation9, R.drawable.image9),
            Affirmation(R.string.affirmation10, R.drawable.image10)
        )
    }


    fun loadLandScape(): List<LandScape> {
        return listOf(
            LandScape(
                imgRes = R.drawable.image1,
                title = "title1",
                description = R.string.affirmation1
            ),
            LandScape(
                imgRes = R.drawable.image2,
                title = "title2",
                description = R.string.affirmation2
            ),
            LandScape(
                imgRes = R.drawable.image3,
                title = "title3",
                description = R.string.affirmation3
            ),
            LandScape(
                imgRes = R.drawable.image4,
                title = "title4",
                description = R.string.affirmation4
            ),
            LandScape(
                imgRes = R.drawable.image5,
                title = "title5",
                description = R.string.affirmation5
            ),
            LandScape(
                imgRes = R.drawable.image6,
                title = "title6",
                description = R.string.affirmation6
            ),
            LandScape(
                imgRes = R.drawable.image7,
                title = "title7",
                description = R.string.affirmation7
            ),
            LandScape(
                imgRes = R.drawable.image8,
                title = "title8",
                description = R.string.affirmation8
            ),
            LandScape(
                imgRes = R.drawable.image5,
                title = "title5",
                description = R.string.affirmation5
            ),
            LandScape(
                imgRes = R.drawable.image8,
                title = "title8",
                description = R.string.affirmation8
            ),
            LandScape(
                imgRes = R.drawable.image3,
                title = "title3",
                description = R.string.affirmation3
            ),
            LandScape(
                imgRes = R.drawable.image9,
                title = "title9",
                description = R.string.affirmation9
            ),
            LandScape(
                imgRes = R.drawable.image10,
                title = "title10",
                description = R.string.affirmation10
            )
            )
    }
}