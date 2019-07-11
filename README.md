![logo](https://i.imgur.com/D6f4eJR.png)
# TinyNeuralNetwork4Java

TinyNN4J is a Java library for Deep Learning


## Usage

```java
/*
(number of inputs, number of hidden layers, number of nodes per 
hidden layer, number of outputs)
*/
NeuralNetwork n = new NeuralNetwork(ins,hiddenLayers,hiddenNodes,outs);
float[] trainingInputs = {1.0,2.0,3.0};
float[] trainingOutputs = {0.5,0.7,0.9};
n.train(trainingInputs,trainingOutputs);
float[] testData = {5.0};
n.guess(testData);
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
