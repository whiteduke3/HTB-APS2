public class HTB {
    int p, m, zasedenost, c1, c2, soupadanja, i;
    Integer[] T;

    public HTB(int p, int m, int c1, int c2) {
        this.p = p;
        this.m = m;
        this.c1 = c1;
        this.c2 = c2;
        this.i = 0;
        this.T = new Integer[this.m];
        this.zasedenost = 0;
        this.soupadanja = 0;
    }

    boolean tabelaPolna() {
        return this.zasedenost == this.m;
    }


    void spremeniTabelo(int novaVelikost) {
        Integer[] novaTabela = this.T.clone();
        this.T = new Integer[novaVelikost];
        for (int i = 0; i < novaTabela.length; i++) {
            if (novaTabela[i] != null) {
                int index = (novaTabela[i] * this.p) % this.m;
                if (this.T[index] == null) {
                    this.T[index] = novaTabela[i];
                } else {
                    kvadPrenaslavljanje(novaTabela[i]);
                }
            }
        }
    }

    void insert(int key) {
        if (tabelaPolna()) {
            this.soupadanja++;
            this.m = 2 * this.m + 1;
            spremeniTabelo(this.m);
        }

        if(find(key)) return;

        int hk = (key * this.p) % this.m;

        if (this.T[hk] == null) {
            this.T[(key * this.p) % this.m] = key;
        } else if(this.T[hk] != null && this.T[hk] == key) {
            return;
        } else {
            kvadPrenaslavljanje(key);
        }
        this.zasedenost++;
    }

    void kvadPrenaslavljanje(int key) {
        int hk = (key * this.p) % this.m;
        int i = 0;
        int index = (hk + i * this.c1 + this.c2 * (i*i)) % this.m;
        //this.soupadanja++;
        while (this.T[index] != null) {
            if (i > this.m) {
                this.m = 2 * this.m + 1;
                spremeniTabelo(this.m);
            }
            index = (hk + i * this.c1 + this.c2 * (i*i)) % this.m;
            i++;
            this.soupadanja++;
        }
        this.T[index] = key;
    }

    boolean find(int key) {
        for (int i = 0; i < this.T.length; i++) {
            if (this.T[i] != null && this.T[i] == key) return true;
        }
        return false;
    }

    void delete(int key) {
        for(int i = 0; i < this.T.length; i++) {
            if (this.T[i] != null && this.T[i] == key) {
                this.T[i] = null;
            }
        }
    }

    void printKeys() {
        for(int i = 0; i < this.T.length; i++) {
            if(this.T[i] != null) {
                System.out.println(i + ": " + this.T[i]);
            }
        }
    }

    void printCollisions() {
        System.out.println(this.soupadanja);
    }
}
