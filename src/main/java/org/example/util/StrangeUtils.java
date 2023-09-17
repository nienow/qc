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
        program.addStep(new Step(new Hadamard(a)));
        program.addStep(new Step(new Cnot(a, b)));
    }

    public static void teleport(Program program, int a, int b, int c) {
        StrangeUtils.entangle(program, b, c);

        program.addStep(new Step(new Cnot(a, b)));
        program.addStep(new Step(new Hadamard(a)));
        program.addStep(new Step(new ProbabilitiesGate(a)));
        program.addStep(new Step(new Measurement(a), new Measurement(b)));
        program.addStep(new Step(new Cnot(b, c)));
        program.addStep(new Step(new Cz(a, c)));
    }
}
