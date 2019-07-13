![logo](https://i.imgur.com/D6f4eJR.png)
# TinyNeuralNetwork4Java

TinyNN4J is a Java library for implementing Neural Networks into your projects


## Usage

```java

//(number of inputs, number of nodes in hidden layer, number of outputs)
NeuralNetwork n = new NeuralNetwork(ins,hiddenNodes,outs);

float[] trainingInputs = {1.0,2.0,3.0};
float[] trainingOutputs = {0.5,0.7,0.9};

n.train(trainingInputs,trainingOutputs);

float[] testData = {5.0};
float outputs[] = n.test(testData);
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)

## Bibliography
[Make Your Own Neural Network](https://www.amazon.com/gp/product/1530826608/) by Tariq Rashid

[3Blue1Brown](https://github.com/3b1b/)'s [Neural Network Playlist](https://www.youtube.com/playlist?list=PLZHQObOWTQDNU6R1_67000Dx_ZCJB-3pi/)

[Daniel Shiffman](https://github.com/shiffman/)'s [Neural Network Playlist](https://www.youtube.com/playlist?list=PLRqwX-V7Uu6aCibgK1PTWWu9by6XFdCfh/)
