package com.example.david.twistthemobile;

/**
 * Created by David on 4/17/2017.
 */

public class Player {
    String name;
    String[] statuses = {"Scrambling", "Inspecting", "Solving"}; //set current_status = 3 when solved
    int current_status; // 0,1,2 - statuses[current_status] ; 3 - stopWatch.getElapsedTime()
    StopWatch stopWatch;

}
