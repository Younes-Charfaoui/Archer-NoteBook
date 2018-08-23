package me.mxcsyounes.archernotebook.asynck

import android.support.annotation.IntDef


const val INSERT = 1
const val UPDATE = 2
const val DELETE = 3
const val DELETE_ALL = 4

@IntDef(UPDATE, DELETE, DELETE_ALL, INSERT)
@Retention(AnnotationRetention.SOURCE)
annotation class DatabaseOperation

