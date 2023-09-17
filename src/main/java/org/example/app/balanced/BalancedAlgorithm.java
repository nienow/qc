package org.example.app.balanced;

import org.example.util.MatrixUtils;
import org.example.util.StrangeUtils;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Result;
import org.redfx.strange.Step;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.Oracle;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

// deutsch-jozsa algorithm
public class BalancedAlgorithm {

    static final int N = 5;

    public static void main(String[] args) {
        int matrixDimensions = 2 << N; // 2^N power

        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(N + 1);
//        StrangeUtils.initTo1(program, 0, 1);

        program.addStep(new Step(new X(N)));

        Step hadamardStep = new Step();
        for (int i = 0; i < N + 1; i++) {
            hadamardStep.addGate(new Hadamard(i));
        }
        program.addStep(hadamardStep);

        program.addStep(new Step(new Oracle(MatrixUtils.cnotMatrix(matrixDimensions))));

        Step hadamardStep2 = new Step();
        for (int i = 0; i < N; i++) {
            hadamardStep2.addGate(new Hadamard(i));
        }
        program.addStep(hadamardStep2);

        Result result = simulator.runProgram(program);
        StrangeUtils.measureBits(result);

        Renderer.renderProgram(program);
    }

}
