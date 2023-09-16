package org.example.app.gates;

import org.redfx.strange.*;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;

/*
 * The Hadamard gate is a single-qubit gate. It puts the qubit in a superposition state.
 */
public class HadamardExample {
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        int value = zero.measure();
        System.out.println("value: " + value);
//        Renderer.renderProgram(program);
    }

}
