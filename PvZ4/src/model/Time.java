package model;

public class Time {
    public static int FPS = 4;
    public final static int totalFrames = 4*60*3; //fps*3*60 default 3 min

    private int minute;
    private int second;
    private int frame;
    private int framesCount;

    public Time (int minute, int second){
        this.minute = minute;
        this.second = second;
        this.frame = FPS - 1;

        this.framesCount = totalFrames;
    }

    public Time(String currentTime){
        String[] times = currentTime.split(":");
        minute = Integer.parseInt(times[0]);
        second = Integer.parseInt(times[1]);
        this.frame = FPS;

        this.framesCount = totalFrames;
    }

    public String getCurrentTime(){
        if (this.getRemainingSeconds()<=0) return "Time Out";
        return minute+":"+second;
    }

    public int getRemainingSeconds(){
        return 60*minute+second;
    }

    public int getRemainingFrames(){
        return framesCount;
    }

    public void oneFramePassed(){
        this.frame--;
        this.framesCount--;

        if (this.frame<=0){
            this.second--;
            this.frame = FPS;
            if (second<=0){
                this.minute--;
                this.second = 59;
                if (this.minute<=0){
                    System.out.println("Time Out");
                }
            }
        }
    }

}
