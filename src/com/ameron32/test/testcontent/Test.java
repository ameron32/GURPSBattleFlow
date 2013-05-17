
package com.ameron32.test.testcontent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ameron32.gurpsbattleflow.Importer;
import com.ameron32.gurpsbattleflow.Importer.ImportType;
import com.ameron32.gurpsbattleflow.Step;

public class Test {

    private static List<Step> allSteps;
    private static List<Advantage> allAdvantages;
    private static List<Skill> allSkills;
    private static boolean continueRunning = true;

    private static Scanner s;

    public static void main(String[] args) {
        prepare();
        choose();
    }

    private static void prepare() {
        Importer ii = new Importer();
        allSteps = ii.readCSVIntoList(
                "C:\\usr\\combatflowchart.csv", new ArrayList<Step>(),
                ImportType.FlowChart);
        allAdvantages = ii.readCSVIntoList(
                "C:\\usr\\adv153.csv", new ArrayList<Advantage>(),
                ImportType.Advantage);
        allSkills = ii.readCSVIntoList(
                "C:\\usr\\skills153.csv", new ArrayList<Skill>(),
                ImportType.Skill);

        s = new Scanner(System.in);
    }

    private static void choose() {
        System.out.println("Steps: 1. Advantages: 2. Skills: 3.");
        int i = s.nextInt();
        switch (i) {
            case 0:
                continueRunning = false;
                break;
            case 1:
                while (continueRunning) {
                    steps();
                }
                break;
            case 2:
                while (continueRunning) {
                    advantages();
                }
                break;
            case 3:
                while (continueRunning) {
                    skills();
                }
                break;
        }
    }

    private static void steps() {
        System.out.println("Number between 1 and " + (allSteps.size()));
        int i = s.nextInt() - 1;
        if (i == -1) {
            continueRunning = false;
            return;
        }
        System.out.println(allSteps.get(i).toString());
    }

    private static void advantages() {
        System.out.println("Number between 1 and " + (allAdvantages.size()));
        int i = s.nextInt() - 1;
        if (i == -1) {
            continueRunning = false;
            return;
        }
        System.out.println(allAdvantages.get(i).toString());
    }

    private static void skills() {
        System.out.println("Number between 1 and " + (allSkills.size()));
        int i = s.nextInt() - 1;
        if (i == -1) {
            continueRunning = false;
            return;
        }
        System.out.println(allSkills.get(i).toString());
    }

}
