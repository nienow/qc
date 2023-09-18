package org.example.app.search;

import org.redfx.strange.*;
import org.redfx.strange.gate.Hadamard;
import org.redfx.strange.gate.Oracle;
import org.redfx.strange.gate.ProbabilitiesGate;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;
import org.redfx.strangefx.render.Renderer;

// grover search algorithm
public class SearchAlgorithm {
    private static final int dim = 5;
    private static final int solution = 6;

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        int N = 1 << dim;
        double cnt = Math.PI * Math.sqrt(N) / 4;
        Program p = new Program(dim);
        Step s0 = new Step();
        for (int i = 0; i < dim; i++) {
            s0.addGate(new Hadamard(i));
        }
        p.addStep(s0);
        Oracle oracle = createOracle();
        oracle.setCaption("O");
        Complex[][] dif = createDiffusionMatrix();
        Oracle difOracle = new Oracle(dif);
        difOracle.setCaption("D");
        for (int i = 1; i < cnt; i++) {
            Step s1 = new Step("Oracle " + i);
            s1.addGate(oracle);
            Step s2 = new Step("Diffusion " + i);
            s2.addGate(difOracle);
            p.addStep(s1);
            p.addStep(s2);
            p.addStep(new Step(new ProbabilitiesGate(i)));
        }
        System.out.println(" n = " + dim + ", steps = " + cnt);

        Result res = simulator.runProgram(p);
        for (int i = 1; i < cnt; i++) {
            Complex[] ip0 = res.getIntermediateProbability(3 * i);
            System.out.println("results after step " + i + ": " + ip0[solution].abssqr());
        }

        // result is binary representation of probability
        Renderer.renderProgram(p);
    }

    static Oracle createOracle() {
        int N = 1 << dim; // 2^dim
        Complex[][] matrix = new Complex[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    matrix[i][j] = Complex.ZERO;
                } else {
                    if (i == solution) {
                        matrix[i][j] = Complex.ONE.mul(-1);
                    } else {
                        matrix[i][j] = Complex.ONE;
                    }
                }
            }
        }
        return new Oracle(matrix);
    }

    static Complex[][] createDiffusionMatrix() {
        int N = 1 << dim; // 2^dim
        double N2 = 2. / N;
        Complex[][] answer = new Complex[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer[i][j] = (i == j ? new Complex(N2 - 1) : new Complex(N2));
            }
        }
        return answer;
    }
}
