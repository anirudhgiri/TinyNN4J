class Matrix{
    int rows;
    int cols;
    float matrix[][];

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.matrix = new float[rows][cols];
    }

    void multiply(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] *= n;
    }

    void add(int n){
        for(int i=0; i<this.rows; i++)
            for(int j=0; j<this.cols; j++)
                this.matrix[i][j] += n;
    }

}