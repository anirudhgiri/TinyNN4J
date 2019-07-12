Training[] t;   
NeuralNetwork nn;

void setup(){
  nn = new NeuralNetwork(2,3,1);
  
  t = new Training[4];
  
  int counter = 0;
  
    for(int i = 0; i < 2; i++)
      for(int j = 0; j<2; j++){
        float inputs[] = {i,j};
        float outputs[] = {(i==j)?0:1};
        t[counter] = new Training(inputs, outputs);
        counter++;
      }
      
   size(600,600);  
}

void draw(){
  background(255);
  
  for(int i = 0; i<1000; i++){
  Training data = t[floor(random(4))];
  nn.train(data.inputs, data.outputs); 
  }
  
  int res = 10;
  float cols = width/res;
  float rows = height/res;
  
  for(int i = 0; i<rows; i++)
    for(int j = 0; j<cols; j++){
      float x1 = i/rows;
      float x2 = j/cols;
      float[] inputs = {x1, x2};
      float y = Matrix.toArray(nn.predict(inputs))[0][0];
      fill(map(y,0,1,0,255));
      rect(i*res, j*res, res, res);
      noStroke();
    }
      
}
