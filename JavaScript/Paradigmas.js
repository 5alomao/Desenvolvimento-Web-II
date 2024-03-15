const soma = function (v1, v2) {
    return v1 + v2;
}

// Sintaxe da ECMAScript 6
class Pessoa {
    // atributos
    constructor(nome, cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    // métodos
    saudar() {
        console.log(`Nome: ${this.nome}, CPF: ${this.cpf}`);
    }
}

const emerson = new Pessoa('Emerson', '123');
const aluno = new Pessoa('Salomão', '321');

// Strings
const nome = 'Emerson';
const meio = 'Assis';

// Template Strings
const familia = 'Carvalho';

const completo = `${nome} ${meio} ${familia}`;
console.log(completo);

console.log(emerson.nome);
console.log(emerson.cpf);
console.log(familia);
console.log(typeof emerson);
console.log(typeof Pessoa);

