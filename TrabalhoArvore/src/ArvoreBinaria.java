public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;
            No pai = null;
            boolean esquerda = false;
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) {
                    pai = atual;
                    atual = atual.getEsq();
                    esquerda = true;
                } else {
                    pai = atual;
                    atual = atual.getDir();
                    esquerda = false;
                }
            }
            if (esquerda) {
                pai.setEsq(novoNo);
            } else {
                pai.setDir(novoNo);
            }
        }
    }

    public No getRaiz() {
        return this.raiz;
    }

    public void preOrdem(No no) {
        if (no == null) {
            return;
        }
        System.out.println(no.getValor());
        preOrdem(no.getEsq());
        preOrdem(no.getDir());
    }

    public void emOrdem(No no) {
        if (no == null) {
            return;
        }
        emOrdem(no.getEsq());
        System.out.println(no.getValor());
        emOrdem(no.getDir());
    }

    public void posOrdem(No no) {
        if (no == null) {
            return;
        }
        posOrdem(no.getEsq());
        posOrdem(no.getDir());
        System.out.println(no.getValor());
    }

    public boolean verificarFilhos(Integer valor) {
        No atual = this.raiz;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()){
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
        if (atual.getDir() == null && atual.getEsq() == null) {
            return false;
        } return true;
    }
    public boolean filhosXOR(Integer valor) {
        No atual = this.raiz;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()){
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
        if (atual.getDir() == null ^ atual.getEsq() == null) {
            return true;
        } return false;
    }
    public boolean busca(Integer valor) {
        No atual = this.raiz;
        boolean esquerda = false;
        do {
            if (valor < atual.getValor()) {
                atual = atual.getEsq();
            }else {
                atual = atual.getDir();
            }
            if (atual == null) {
                return false;
            }
        }while (atual.getValor() != valor);
        return true;
    }
    public void remocaoRaizPrimaria(No atual){
        No pai = atual;
        No segura = atual;
        if (atual.getDir() != null) {
            atual = atual.getDir();
            if (atual.getEsq() == null){
                segura.setValor(atual.getValor());
                pai.setValor(segura.getValor());
                pai.setDir(atual.getDir());
                raiz.setValor(atual.getValor());
            } else {
                while(atual.getEsq() != null){
                    pai = atual;
                    atual = atual.getEsq();
                }
                if (atual.getDir() == null){
                    segura.setValor(atual.getValor());
                    pai.setEsq(null);
                    raiz.setValor(segura.getValor());
                } else {
                    segura.setValor(atual.getValor());
                    pai.setEsq(atual.getDir());
                    raiz.setValor(segura.getValor());
                }
            }
        } else {
            raiz = raiz.getEsq();
        }
    }
    public void remocaoRaizSecundaria(No atual, Integer valor) {
        No pai = atual;
        No aux = atual;
        do {
            if (valor < atual.getValor()) {
                pai = atual;
                atual = atual.getEsq();
            } else {
                pai = atual;
                atual = atual.getDir();
            }
        } while (atual.getValor() != valor);
        aux = atual.getDir();
        boolean foiPraEsquerda = false;
        if (aux.getEsq() != null) {
            No segura = aux;
            do {
                segura = aux;
                aux = aux.getEsq();
            } while (aux.getEsq() != null);
            segura.setEsq(null);
            foiPraEsquerda = true;
        }
        if (aux.getValor() < raiz.getValor()){
            pai.setEsq(atual.getDir());
            aux.setDir(atual.getEsq());
        } else {
            if (foiPraEsquerda) {
                aux.setDir(atual.getDir());
                aux.setEsq(atual.getEsq());
            } else {
                aux.setEsq(atual.getEsq());
                pai.setDir(aux);
            }
        }
    }

    public void remocaoFolhas(Integer valor) {
        No atual = this.raiz;
        No pai = atual;
        boolean esquerda = false;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()) {
                pai = atual;
                atual = atual.getEsq();
                esquerda = true;
            } else {
                pai = atual;
                atual = atual.getDir();
                esquerda = false;
            }
        }
        if (esquerda) {
            pai.setEsq(null);
        } else {
            pai.setDir(null);
        }
    }
    public void remocaoComUmFilho(Integer valor) {
        No atual = this.raiz;
        No pai = atual;
        boolean esquerda = false;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()) {
                pai = atual;
                atual = atual.getEsq();
                esquerda = true;
            } else {
                pai = atual;
                atual = atual.getDir();
                esquerda = false;
            }
        }
        if (esquerda) {
            if (atual.getEsq() == null) {
                pai.setEsq(atual.getDir());
            } else {
                pai.setEsq(atual.getEsq());
            }
        } else {
            if (atual.getDir() == null) {
                pai.setDir(atual.getEsq());
            } else {
                pai.setDir(atual.getDir());
            }
        }
    }

    public void deletar(Integer valor) {
        if (this.raiz == null) {
            System.out.println("Não existe arvore.");
        }
        if (valor == raiz.getValor()){
            No atual = this.raiz;
            remocaoRaizPrimaria(atual);
            System.out.println("Numero " + valor + " excluído com sucesso.");
            return;
        }
        if (busca(valor) == false){
            System.out.println("O numero " + valor +" não está na arvore.");
            return;
        }
        verificarFilhos(valor);
        if (verificarFilhos(valor) == false) {
            remocaoFolhas(valor);
            System.out.println("Numero " + valor + " excluído com sucesso.");
            return;
        }
        if (filhosXOR(valor) == true){
            remocaoComUmFilho(valor);
            System.out.println("Numero " + valor + " excluído com sucesso.");
            return;
        }
        No atual = this.raiz;
        remocaoRaizSecundaria(atual, valor);
    }
}