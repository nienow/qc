package org.example.app.teleport;

import org.example.util.StrangeUtils;
import org.redfx.strange.Program;
import org.redfx.strange.QuantumExecutionEnvironment;
import org.redfx.strange.Qubit;
import org.redfx.strange.Result;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

// The teleportation algorithm is a quantum algorithm that can be used to transfer a qubit between two parties.
public class Teleport {
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(3);

        program.initializeQubit(0, 0.4);

        StrangeUtils.teleport(program, 0, 1, 2);

        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        Qubit one = qubits[1];
        Qubit two = qubits[2];

        System.out.println("values: " + zero.measure() + ", " + one.measure() + ", " + two.measure());
        Renderer.renderProgram(program);
    }
}
