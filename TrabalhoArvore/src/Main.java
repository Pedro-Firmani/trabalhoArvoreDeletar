public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria= new ArvoreBinaria();
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(4);
        arvoreBinaria.inserir(6);
        arvoreBinaria.inserir(7);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(2);
        arvoreBinaria.inserir(14);
        arvoreBinaria.inserir(12);
        arvoreBinaria.inserir(18);
        arvoreBinaria.inserir(17);
        arvoreBinaria.inserir(19);
        arvoreBinaria.inserir(13);
        //10,4,6,7,5,2,14,12,18,17,19,13

        arvoreBinaria.preOrdem(arvoreBinaria.getRaiz());
        System.out.println();
        arvoreBinaria.deletar(18);
        arvoreBinaria.preOrdem(arvoreBinaria.getRaiz());

    }
}