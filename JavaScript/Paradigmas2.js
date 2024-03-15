// Sintaxe pré ES6

// Função construtora
function Pessoa(nome, cpf) {
    this.nome = nome;
    this.cpf = cpf;

    this.saudar = function () {
        console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
    };
}

const programador = new Pessoa("Salomão", "123");

// console.log(programador.nome)
// console.log(programador.cpf)
console.log(programador.saudar());
console.log(typeof Pessoa);

Pessoa.prototype.saudarNome = function () {
    console.log(`Nome: ${this.nome}`);
}
console.log(Pessoa.prototype);
console.log(programador.saudarNome());