package vms.android.antibodyresultlist

import android.app.Application

class AntibodyResultListApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        StudentRepository.initialize(this)
    }

}