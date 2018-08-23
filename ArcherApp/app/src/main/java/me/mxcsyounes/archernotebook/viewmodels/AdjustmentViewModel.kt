package me.mxcsyounes.archernotebook.viewmodels;


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.mxcsyounes.archernotebook.database.entities.Adjustment
import me.mxcsyounes.archernotebook.repositories.AdjustmentsRepository

class AdjustmentViewModel(application: Application) : AndroidViewModel(application) {

    val mAllAdjustment: LiveData<MutableList<Adjustment>>
    private val mRepository: AdjustmentsRepository = AdjustmentsRepository(application)

    init {
        mAllAdjustment = mRepository.allAdj
    }


    fun insertAdjustment(adjustment: Adjustment) =
            mRepository.insertAdjustment(adjustment);


    fun deleteAdjustment(adjustment: Adjustment) =
            mRepository.deleteAdjustment(adjustment);


    fun deleteAllAdjustments() =
            mRepository.deleteAllAdjustments();

}
