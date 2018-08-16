package me.mxcsyounes.archernotebook.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;



import java.util.List;

import me.mxcsyounes.archernotebook.DataRepository;
import me.mxcsyounes.archernotebook.database.entities.Adjustment;

public class AdjustmentViewModel extends AndroidViewModel {

    private LiveData<List<Adjustment>> mAllNotes;
    private DataRepository mRepository;

    public AdjustmentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mAllNotes = mRepository.getAllAdj();
    }

    public LiveData<List<Adjustment>> getAllNotes() {
        return mAllNotes;
    }

    public void insertNote(Adjustment adjustment) {
        mRepository.insertNote(adjustment);
    }

    public void deleteNote(Adjustment adjustment) {
        mRepository.deleteNote(adjustment);
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNote();
    }
}
