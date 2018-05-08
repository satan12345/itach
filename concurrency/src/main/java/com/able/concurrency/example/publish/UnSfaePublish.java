package com.able.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class UnSfaePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public void setStates(String[] states) {
        this.states = states;
    }

    public static void main(String[] args) {
        UnSfaePublish unSfaePublish = new UnSfaePublish();

        log.info("{}", Arrays.toString(unSfaePublish.getStates()));

        unSfaePublish.getStates()[0] = "d";

        log.info("{}", Arrays.toString(unSfaePublish.getStates()));

    }
}
