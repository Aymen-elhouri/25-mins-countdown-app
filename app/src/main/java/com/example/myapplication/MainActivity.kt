package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime
import kotlin.time.days

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    @OptIn(ExperimentalTime::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launchbtn = findViewById<Button>(R.id.button)
        val pausebtn = findViewById<Button>(R.id.button2)
        val resetbtn = findViewById<Button>(R.id.button3)
        val clock = findViewById<TextView>(R.id.textView)

        var righttext = "00"
        var lefttext = "25"
        var a = ":0"
        var b = ":"
        fun set(){
            if(righttext.length==2){
                clock.text = lefttext+b+righttext
            }
            else{
                clock.text = lefttext+a+righttext
            }
        }


        var s:Int = 59
        var m:Int = 25
        var pause = false
        var reset = false
            pausebtn.setOnClickListener(){
                GlobalScope.launch {
                    pause = true
                }

            }

            resetbtn.setOnClickListener(){
                GlobalScope.launch {
                    reset = true
                }

            }
            launchbtn.setOnClickListener(){
                if(reset==true){
                     s= 59
                     m = 25
                }
                lefttext=m.toString()
                righttext=s.toString()
                pause=false
                reset=false
                var i =1

                GlobalScope.launch {
                    while ( i<=1500 && pause==false && reset==false){
                        i=i+1
                        delay(1000)
                        righttext=s.toString()
                        s = s -1
                        set()
                        if (s==-1){
                            s=59
                            set()
                        }
                        if (s==58){
                            m=m-1
                            lefttext=m.toString()
                            set()
                        }
                    }
                    while (pause==true){
                        if (reset==true){
                            righttext = "00"
                            lefttext = "25"
                            clock.text = lefttext+b+righttext

                        }
                    }
                    if (reset==true){
                        righttext = "00"
                        lefttext = "25"
                        clock.text = lefttext+b+righttext

                    }

                }

                }
            }



    }
