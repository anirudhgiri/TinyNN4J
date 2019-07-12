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
    float learning_rate = 0.1f;

    NeuralNetwork(int NumberOfInputs, int NumberOfHiddenNodes, int NumberOfOutputs){
        this.inputs = NumberOfInputs;
        this.hiddens = NumberOfHiddenNodes;
        this.outputs = NumberOfOutputs;

        weightsIH = new Matrix(this.hiddens, this.inputs);
        weightsHO = new Matrix(this.outputs, this.hiddens);

        weightsIH.randomize();
        weightsHO.randomize();

        biasH = new Matrix(this.hiddens, 1);
        biasO = new Matrix(this.outputs, 1);

        biasH.randomize();
        biasO.randomize();
    }

    Matrix predict(float[] inputArray){
        //Feedforward Algorithm
        Matrix inputMatrix = Matrix.fromArray(inputArray);

        Matrix hidden = Matrix.multiply(this.weightsIH, inputMatrix);
        hidden.add(this.biasH);
        hidden.sigmoidMap();

        Matrix output = Matrix.multiply(this.weightsHO,hidden);
        output.add(this.biasO);
        output.sigmoidMap();

        return output;
    }

    void train(float[] inputArray, float[] targetsArray){
        //Feedforward Algorithm
        Matrix inputsMatrix = Matrix.fromArray(inputArray);

        Matrix hidden = Matrix.multiply((this.weightsIH), inputsMatrix);
        hidden.add(this.biasH);
        hidden.sigmoidMap();

        Matrix outputs = Matrix.multiply(this.weightsHO, hidden);
        outputs.add(this.biasO);
        outputs.sigmoidMap();
        
        //Creating a targets matrix from the float array
        Matrix targets = Matrix.fromArray(targetsArray);

        //Calculating the errors
        Matrix outputErrors = Matrix.subtract(targets,outputs);
        
        //Calculate the gradients
        Matrix gradients = Matrix.derivativeSigmoidMap(outputs);
        gradients.hadamard(outputErrors);
        gradients.multiply(this.learning_rate);

        //Calculate the deltas
        Matrix weightsHO_Deltas = Matrix.multiply(gradients,Matrix.transpose(hidden));
        
        //Adjust the weights by it's deltas
        this.weightsHO.add(weightsHO_Deltas);
        
        //Adjust the bias by it's deltas
        this.biasO.add(gradients);

        
        //Calculate the hidden layer errors
        Matrix hiddenErrors = Matrix.multiply(Matrix.transpose(this.weightsHO), outputErrors);
        
        //Caclulate the gradients for the hidden layers
        Matrix hiddenGradient = Matrix.derivativeSigmoidMap(hidden);
        hiddenGradient.hadamard(hiddenErrors);
        hiddenGradient.multiply(this.learning_rate);

        //calculate the deltas for all weights from the input layer to the hidden layer
        Matrix weightsIH_Deltas = Matrix.multiply(hiddenGradient,Matrix.transpose(inputsMatrix));
        this.weightsIH.add(weightsIH_Deltas);
        
        //Adjust the bias by it's deltas
        this.biasH.add(hiddenGradient);
    }
}