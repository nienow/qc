package org.example.util;

import org.redfx.strange.Program;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.*;

import java.util.Arrays;

public class StrangeUtils {
    public static void initTo1(Program program, int... qubit) {
        Step step = new Step();
        for (int i : qubit) {
            step.addGate(new X(i));
        }
        program.addStep(step);
    }

    public static Integer[] measureBits(Result result) {
        Qubit[] qubits = result.getQubits();
        Integer[] measurements = Arrays.stream(qubits).map(Qubit::measure).toArray(Integer[]::new);
        System.out.println("measurements: " + Arrays.toString(measurements));
        return measurements;
    }

    public static void entangle(Program program, int a, int b) {
        Step step1 = new Step();
        step1.addGate(new Hadamard(a));
        Step step2 = new Step();
        step2.addGate(new Cnot(a, b));
        program.addStep(step1);
        program.addStep(step2);
    }

    public static void teleport(Program program, int a, int b, int c) {
        StrangeUtils.entangle(program, b, c);

        Step step1 = new Step();
        step1.addGate(new Cnot(a, b));
        program.addStep(step1);

        Step step2 = new Step();
        step2.addGate(new Hadamard(a));
        program.addStep(step2);

        Step step3 = new Step();
        step3.addGate(new Measurement(a));
        step3.addGate(new Measurement(b));
        program.addStep(step3);

        Step step4 = new Step();
        step4.addGate(new Cnot(b, c));
        program.addStep(step4);

        Step step5 = new Step();
        step5.addGate(new Cz(a, c));
        program.addStep(step5);
    }
}
