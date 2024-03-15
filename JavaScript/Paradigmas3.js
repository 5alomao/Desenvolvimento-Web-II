const objeto = {
    nome: 'Salomão',
    cpf: '123',
    nascimento: '15/07/2003',
    saudar: function () {
        console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
    }
};

console.log(typeof objeto);
console.log(objeto.saudar());

// Template da 'Classe'
Object.getPrototypeOf(objeto).saudar2 = function () {
    console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
}

console.log(objeto.saudar2());

console.log(objeto.prototype);