//Written by Anirudh Giri
//Inspired by the book Make Your Own Neural Network by Tariq Rashid and
//youtube lessons by Daniel Shiffman from The Coding Train

class NeuralNetwork {
    int inputs;
    int hLayers;
    int hiddens;
    int outputs;

    Matrix weightsIH;
    Matrix weightsHO;

    Matrix hWeights[];

    Matrix biasH[];
    Matrix biasO;
    float learning_rate = 0.1f;

    String activationFunction;

    NeuralNetwork(int NumberOfInputs, int NumberOfHiddenLayers, int NumberOfHiddenNodes, int NumberOfOutputs){
        this.inputs = NumberOfInputs;
        this.hLayers = NumberOfHiddenLayers;
        this.hiddens = NumberOfHiddenNodes;
        this.outputs = NumberOfOutputs;

        weightsIH = new Matrix(this.hiddens, this.inputs);
        weightsHO = new Matrix(this.outputs, this.hiddens);

        weightsIH.randomize(-1,1);
        weightsHO.randomize(-1,1);

        hWeights = new Matrix[hLayers-1];
        for(int i = 0; i<hWeights.length; i++){
            hWeights[i] = new Matrix(this.hiddens,this.hiddens);
            hWeights[i].randomize(-1,1);
        }

        biasH = new Matrix[hLayers];
        for(int i=0; i<biasH.length; i++){
            biasH[i] = new Matrix(this.hiddens, 1);
            biasH[i].randomize(-1,1);
        }

        biasO = new Matrix(this.outputs, 1);
        biasO.randomize(-1,1);

        activationFunction = "SIGMOID";
    }

    NeuralNetwork(int NumberOfInputs, int NumberOfHiddenLayers, int NumberOfHiddenNodes, int NumberOfOutputs, String activationFunction){
        this.inputs = NumberOfInputs;
        this.hLayers = NumberOfHiddenLayers;
        this.hiddens = NumberOfHiddenNodes;
        this.outputs = NumberOfOutputs;

        weightsIH = new Matrix(this.hiddens, this.inputs);
        weightsHO = new Matrix(this.outputs, this.hiddens);

        weightsIH.randomize(-1,1);
        weightsHO.randomize(-1,1);

        hWeights = new Matrix[hLayers-1];
        for(int i = 0; i<hWeights.length; i++){
            hWeights[i] = new Matrix(this.hiddens,this.hiddens);
            hWeights[i].randomize(-1,1);
        }

        biasH = new Matrix[hLayers];
        for(int i=0; i<biasH.length; i++){
            biasH[i] = new Matrix(this.hiddens, 1);
            biasH[i].randomize(-1,1);
        }

        biasO = new Matrix(this.outputs, 1);
        biasO.randomize(-1,1);

        this.activationFunction = (activationFunction.equalsIgnoreCase("SIGMOID") 
        || activationFunction.equalsIgnoreCase("RELU")) ? activationFunction.toUpperCase() : "SIGMOID";
    }

    //Sets the learning rate for the neural network
    void setLearningRate(float learning_rate){
        this.learning_rate = learning_rate;
    }

    //Sets the activation function for the neural network
    void setActivationFunction(String activationFunction){
        this.activationFunction = activationFunction;
    }

    float[] predict(float[] inputArray){
        //Feedforward Algorithm
        Matrix inputMatrix = Matrix.fromArray(inputArray);

        Matrix hiddens[] = new Matrix[hLayers];
        hiddens[0] = Matrix.multiply(this.weightsIH, inputMatrix);
        hiddens[0].add(biasH[0]);
        hiddens[0].activationMap(this.activationFunction);
        for(int i=0; i<hiddens.length-1; i++){
            hiddens[i+1] = Matrix.multiply(this.hWeights[i],hiddens[i]);
            hiddens[i+1].add(biasH[i+1]);
            hiddens[i+1].activationMap(this.activationFunction);
        } 

        Matrix output = Matrix.multiply(this.weightsHO,hiddens[hiddens.length-1]);
        output.add(this.biasO);
        output.softmax();
        return Matrix.toArray(Matrix.transpose(output))[0];
    }

