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

    void randomize(){
        for(int i = 0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] = (float)((Math.random()*2)-1);

    }

    void multiply(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] *= n;
    }

    static Matrix multiply(Matrix m1, Matrix m2){
        if(m1.cols != m2.rows){
            System.out.println("Columns of first matrix must equal rows of second matrix");
            return null;
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

    void add(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] += n;
    }

    void add(Matrix m){
        for(int i=0;i<this.rows;i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] += m.matrix[i][j];
    }

    static Matrix transpose(Matrix m){
        Matrix transposedMatrix = new Matrix(m.rows, m.cols);
        for(int i = 0; i<m.rows; i++)
            for(int j = 0; j<m.cols; j++)
                transposedMatrix.matrix[j][i] = m.matrix[i][j];
        return transposedMatrix;
    }
    
    void print(){
            for(int i=0;i<this.rows;i++){
                for(int j=0; j<this.cols; j++)
                    System.out.print(this.matrix[i][j] + " ");
                System.out.println("\n");
                }
    }

    static Matrix fromArray(float[] array){
        Matrix m = new Matrix(array.length,1);
        for(int i = 0; i < array.length; i++)
            m.matrix[i][0] = array[0];
        return m;
    }

    static float[][] toArray(Matrix m){
        float[][] arr = new float[m.rows][m.cols];
        for(int i = 0; i< m.rows; i++)
            for(int j = 0; j< m.cols; j++)
                arr[i][j] = m.matrix[i][j];
        return arr;
    }

    float sigmoid(float x){
        return (float)(1 / (1 - Math.exp(-x)));
    }

    void sigmoidMap(){
        for(int i = 0; i < this.rows; i++)
            for(int j = 0; j < this.cols; j++)
                this.matrix[i][j] = sigmoid(this.matrix[i][j]);
    }

}