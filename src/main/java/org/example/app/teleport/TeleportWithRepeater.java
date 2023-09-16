package org.example.app.teleport;

import org.example.util.StrangeUtils;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

public class TeleportWithRepeater {
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(5);

        program.initializeQubit(0, 0.4);

        StrangeUtils.teleport(program, 0, 1, 2);

        StrangeUtils.teleport(program, 2, 3, 4);

        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        Qubit last = qubits[4];

        System.out.println("values: " + zero.measure() + ", " + last.measure());
        Renderer.renderProgram(program);
    }
}
