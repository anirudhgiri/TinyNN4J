![logo](https://i.imgur.com/D6f4eJR.png)
# TinyNeuralNetwork4Java

TinyNN4J is a Java library for implementing Neural Networks into your projects


## Usage
[Download](https://github.com/anirudhgiri/TinyNN4J/raw/master/lib/TinyNN4J.jar) the jar file and add it to your build path.
To use with Processing3, simply drag the jar file into the Processing editor.
```java

//(number of inputs, number of nodes in hidden layer, number of outputs)
NeuralNetwork nn = new NeuralNetwork(3,5,1);

nn.setActivationFunction("RELU"); //default is SIGMOID
nn.setLearningRate(0.01f); //default is 0.1f

//3 inputs and 1 output
float[] trainingInputs = {1.0,2.0,3.0};
float[] trainingOutputs = {0.5};

nn.train(trainingInputs,trainingOutputs);

float[] testData = {1.1,2.1,3.1};
//output will be a float[] with size equal to the number of output nodes
float outputs[] = n.predict(testData);
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Bibliography
[Make Your Own Neural Network](https://www.amazon.com/gp/product/1530826608/) by Tariq Rashid

[3Blue1Brown](https://github.com/3b1b/)'s [Neural Network Playlist](https://www.youtube.com/playlist?list=PLZHQObOWTQDNU6R1_67000Dx_ZCJB-3pi/)

[Daniel Shiffman](https://github.com/shiffman/)'s [Neural Network Playlist](https://www.youtube.com/playlist?list=PLRqwX-V7Uu6aCibgK1PTWWu9by6XFdCfh/)
