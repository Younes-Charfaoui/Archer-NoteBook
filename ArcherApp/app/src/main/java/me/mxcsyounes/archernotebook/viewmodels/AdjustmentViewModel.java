package me.mxcsyounes.archernotebook.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;



import java.util.List;

import me.mxcsyounes.archernotebook.repositories.AdjustmentsRepository;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AdjustmentViewModel extends AndroidViewModel {

    private LiveData<List<Adjustment>> mAllAdjustment;
    private AdjustmentsRepository mRepository;

    public AdjustmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AdjustmentsRepository(application);
        mAllAdjustment = mRepository.getAllAdj();
    }

    public LiveData<List<Adjustment>> getAllAdjustments() {
        return mAllAdjustment;
    }

    public void insertAdjustment(Adjustment adjustment) {
        mRepository.insertAdjustment(adjustment);
    }

    public void deleteAdjustment(Adjustment adjustment) {
        mRepository.deleteAdjustment(adjustment);
    }

    public void deleteAllAdjustments() {
        mRepository.deleteAllAdjustments();
    }
}
