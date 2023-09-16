package org.example.app.gates;

import com.sun.javafx.binding.StringFormatter;
import org.redfx.strange.*;
import org.redfx.strange.gate.Cnot;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;

/**
 * The cnot gate is a two-qubit gate. It flips the second qubit if the first qubit is 1.
 */
public class CnotExample {
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(2);
        Step step = new Step();
        step.addGate(new Cnot(0, 1));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        Qubit one = qubits[1];
        System.out.println(StringFormatter.format("zero: %d, one: %d", zero.measure(), one.measure()).getValue());
    }
}
