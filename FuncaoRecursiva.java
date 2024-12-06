//Função recursiva para realizar busca binária.

package funcaorecursiva;

public class FuncaoRecursiva {

    public static int buscaBinaria(int x, int n, int e, int d, int v[]){
        
        if(e >= d-1){
            return d;
	}

        System.out.println("Divide");
        
	int m = (e + d)/2;

	if (v[m] < x){
            e = m;
        } else {
            d = m;
        }

	return buscaBinaria(x, n, e, d, v);

    }
    
    public static void main(String[] args) {
        
        int vetor[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
        System.out.println(buscaBinaria(1, 39, -1, 39, vetor));
    
    }
    
}
