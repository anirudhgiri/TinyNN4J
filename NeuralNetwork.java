//Written by Anirudh Giri
//Inspited by the book Make Your Own Neural Network by Tariq Rashid and
//youtube lessons by Daniel Shiffman from The Coding Train

class NeuralNetwork {
    int inputs;
    int hiddens;
    int outputs;

    Matrix weightsIH;
    Matrix weightsHO;

    Matrix biasH;
    Matrix biasO;

    NeuralNetwork(int NumberOfInputs, int NumberOfHiddenNodes, int NumberOfOutputs){
        this.inputs = NumberOfInputs;
        this.hiddens = NumberOfHiddenNodes;
        this.outputs = NumberOfInputs;

        weightsIH = new Matrix(this.hiddens, this.inputs);
        weightsHO = new Matrix(this.outputs, this.hiddens);

        weightsIH.randomize();
        weightsHO.randomize();

        biasH = new Matrix(hiddens, 1);
        biasO = new Matrix(outputs, 1);

        biasH.randomize();
        biasO.randomize();
    }

    float[] feedForward(float[] inputArray){
        
        Matrix inputMatrix = Matrix.fromArray(inputArray);

        Matrix hidden = Matrix.multiply(weightsIH, inputMatrix);
        hidden.add(this.biasH);
        hidden.sigmoidMap();

        Matrix output = Matrix.multiple(weightsHO, hidden);
        output.add(biasO);
        output.sigmoidMap();

        return Matrix.toArray(output)[0];
    }

}