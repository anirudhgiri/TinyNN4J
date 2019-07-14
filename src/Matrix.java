class Matrix{
    int rows;
    int cols;
    float matrix[][];

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.matrix = new float[rows][cols];
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] = 0;
    }

    //fills all elements in the matrix with a random value between 0 and 1
    void randomize(){
        for(int i = 0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] = (float)(Math.random());

    }

    //fills all elements in the matrix with a random value between a and b
    void randomize(int a , int b){
        for(int i = 0; i<this.rows; i++)
            for(int j = 0; j<this.cols; j++)
                this.matrix[i][j] = (float)(a + ((b-a)*Math.random()));
    }

    //multiplying the matrix with a scalar
    void multiply(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] *= n;
    }

    //multiplying the matrix with a scalar
    void multiply(float n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] *= n;
    }

    //returns the matrix product of the matrices
    static Matrix multiply(Matrix m1, Matrix m2){
        if(m1.cols != m2.rows){
            System.err.println("Matrix multiply size problem!");
            System.err.println("M1 : "+ m1.rows + " " + m1.cols);
            System.err.println("M2 : "+ m2.rows + " " + m2.cols);
        }
        
        Matrix result = new Matrix(m1.rows, m2.cols);

        for(int i =0; i < result.rows; i++)
            for(int j=0; j < result.cols; j++){
                float sum = 0;
                for(int k = 0; k < m1.cols; k++)
                    sum += m1.matrix[i][k] * m2.matrix[k][j];
                result.matrix[i][j] = sum;
            }

        return result;
            
    }

    //returns the hadamard product of the matrices
    void hadamard(Matrix m){
        for(int i=0; i<m.rows; i++)
            for(int j=0; j<m.cols; j++)
                this.matrix[i][j] *= m.matrix[i][j];
    }

    //element-wise addition of a matrix with a scalar
    void add(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] += n;
    }

    //element-wise addition of a matrix with another matrix
    void add(Matrix m){
        if(this.rows != m.rows || this.cols != m.cols){
            System.err.println("add() rows cols not matching exception");
        }
        
        for(int i=0;i<this.rows;i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] += m.matrix[i][j];
    }

    //element-wise subtraction of a matrix with another matrix (static function)
    static Matrix subtract(Matrix m1, Matrix m2){
        if(m1.rows != m2.rows || m1.cols != m2.cols)
            System.out.println(m1.rows+" "+m1.cols+"\n"+m2.rows+" "+m2.cols);
        
        Matrix result = new Matrix(m2.rows, m2.cols);

        for(int i = 0; i<m2.rows; i++)
            for(int j = 0; j<m2.cols; j++)
                result.matrix[i][j] = m1.matrix[i][j] - m2.matrix[i][j];
        
        return result;   
    }

    //element-wise subtraction of a matrix with another matrix 
    Matrix subtract(Matrix m){
        if(m.rows != this.rows || m.cols != this.cols)
            System.out.println(m.rows+" "+m.cols+"\n"+this.rows+" "+this.cols);
        
        Matrix result = new Matrix(m.rows, m.cols);

        for(int i = 0; i<m.rows; i++)
            for(int j = 0; j<m.cols; j++)
                result.matrix[i][j] = m.matrix[i][j] - this.matrix[i][j];
        
        return result;   
    }

    //returns the transpose of a matrix
    static Matrix transpose(Matrix m){
        Matrix transposedMatrix = new Matrix(m.cols, m.rows);
        for(int i = 0; i<m.rows; i++)
            for(int j = 0; j<m.cols; j++)
                transposedMatrix.matrix[j][i] = m.matrix[i][j];
        return transposedMatrix;
    }
    
    //prints the elements of the matrix
    void print(){
            for(int i=0;i<this.rows;i++){
                for(int j=0; j<this.cols; j++)
                    System.out.print(this.matrix[i][j] + " ");
                System.out.println("\n");
                }
    }

    //converts a one dimensional array to a matrix
    static Matrix fromArray(float[] array){
        Matrix m = new Matrix(array.length,1);
        for(int i = 0; i < array.length; i++)
            m.matrix[i][0] = array[i];
        return m;
    }

    //converts a two dimensional array to a matrix
    static Matrix fromArray(float[][] array){
        Matrix m = new Matrix(array.length,array[0].length);
        m.matrix = array;
        return m;
    }

    //converts a matrix to an array
    static float[][] toArray(Matrix m){
        float[][] arr = new float[m.rows][m.cols];
        for(int i = 0; i< m.rows; i++)
            for(int j = 0; j< m.cols; j++)
                arr[i][j] = m.matrix[i][j];
        return arr;
    }

    //returns the value of Sigmoid(x)
    float sigmoid(float x){
        return (float)(1 / (1 + Math.exp(-x)));
    }

    //applies the sigmoid function to each element of the matrix
    void sigmoidMap(){
        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < this.cols; j++)
                this.matrix[i][j] = sigmoid(this.matrix[i][j]);
    }

    //applies the derivative of the sigmoid function to each element of the matrix
    static Matrix derivativeSigmoidMap(Matrix m){
        Matrix output = new Matrix(m.rows, m.cols);
        
        for(int i = 0; i < output.rows; i++)
            for(int j = 0; j < output.cols; j++)
            output.matrix[i][j] = (m.matrix[i][j]) * (1.0f-m.matrix[i][j]);
        return output;
    }

}
