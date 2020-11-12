<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/backgroundActivity"
    tools:context=".MainActivity">


    <TextView
        android:id="@+id/tvBalls"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text=""
        android:textSize="50sp"
        android:textColor="#fff"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>

    <ImageView
        android:id="@+id/ivRaceta"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:src="@drawable/ic_balls"
        app:layout_constraintLeft_toRightOf="@+id/tvBalls"
        app:layout_constraintTop_toTopOf="parent"/>



    <TextView
        android:id="@+id/namelogo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Game"
        android:textColor="#ffb64f"
        android:textSize="70sp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/ivRaceta"
        android:fontFamily="@font/bungee_inline"
        android:layout_marginTop="30dp"/>

    <ImageView
        android:id="@+id/ivWorld"
        android:layout_width="100dp"
        android:layout_height="109dp"
        android:focusable="true"
        android:src="@drawable/ic_world"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintBottom_toBottomOf="@+id/btnLevel1"
        android:layout_marginRight="40dp"/>

    <ImageView
        android:id="@+id/ivRaceta1"
        android:layout_width="329dp"
        android:layout_height="379dp"
        android:src="@drawable/ic_raceta"
        android:focusable="true"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"/>