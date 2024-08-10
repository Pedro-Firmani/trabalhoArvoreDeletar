public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor); //atribui o 'valor' para a variavel novoNo
        if (this.raiz == null) {
            this.raiz = novoNo; //se não havia raiz, esse novoNo agora é raiz
        } else {
            No atual = this.raiz; //atribui o valor da raiz para a variável auxiliar 'atual'
            No pai = null;
            boolean esquerda = false;
            while (atual != null) {
                if (novoNo.getValor() < atual.getValor()) { //caso o valor inserido seja menor que o valor atual (inicia pela raiz, vai seguindo pegando os valores da esquerda
                    pai = atual;  //coloca o valor atual como pai
                    atual = atual.getEsq(); //pega o valor que se encontra a esquerda do atual e insere na variável
                    esquerda = true;
                } else {
                    pai = atual;  //coloca o valor atual como pai
                    atual = atual.getDir(); //pega o valor que se encontra à direita do atual e insere na variável
                    esquerda = false;
                }
            }
            if (esquerda) { //dependendo do bool esquerda, vai setar o valor inserido como filho à direita ou esquerda do pai
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

    public boolean verificarFilhos(Integer valor) { //verifica apenas SE tem algum filho
        No atual = this.raiz;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()){
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
        if (atual.getDir() == null && atual.getEsq() == null) {
            return false; //não tem filhos
        }
        return true;
    }

    public boolean filhosXOR(Integer valor) { //considera que TEM algum filho, esse verifica se tem exatamente UM filho
        No atual = this.raiz;
        while (valor != atual.getValor()) {
            if (valor < atual.getValor()){
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
        if (atual.getDir() == null ^ atual.getEsq() == null) {
            return true; //se tiver pelo menos um nulo, retorna verdadeiro - há apenas um filho
        }
        return false;
    }

    public boolean busca(Integer valor) {
        No atual = this.raiz;
        boolean esquerda = false;
        do {
            if (valor < atual.getValor()) { //se o valor buscado for menor que a raiz, ele entra no loop de buscar sempre o próximo à esquerda
                atual = atual.getEsq();
            }else { //se for maior, busca sempre o próximo à direita
                atual = atual.getDir();
            }
            if (atual == null) {
                return false;
            }
        }while (atual.getValor() != valor);
        return true; //o método acaba quando o valor é encontrado na árvore
    }

    public void deletar(Integer valor) { //determina qual caso e qual método usar
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
        System.out.println("Numero " + valor + " excluído com sucesso.");
    }

    public void remocaoRaizPrimaria(No atual){
        No pai = atual;
        No segura = atual; //guarda as informações do nó a ser removido (raiz)
        if (atual.getDir() != null) {
            atual = atual.getDir(); //se houver filhos direitos da raiz, percorre cada valor setando como 'atual'
            if (atual.getEsq() == null){ //se o atual não tiver filhos à esquerda
                segura.setValor(atual.getValor());
                pai.setValor(segura.getValor());
                pai.setDir(atual.getDir());
                raiz.setValor(atual.getValor()); //coloca o valor da raiz como o valor do atual
            } else { //se o atual tiver filhos à esquerda
                while(atual.getEsq() != null){
                    pai = atual; //transforma o atual em pai e pega o filho esquerdo para percorrer novamente o loop, até encontrar o menor valor possível
                    atual = atual.getEsq();
                }
                if (atual.getDir() == null){ //se o menor valor à direita da raiz não tiver filhos à direita
                    segura.setValor(atual.getValor());
                    pai.setEsq(null);
                    raiz.setValor(segura.getValor());
                } else {
                    segura.setValor(atual.getValor());
                    pai.setEsq(atual.getDir());
                    raiz.setValor(segura.getValor()); //coloca no valor da raiz (segura), o valor do nó atual, preservando as ligações
                }
            }
        } else {
            raiz = raiz.getEsq();
        }
    }

    public void remocaoRaizSecundaria(No atual, Integer valor) {
        No paiNoRemovido = atual;
        No noSubstituidor = atual;
        No paiSubsituido = noSubstituidor;
        do { //encontrar o nó a ser removido
            if (valor < atual.getValor()) {
                paiNoRemovido = atual;
                atual = atual.getEsq();
            } else {
                paiNoRemovido = atual;
                atual = atual.getDir();
            }
        } while (atual.getValor() != valor); //encontra o nó que vai entrar no lugar do removido
        noSubstituidor = atual.getDir();
        if (noSubstituidor.getEsq() != null) {
            do {
                paiSubsituido = noSubstituidor;
                noSubstituidor = noSubstituidor.getEsq();
            } while (noSubstituidor.getEsq() != null);
            paiSubsituido.setEsq(noSubstituidor.getDir());
        }
        if (atual.getDir() == noSubstituidor){ //organiza os apontamentos da árvore
            atual.setDir(null);
        } else{
            noSubstituidor.setDir(atual.getDir());
        }
        noSubstituidor.setEsq(atual.getEsq());
        if (paiNoRemovido.getValor() > atual.getValor()) {
            paiNoRemovido.setEsq(noSubstituidor);
        } else {
            paiNoRemovido.setDir(noSubstituidor);
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
}
