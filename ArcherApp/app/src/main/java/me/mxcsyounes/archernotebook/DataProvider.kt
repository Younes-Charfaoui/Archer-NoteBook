package me.mxcsyounes.archernotebook

import me.mxcsyounes.archernotebook.database.entities.Adjustment
import java.util.*

object DataProvider {

    private val adjustmentList = ArrayList<Adjustment>()

    init {
        adjustmentList.add(Adjustment(1, 2, "6.2", "1.3", Date(),
                null!!, "Hello world"))
        adjustmentList.add(Adjustment(1, 1, "11", "1.2", Date(),
                null!!, "Hello algeria"))
        adjustmentList.add(Adjustment(1, 3, "8", "4", Date(),
                null!!, "Hello merrakch"))
        adjustmentList.add(Adjustment(1, 4, "6", "12", Date(), null!!,
                "Hello tiaret"))
    }
}
