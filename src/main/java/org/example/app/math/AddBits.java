package org.example.app.math;

import org.example.util.StrangeUtils;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.gate.Toffoli;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class AddBits {
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(3);
        StrangeUtils.initTo1(program, 0, 1);

        Step carryOverStep = new Step();
        carryOverStep.addGate(new Toffoli(0, 1, 2));

        Step addStep = new Step();
        addStep.addGate(new Cnot(0, 1));

        program.addSteps(carryOverStep, addStep);

        Result result = simulator.runProgram(program);
        StrangeUtils.measureBits(result);

        Renderer.renderProgram(program);
    }
}
