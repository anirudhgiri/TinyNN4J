//Written by Anirudh Giri

class NeuralNetwork {
    float[] inputs;
    float[][] hiddens;
    float[] outputs;

    NeuralNetwork(int NumberOfInputs, int hiddenLayers, int hiddenNodes, int NumberOfOutputs){
        this.inputs = new float[NumberOfInputs];
        this.hiddens = new float[hiddenLayers][hiddenNodes];
        this.outputs = new float[NumberOfOutputs];
    }
}