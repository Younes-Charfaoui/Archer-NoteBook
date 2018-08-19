package me.mxcsyounes.archernotebook.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import me.mxcsyounes.archernotebook.database.entities.Score;
import me.mxcsyounes.archernotebook.repositories.ScoresRepository;

public class ScoresViewModel extends AndroidViewModel {

    private LiveData<List<Score>> mAllScores;
    private ScoresRepository mRepository;

    public ScoresViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ScoresRepository(application);
        mAllScores = mRepository.getScores();
    }

    public LiveData<List<Score>> getAllScores() {
        return mAllScores;
    }

    public void deleteAllScores() {
        mRepository.deleteAllScores();
    }

    public void deleteScore(Score score) {
        mRepository.deleteScore(score);
    }

    public void updateScore(Score score) {
        mRepository.updateScore(score);
    }

    public void insertScore(Score score) {
        mRepository.insertScore(score);
    }

}
