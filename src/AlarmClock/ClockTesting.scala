package AlarmClock

import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import scala.concurrent.duration._
import javax.sound.sampled.{AudioSystem, FloatControl}
/*
get the local time till seconds
get local date
start a loop for continous showing

check how to play a sound
make a condition for sound play at the certain time(play sound at alarm time)
give a option to set a alarm in Military time
give a option to delete a alarm
 */

object DateUtil{
  private val hour12Format = new SimpleDateFormat("hh")
  private val hour24Format = new SimpleDateFormat("HH")
  private val minuteFormat = new SimpleDateFormat("mm")
  private val amPmFormat = new SimpleDateFormat("a")
  private val dateFormat = new SimpleDateFormat("dd:MM:yyyy")

  def date: String ={
    val timeNow = Calendar.getInstance.getTime
    dateFormat.format(timeNow)
  }
  def dateByDateMonthYearSeparately: (String, String, String) ={
    val timeNow = Calendar.getInstance.getTime
    val dateArray = dateFormat.format(timeNow).split(":")
    (dateArray(0),dateArray(1),dateArray(2))
  }

  def time: String ={
    val timeNow = Calendar.getInstance.getTime
    s"${hour24Format.format(timeNow)}:${minuteFormat.format(timeNow)}"
  }

  def timeByMinuteAndHour: (String, String) ={
    val timeNow = Calendar.getInstance.getTime
    val hours = hour24Format.format(timeNow)
    val minutes = minuteFormat.format(timeNow)
    (hours, minutes)
  }

  def day: String ={
    val timeNow = Calendar.getInstance.getTime.toString
    timeNow.split(" ")(0)
  }

  def month: String ={
    val timeNow = Calendar.getInstance.getTime.toString
    timeNow.split(" ")(1)
  }

}

object SoundUtil{
  def playSound(file: String): Unit ={
    val audioInputStream = AudioSystem.getAudioInputStream(new File(file))
    val clip = AudioSystem.getClip()
    clip.open(audioInputStream)
    val floatGainControl = clip.getControl(FloatControl.Type.MASTER_GAIN).asInstanceOf[FloatControl]
    //floatGainControl.setValue(gainControl)
    clip.start()
  }

}


object ClockTesting extends App{

  val audioFileName = getClass.getResource("Avicii-Wake-Me-Up.mp3")

  val deadline  = 5.seconds.fromNow
  println(Calendar.getInstance().getTime)
  while(deadline.hasTimeLeft()){
    println(Calendar.getInstance().getTime)
    println(deadline.timeLeft)
    Thread.sleep(1000)
  }

}
