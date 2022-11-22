package com.javarush.task.task37.task3713.space.crew;

import org.glassfish.jaxb.runtime.v2.runtime.output.FastInfosetStreamWriterOutput;

public class FirstMate extends AbstractCrewMember {
    public FirstMate(AbstractCrewMember.CompetencyLevel competencyLevel) {
        this.competencyLevel = competencyLevel;
    }

    @Override
    protected void doTheJob(String request) {
        System.out.println("The request " + request + " will be handled by first mate, let's not bother the captain with it.");
    }
}