    void train(float[] inputArray, float[] targetsArray){
        //Feedforward Algorithm
        Matrix inputMatrix = Matrix.fromArray(inputArray);

        Matrix hiddens[] = new Matrix[hLayers];
        hiddens[0] = Matrix.multiply(this.weightsIH, inputMatrix);
        hiddens[0].add(biasH[0]);
        hiddens[0].activationMap(this.activationFunction);
        for(int i=0; i<hiddens.length-1; i++){
            hiddens[i+1] = Matrix.multiply(this.hWeights[i],hiddens[i]);
            hiddens[i+1].add(biasH[i+1]);
            hiddens[i+1].activationMap(this.activationFunction);
        } 

        Matrix output = Matrix.multiply(this.weightsHO,hiddens[hiddens.length-1]);
        output.add(this.biasO);
        output.softmax();

        //Creating a targets matrix from the float array
        Matrix targets = Matrix.fromArray(targetsArray);

        //Calculating the errors
        Matrix outputErrors = Matrix.subtract(targets,output);
        
        //Calculate the gradients
        Matrix gradients = Matrix.derivativeActivationMap(this.activationFunction,output);
        gradients.hadamard(outputErrors);
        gradients.multiply(this.learning_rate);

        //Calculate the deltas
        Matrix weightsHO_Deltas = Matrix.multiply(gradients,Matrix.transpose(hiddens[hLayers-1]));
        
        //Adjust the weights by it's deltas
        this.weightsHO.add(weightsHO_Deltas);
        
        //Adjust the bias by it's deltas
        this.biasO.add(gradients);

        //Calculate the hidden layer errors
        Matrix hiddenErrors[] = new Matrix[hLayers];

        //Calculate the errors for the HO layer
        hiddenErrors[hLayers-1] = Matrix.multiply(Matrix.transpose(this.weightsHO), outputErrors);

        //Calculate the gradients for the HO layer
        Matrix hiddenGradient = Matrix.derivativeActivationMap(this.activationFunction,hiddens[hLayers-1]);
        hiddenGradient.hadamard(hiddenErrors[hLayers-1]);
        hiddenGradient.multiply(this.learning_rate);
        
        //Calculates the deltas for the last hidden layer
        Matrix wDeltas;

        if(hLayers != 1){ //(If there is only one hidden layer, we need to tune weightsIH instead)
        wDeltas = Matrix.multiply(hiddenGradient,Matrix.transpose(hiddens[hLayers-2]));
        this.hWeights[hLayers-2].add(wDeltas);
        biasH[hLayers-2].add(hiddenGradient);
        }

        //tune all other weights through backpropogation (except for the first layer's weights)
        for(int i = hLayers-2; i>0; i--){
            hiddenErrors[i] = Matrix.multiply(Matrix.transpose(this.hWeights[i-1]), hiddenErrors[i+1]);

            hiddenGradient = Matrix.derivativeActivationMap(this.activationFunction,hiddens[i]);
            hiddenGradient.hadamard(hiddenErrors[i]);
            hiddenGradient.multiply(this.learning_rate);

            wDeltas = Matrix.multiply(hiddenGradient,Matrix.transpose(hiddens[i-1]));
            this.hWeights[i-1].add(wDeltas);

            biasH[i].add(hiddenGradient);
        }

        //tune the first layer's weights
        hiddenErrors[0] = Matrix.multiply(Matrix.transpose(hWeights[0]), hiddenErrors[1]);

        hiddenGradient = Matrix.derivativeActivationMap(this.activationFunction,hiddens[0]);
        hiddenGradient.hadamard(hiddenErrors[0]);
        hiddenGradient.multiply(this.learning_rate);

        wDeltas = Matrix.multiply(hiddenGradient,Matrix.transpose(inputMatrix));
        this.weightsIH.add(wDeltas);

        biasH[0].add(hiddenGradient);

    }
}
